/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.HasParam;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.NotImplementedOperationException;
import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Processor;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.openehrclient.AqlEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Patient;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.ehrbase.client.aql.query.Query;
import org.springframework.stereotype.Component;


/**
 * Test to find Patient in openEHR.
 *
 */
@Component
public class FindPatientOpenehrProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(FindPatientOpenehrProcessor.class);

    private final OpenEhrClient openEhrClient;

    public FindPatientOpenehrProcessor(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("Processing...");

        if (isSearchOperation(exchange)) {
            handleSearchOperation(exchange);
        } else {
            handleReadOperation(exchange);
        }
    }

    private void handleSearchOperation(Exchange exchange) throws Exception {
        LOG.debug("Execute 'search' operation");

        List<String> codeValues;
        HasParam tmpParam;

        // these are all the inbound parameters as they come from the client
        /*
        Sample paramName and values:

        _summary:
        count

        _has:Encounter:patient:date:
        2020

        # if the same parameter name appears twice in the request, here we have just one name with two values
        # note for the 'code' attribute we can have a string value that represents a list of values, but it comes as a single string
        _has:Observation:patient:code:
        2160-0,14682-9,3091-6,22664-7
        1743-4,1742-6,30239-8,1920-8,88112-8

        _has:Condition:patient:code:
        C34.0,C34.1,C34.2,C34.3,C34.8,C34.9
        */
        RequestDetails request = extractRequestDetails(exchange);
        Map<String, String[]> inParams = request.getParameters();

        // Gather all the information for the parameters including the templateId lookup result for the code params
        List<HasParamTemplate> outParams = new ArrayList<>();

        // aux structure to simplify creation of AQL queries based on templates
        // templateId => list of params which values will be included in the same AQL query
        // because of how this is constructed, only parameters for the 'code' attribute will
        // be in this map, so based on the template, resource type and attribute name (code),
        // we know which path to use in the AQL
        Map<String, List<HasParamTemplate>> templateParamMap = new LinkedHashMap<>();

        for (String paramName : inParams.keySet()) {

            // paramName: _has:Encounter:patient:date or _summary or _has:Encounter:patient:date
            String[] paramValues = inParams.get(paramName);

            if (paramName.startsWith("_summary") && paramValues.length > 0) {

                // this will be handled below by the headers of the camel message
                // paramValues[0].equals("count") means we should return only the count
                continue;

            } else if (paramName.startsWith("_has")) {

                for (int i = 0; i < paramValues.length; i++) { // multiple values if the same param was submitted more than once

                    tmpParam = new HasParam();

                    // 3rd param is the key without the _has
                    tmpParam.setValueAsQueryToken(null, ca.uhn.fhir.rest.api.Constants.PARAM_HAS, paramName.substring(4), paramValues[i]);

                    // if any of the codes in getParameterValue() matches a template, all the codes will be compared to a path in that template
                    // it might be semantically incorrect to have codes in the same list matching different templates
                    // so one match is enough to know the template
                    // but then we need an extra piece of metadata to know which path corresponds to the getTargetResourceType() . getParameterName() FHIR attribute,
                    // which could be Observation.code or Condition.code.
                    // if the getParameterName() is not "code" then it should be processed separately, for instance Encounter.date
                    //System.out.println(param.getTargetResourceType() + "." + param.getParameterName() + "=" + param.getParameterValue());

                    // process only the values of the 'code' attribute from the TargetResourceType (Observation, Condition, etc.)
                    if (tmpParam.getParameterName().equals("code")) {

                        // process each code in the code list individually because we can't be sure the
                        // query parameter codes are consistent (all match the same template/path)
                        codeValues = Arrays.stream(tmpParam.getParameterValue().split(","))
                                .map(String::trim)
                                .collect(Collectors.toList());

                        Set<String> templateIds;

                        for (String code : codeValues) {

                            templateIds = this.findMatchingTemplates(code);

                            // one outParam for each code in the list
                            // if the matching template is the same, all the codes could be joined in the query, but just for that template
                            // if there are many matching templates for each code, then different codes can be joined per template and there
                            // will be one query per template, so if we have:
                            // code1 | template1, template2
                            // code2 | template2, template3
                            //
                            // there should be 3 aql queries: (pathX is defined in templateX)
                            // SELECT .. FROM ... WHERE path1 = code1
                            // SELECT .. FROM ... WHERE path2 matches {code1, code2}
                            // SELECT .. FROM ... WHERE path3 = code2
                            HasParamTemplate outParam = new HasParamTemplate(
                                 tmpParam.getTargetResourceType(),
                                 tmpParam.getParameterName(),
                                 code,
                                 templateIds
                            );

                            // build aux structure to join all params that will be used ont the AQL query for the same templateId
                            List<HasParamTemplate> templateParams;
                            boolean isTemplateParam = false;

                            for (String templateId : templateIds) {

                                templateParams = templateParamMap.get(templateId);

                                if (templateParams == null) { // initialize the list for the current templateId key

                                    templateParams = new ArrayList<>();
                                    templateParams.add(outParam);
                                    templateParamMap.put(templateId, templateParams);

                                } else {

                                    templateParams.add(outParam);
                                }

                                // true only if there are matching templates for the code value
                                isTemplateParam = true;
                            }

                            // outParams has all the params that are not going to be used in queries based on templateIds
                            if (!isTemplateParam) {
                                outParams.add(outParam);
                            }
                        }
                    } else if (tmpParam.getParameterName().equals("date")) {

                        HasParamTemplate outParam = new HasParamTemplate(
                            tmpParam.getTargetResourceType(),
                            tmpParam.getParameterName(),
                            tmpParam.getParameterValue(), // should be a single value
                            null
                        );

                        outParams.add(outParam);
                    }
                }
            }
        }

        // execute queries based on the processed paramters and join results
        Set<String> ehrIds = new HashSet<>();

        // process queries not based on templates
        for (HasParamTemplate outParam : outParams) {

            // TODO: execute queries based on resource type and attribute name, no template lookupt here because
            // the attribute is not 'code', so we need to search manually for the possible matching templates
            // and hardcode them here.
            //
            // Encounter => Patientenaufenthalt (openEHR-EHR-COMPOSITION.event_sumary.v1)
            // Encounter => Stationärer Versorgungsfall (openEHR-EHR-COMPOSITION.fall.v1)
            System.out.println(outParam.getValue());
        }

        // process queries based on templates
        for (Map.Entry<String, List<HasParamTemplate>> templateParamsEntry : templateParamMap.entrySet() ) {

            if (templateParamsEntry.getKey().equals("GECCO_Laborbefund")) {
                ehrIds.addAll(handleQueryForGECCO_Laborbefund(templateParamsEntry.getValue()));
            } else if (templateParamsEntry.getKey().equals("GECCO_Laborbefund")) {
                ehrIds.addAll(handleQueryForGECCO_Diagnose(templateParamsEntry.getValue()));
            }
        }


        /*
        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, bundleProvider.size());
        } else {
            Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
            exchange.getMessage().setBody(bundleProvider.getResources(from, to));
        }
        */

        // TODO: use the subject id instead of the ehr id
        List<IBaseResource> result = ehrIds.stream()
            .map(ehrId -> new Patient().setId(ehrId))
            .collect(Collectors.toList());

        //result.add(new Patient().setId("ID-12345"));
        exchange.getMessage().setBody(result);

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, result.size()); // required if param _summary=count
        } else {
            //throw new NotImplementedOperationException("_summary should be equals to 'count'");
            // FIXME: this is not paginated, could be big
            exchange.getMessage().setBody(result);
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, result.size());
        }
    }

    private void handleReadOperation(Exchange exchange) throws InvalidPayloadException {
        LOG.debug("Execute 'read'/'vread' operation");

        //IIdType id = exchange.getIn().getMandatoryBody(IIdType.class);
        //exchange.getMessage().setBody(resourceDao.read(id, extractRequestDetails(exchange)));
    }

    private boolean isSearchOperation(Exchange exchange) {
        RequestDetails requestDetails = extractRequestDetails(exchange);
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
    }

    private RequestDetails extractRequestDetails(Exchange exchange) {
        RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);
        if (requestDetails == null) {
            throw new IllegalStateException("RequestDetails must not be null");
        }
        return requestDetails;
    }

    private Set<String> findMatchingTemplates(String code) {

        Set<String> templateIds = new HashSet<>();

        Reflections reflections = new Reflections("org.ehrbase.fhirbridge.ehr.opt");
        Set<Class<? extends Enum>> enumClasses = reflections.getSubTypesOf(Enum.class);

        String enumCode;
        boolean foundCode = false;
        String foundPackage;

        for (Class<? extends Enum> enumClass: enumClasses) {

            foundCode = false;

            if (!enumClass.isEnum()) {
                continue;
            }

            for (Object econt : enumClass.getEnumConstants()) {
                Method methodToFind = null;
                try {
                    methodToFind = econt.getClass().getMethod("getCode", (Class<?>[]) null);

                    // calls econt.getCode(), no parameters
                    enumCode = (String) methodToFind.invoke(econt, (Object[]) null);

                    if (code.equals(enumCode)) {
                        foundCode = true;
                        break;
                    }

                    //System.out.println(code); // this should be used for code matching

                    // when the code matches, we need the package where the enum is defined
                    // then remove the '.definition' from the package
                    // then in that package search for the class that implements CompositionEntity
                    // on that class, get the @Template anotation
                    // the value of the annotation will be the template we are looking for

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println("oops 1");
                }
            }

            if (!foundCode) {
                continue;
            }

            // enumClass has a code that matches

            // code for processing the found package

            // get parent package
            foundPackage = enumClass.getPackageName(); // org.ehrbase.fhirbridge.ehr.opt.xxx.definition
            int lastIndex = foundPackage.lastIndexOf(".");
            String parentPackName = foundPackage.substring(0, lastIndex); // org.ehrbase.fhirbridge.ehr.opt.xxx


            // get compo classes in parent package (there should be just one)
            Reflections reflectComposition = new Reflections(parentPackName);
            Set<Class<? extends CompositionEntity>> compoClasses = reflectComposition.getSubTypesOf(CompositionEntity.class);

            // get template annotation
            for (Class<? extends CompositionEntity> compoClass : compoClasses) {

                Template templateAnnotation = compoClass.getAnnotation(Template.class);
                templateIds.add(templateAnnotation.value());
            }
        }

        return templateIds;
    }


    private Set<String> handleQueryForGECCO_Laborbefund(List<HasParamTemplate> params) {

        Set<String> ehrIds = new HashSet<>();

        String aql = "SELECT e/ehr_id/value "+
                "FROM EHR e "+
                "CONTAINS CLUSTER c[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1] ";

        // we know which path to use in the query because all the params are for the 'code' attribute of the same resource type
        switch (params.size()) {
            case 0:
                // error
                break;
            case 1:
                aql += "WHERE c/items[at0024]/value/defining_code/code_string = '"+ params.get(0).getValue() +"'";
                break;
            default:
                String values = String.join(
                    ",",
                    params.stream()
                        .map(HasParamTemplate::getValue)
                        .map(s -> "'" + s + "'")
                        .collect(Collectors.toList())
                );
                aql += "WHERE c/items[at0024]/value/defining_code/code_string matches{"+ values +"}";
                break;
        }

        LOG.info(aql);

        // Execute the AQL
        Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

        List<Record1<String>> results = new ArrayList<>();

        try {
            results = this.openEhrClient.aqlEndpoint().execute(query);

            for (Record1<String> record : results) {

                ehrIds.add(record.value1());
            }
        } catch (Exception e) {
            throw new InternalErrorException("There was a problem retrieving the result", e);
        }

        return ehrIds;
    }

    private Set<String> handleQueryForGECCO_Diagnose(List<HasParamTemplate> params) {

        // openEHR-EHR-EVALUATION.problem_diagnosis.v1
        // /data[at0001]/items[at0002]/value

        Set<String> ehrIds = new HashSet<>();

        String aql = "SELECT e/ehr_id/value "+
                "FROM EHR e "+
                "CONTAINS EVALUATION e[openEHR-EHR-EVALUATION.problem_diagnosis.v1] ";

        // we know which path to use in the query because all the params are for the 'code' attribute of the same resource type
        switch (params.size()) {
            case 0:
                // error
                break;
            case 1:
                aql += "WHERE e/data[at0001]/items[at0002]/value/defining_code/code_string = '"+ params.get(0).getValue() +"'";
                break;
            default:
                String values = String.join(
                    ",",
                    params.stream()
                        .map(HasParamTemplate::getValue)
                        .map(s -> "'" + s + "'")
                        .collect(Collectors.toList())
                );
                aql += "WHERE e/data[at0001]/items[at0002]/value/defining_code/code_string matches{"+ values +"}";
                break;
        }

        LOG.info(aql);

        // Execute the AQL
        Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

        List<Record1<String>> results = new ArrayList<>();

        try {
            results = this.openEhrClient.aqlEndpoint().execute(query);

            for (Record1<String> record : results) {

                ehrIds.add(record.value1());
            }
        } catch (Exception e) {
            throw new InternalErrorException("There was a problem retrieving the result", e);
        }

        return ehrIds;
    }

    /**
     * Represents one code matching a set of templateIds (the same code could be referenced by
     * different paths on different templates)
     */
    private class HasParamTemplate {

        private String targetResource;   // Observation, Encounter, Condition
        private String targetParamName;  // code, date
        private String value;            // code value or value of the single param name
        private Set<String> templateIds; // template_ids matching any of the codes, can be empty if there are no matches or if the paramName is not a code, or multiple if different codes in the list match different templates

        public HasParamTemplate(String targetResource, String targetParamName, String value, Set<String> templateIds) {
            this.targetResource = targetResource;
            this.targetParamName = targetParamName;
            this.value = value;
            this.templateIds = templateIds;
        }

        public String getTargetResource() {
            return targetResource;
        }

        public String getTargetParamName() {
            return targetParamName;
        }

        public String getValue() {
            return value;
        }

        public Set<String> getTemplateIds() {
            return templateIds;
        }
    }
}
