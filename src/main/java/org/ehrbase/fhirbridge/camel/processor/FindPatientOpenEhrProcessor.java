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
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.HasParam;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Processor;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Patient;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Test to find Patient in openEHR.
 */
@Component(FindPatientOpenEhrProcessor.BEAN_ID)
public class FindPatientOpenEhrProcessor implements FhirRequestProcessor {

    public static final String BEAN_ID = "findPatientOpenEhrProcessor";

    private static final Logger LOG = LoggerFactory.getLogger(FindPatientOpenEhrProcessor.class);

    private final OpenEhrClient openEhrClient;

    public FindPatientOpenEhrProcessor(OpenEhrClient openEhrClient) {
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

    private void handleSearchOperation(Exchange exchange) {
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

        # if the same parameter name appears twice in the request, here we have one name with two values
        # for the 'code' attribute each value could represent a single or list of values, in both cases it is a string that should be parsed as CSV
        _has:Observation:patient:code:
          2160-0,14682-9,3091-6,22664-7
          1743-4,1742-6,30239-8,1920-8,88112-8

        _has:Condition:patient:code:
          C34.0,C34.1,C34.2,C34.3,C34.8,C34.9
        */
        RequestDetails request = getRequestDetails(exchange);
        Map<String, String[]> inParams = request.getParameters();

        // Gather all the information for the parameters including the templateId lookup result for the code params
        List<HasParamTemplate> outParams = new ArrayList<>();

        // aux structure to simplify creation of AQL queries based on templates
        // templateId => list of params which values will be included in the same AQL query
        // because of how this is constructed, only parameters for the 'code' attribute will
        // be in this map, so based on the template, resource type and attribute name (code),
        // we know which path to use in the AQL
        Map<String, List<HasParamTemplate>> templateParamMap = new LinkedHashMap<>();

        // Filters over patient attributes
        Map<String, String[]> patientParams = new HashMap<>();

        for (String paramName : inParams.keySet()) {

            // avoid processing _summary
            if (paramName.startsWith("_summary")) {

                // this will be handled below by the headers of the camel message
                // paramValues[0].equals("count") means we should return only the count
                continue;
            }

            // paramName: _has:Encounter:patient:date or _summary or _has:Encounter:patient:date
            String[] paramValues = inParams.get(paramName); // multiple values per param name, each value could be a list if the attribute in the _has is 'code'

            if (paramName.startsWith("_has")) {

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

                            templateIds = this.findMatchingTemplates(code); // could be empty if there are no mappings for the code

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

                            for (String templateId : templateIds) {

                                // accumulates params for template based queries in templateParamMap
                                // the rest are accumulated in outParams
                                templateParams = templateParamMap.get(templateId);

                                if (templateParams == null) { // initialize the list for the current templateId key

                                    templateParams = new ArrayList<>();
                                    templateParams.add(outParam);
                                    templateParamMap.put(templateId, templateParams);

                                } else {

                                    templateParams.add(outParam);
                                }
                            }

                            // outParams has all the params that are not going to be used in queries based on templateIds
                            if (templateIds.isEmpty()) {
                                //outParams.add(outParam);
                                LOG.warn("No matching mapping for code '"+ code +"' (won't be used to query)");
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

            } else if (paramName.equals("birthdate")) {

                patientParams.put(paramName, paramValues);

            } else if (paramName.equals("gender")) {

                patientParams.put(paramName, paramValues);

            } else {

                LOG.warn("Param "+ paramName +" not supported");
            }
        }

        // Execute one query for each processed parameter (out params or template params joined by same templateID)
        // For a result to be counted, each of those queries should retrieve the same value (subjectId)
        // Then the first result is kept, and the rest are intersected with that one, creating an AND processing
        // If any result is empty, there is no need to execute the following queries, we know the total result will be empty/zero

        int totalQueries = outParams.size() + templateParamMap.size();
        int executedQueries = 0;

        // execute queries based on the processed parameters and intersect results
        // note for unsupported parameters, or for codes that don't have a matching template, no AQL will be
        // executed without throwing an exception
        Set<String> subjectIds = new HashSet<>();
        Set<String> tmpResult;
        boolean emptyResult = false;


        // TEST
        if (patientParams.size() > 0) {
            try {
                handleQueryForGECCO_Personendaten(patientParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // process queries not based on templates
        for (HasParamTemplate outParam : outParams) {

            // TODO: execute queries based on resource type and attribute name, no template lookupt here because
            // the attribute is not 'code', so we need to search manually for the possible matching templates
            // and hardcode them here.
            //
            // Encounter => Patientenaufenthalt (openEHR-EHR-COMPOSITION.event_summary.v0)
            // Encounter => Stationärer Versorgungsfall (openEHR-EHR-COMPOSITION.fall.v1)
            //System.out.println(outParam.getValue());

            if (outParam.getTargetResource().equals("Encounter")) {

                // NOTE: this will search by one date not a date range, since it accepts one date param value at a time,
                // to search by a date range, the outParams for the Encounter should be grouped together
                tmpResult = handleQueryForEncounter(outParam);

                LOG.info("Executed Query: "+ outParam.targetParamName);

                if (tmpResult.isEmpty()) {
                    executedQueries++;
                    emptyResult = true;
                    break;
                }

                if (executedQueries == 0) {
                    subjectIds.addAll(tmpResult);
                } else {
                    subjectIds.retainAll(tmpResult); // does an intersection ~ AND
                }
                executedQueries++;
            } else {
                LOG.info("Ignoring param "+ outParam.targetParamName);
            }
        }

        if (!emptyResult) {

            // process queries based on templates
            for (Map.Entry<String, List<HasParamTemplate>> templateParamsEntry : templateParamMap.entrySet()) {

                if (templateParamsEntry.getKey().equals("GECCO_Laborbefund")) {

                    tmpResult = handleQueryForGECCO_Laborbefund(templateParamsEntry.getValue());

                    LOG.info("Executed Query For Template: "+ templateParamsEntry.getKey());

                    if (tmpResult.isEmpty()) {
                        executedQueries++;
                        emptyResult = true;
                        break;
                    }
                    if (executedQueries == 0) {
                        subjectIds.addAll(tmpResult);
                    } else {
                        subjectIds.retainAll(tmpResult); // does an intersection ~ AND
                    }
                    executedQueries++;

                } else if (templateParamsEntry.getKey().equals("GECCO_Laborbefund")) {

                    tmpResult = handleQueryForGECCO_Diagnose(templateParamsEntry.getValue());

                    LOG.info("Executed Query For Template: "+ templateParamsEntry.getKey());

                    if (tmpResult.isEmpty()) {
                        executedQueries++;
                        emptyResult = true;
                        break;
                    }
                    if (executedQueries == 0) {
                        subjectIds.addAll(tmpResult);
                    } else {
                        subjectIds.retainAll(tmpResult); // does an intersection ~ AND
                    }
                    executedQueries++;
                }
            }
        }

        LOG.info("Executed Queries: "+ executedQueries);
        LOG.info("Total Queries: "+ totalQueries);
        LOG.info("Empty Result: "+ emptyResult);
        LOG.info("Subject IDs: "+ subjectIds.toString());

        // Create bundle result of Patient just with the subjectId from openEHR
        List<IBaseResource> result = subjectIds.stream()
                .map(subjectId -> new Patient().setId(subjectId))
                .collect(Collectors.toList());

        //result.add(new Patient().setId("ID-12345"));
        exchange.getMessage().setBody(result);

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, result.size()); // required if param _summary=count
        } else {
            // FIXME: this is not paginated, could be big. Check how the pagination params are read.
            // Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            // Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
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
        RequestDetails requestDetails = getRequestDetails(exchange);
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
    }

    private Set<String> findMatchingTemplates(String code) {

        Set<String> templateIds = new HashSet<>();

        Reflections reflections = new Reflections("org.ehrbase.fhirbridge.ehr.opt");
        Set<Class<? extends Enum>> enumClasses = reflections.getSubTypesOf(Enum.class);

        String enumCode;
        boolean foundCode = false;
        String foundPackage;

        for (Class<? extends Enum> enumClass : enumClasses) {

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
                    LOG.error(e.getMessage());
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

        Set<String> subjectIds = new HashSet<>();

        String aql = "SELECT e/ehr_status/subject/external_ref/id/value " +
                "FROM EHR e " +
                "CONTAINS CLUSTER c[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1] ";

        // we know which path to use in the query because all the params are for the 'code' attribute of the same resource type
        switch (params.size()) {
            case 0:
                // error
                break;
            case 1:
                aql += "WHERE c/items[at0024]/value/defining_code/code_string = '" + params.get(0).getValue() + "'";
                break;
            default:
                String values = String.join(
                        ",",
                        params.stream()
                                .map(HasParamTemplate::getValue)
                                .map(s -> "'" + s + "'")
                                .collect(Collectors.toList())
                );
                aql += "WHERE c/items[at0024]/value/defining_code/code_string matches {" + values + "}";
                break;
        }

        LOG.info(aql);

        // execute query only if there is a matching supported param
        if (aql.contains("WHERE")) {

            Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

            List<Record1<String>> results = new ArrayList<>();

            try {
                results = this.openEhrClient.aqlEndpoint().execute(query);

                for (Record1<String> record : results) {
                    subjectIds.add(record.value1());
                }
            } catch (Exception e) {
                throw new InternalErrorException("There was a problem retrieving the result", e);
            }
        }

        return subjectIds;
    }

    private Set<String> handleQueryForGECCO_Diagnose(List<HasParamTemplate> params) {

        // openEHR-EHR-EVALUATION.problem_diagnosis.v1
        // /data[at0001]/items[at0002]/value

        Set<String> subjectIds = new HashSet<>();

        String aql = "SELECT e/ehr_status/subject/external_ref/id/value " +
                "FROM EHR e " +
                "CONTAINS EVALUATION e[openEHR-EHR-EVALUATION.problem_diagnosis.v1] ";

        // we know which path to use in the query because all the params are for the 'code' attribute of the same resource type
        switch (params.size()) {
            case 0:
                // error
                break;
            case 1:
                aql += "WHERE e/data[at0001]/items[at0002]/value/defining_code/code_string = '" + params.get(0).getValue() + "'";
                break;
            default:
                String values = String.join(
                        ",",
                        params.stream()
                                .map(HasParamTemplate::getValue)
                                .map(s -> "'" + s + "'")
                                .collect(Collectors.toList())
                );
                aql += "WHERE e/data[at0001]/items[at0002]/value/defining_code/code_string matches {" + values + "}";
                break;
        }

        LOG.info(aql);

        // Execute the AQL
        Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

        List<Record1<String>> results = new ArrayList<>();

        try {
            results = this.openEhrClient.aqlEndpoint().execute(query);

            for (Record1<String> record : results) {
                subjectIds.add(record.value1());
            }
        } catch (Exception e) {
            throw new InternalErrorException("There was a problem retrieving the result", e);
        }

        return subjectIds;
    }

    private Set<String> handleQueryForEncounter(HasParamTemplate param) {

        Set<String> subjectIds = new HashSet<>();

        // in the query, the hardcoded archetype IDs are the ones that are mapped from fhir encounters
        String aql = "SELECT e/ehr_status/subject/external_ref/id/value " +
                "FROM EHR e " +
                "CONTAINS (COMPOSITION c1[openEHR-EHR-COMPOSITION.event_summary.v0] OR COMPOSITION c2[openEHR-EHR-COMPOSITION.fall.v1]) ";

        // processing _has:Encounter:patient:date=2020
        if (param.targetParamName.equals("date")) {

            String dateValue = param.getValue();
            String dateQuery = "";

            /*
            if (dateValue.length() == 4) { // year only
                dateQuery += "WHERE c1/context/start_time/value >= '" + dateValue + "-01-01' AND c1/context/start_time/value <= '" + dateValue + "-12-31' ";
                dateQuery += "OR c2/context/start_time/value >= '" + dateValue + "-01-01' AND c2/context/start_time/value <= '" + dateValue + "-12-31' ";
            }
            */

            try {
                dateQuery += "WHERE ("+ aqlDateConditions("c1/context/start_time/value", new String[]{dateValue}) +") OR ("+
                             aqlDateConditions("c2/context/start_time/value", new String[]{dateValue}) +")";
            } catch (Exception e) {
                e.printStackTrace();
            }

            aql += dateQuery;
        } else {
            LOG.warn("Parameter name "+ param.targetParamName +" for Encounter is not supported yet, only 'date' is supported");
        }

        LOG.info(aql);

        // execute query only if there is a matching supported param
        if (aql.contains("WHERE")) {

            // Execute the AQL
            Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

            List<Record1<String>> results = new ArrayList<>();

            try {
                results = this.openEhrClient.aqlEndpoint().execute(query);

                for (Record1<String> record : results) {
                    subjectIds.add(record.value1());
                }
            } catch (Exception e) {
                throw new InternalErrorException("There was a problem retrieving the result", e);
            }
        }

        return subjectIds;
    }

    // query over DoB and gender
    private Set<String> handleQueryForGECCO_Personendaten(Map<String, String[]> patientParams) throws Exception {

        Set<String> subjectIds = new HashSet<>();

        String aql = "SELECT e/ehr_status/subject/external_ref/id/value FROM EHR e ";

        if (patientParams.containsKey("birthdate") && patientParams.containsKey("gender")) {
            aql += "CONTAINS (CLUSTER cl1[openEHR-EHR-CLUSTER.person_birth_data_iso.v0] AND EVALUATION ev[openEHR-EHR-EVALUATION.gender.v1]) ";
        } else if (patientParams.containsKey("birthdate")) {
            aql += "CONTAINS CLUSTER cl1[openEHR-EHR-CLUSTER.person_birth_data_iso.v0] ";
        } else if (patientParams.containsKey("gender")) {
            aql += "CONTAINS EVALUATION ev[openEHR-EHR-EVALUATION.gender.v1] ";
        }

        /*
        cl1/items[at0001]/value //dob

        ev/data[at0002]/items[at0022]/value // administrative gender
        */

        String where = "WHERE";

        String[] values;
        for (String paramName : patientParams.keySet()) {

            values = patientParams.get(paramName);

            if (paramName.equals("birthdate")) {
                if (where.length() > 5) {
                    where += " AND ";
                }
                where += " "+ aqlDateConditions("cl1/items[at0001]/value", values);

            } else if (paramName.equals("gender")) {
                if (values.length > 0) {
                    if (where.length() > 5) {
                        where += " AND ";
                    }
                    where += " ev/data[at0002]/items[at0022]/value = '" + values[0] +"'"; // TODO: check if the template uses codes
                }
            }
        }

        if (where.length() > 5) {
            aql += where;
        }

        LOG.info(aql);

        // execute query only if there is a matching supported param
        if (aql.contains("WHERE")) {

            // Execute the AQL
            Query<Record1<String>> query = Query.buildNativeQuery(aql, String.class);

            List<Record1<String>> results = new ArrayList<>();

            try {
                results = this.openEhrClient.aqlEndpoint().execute(query);

                for (Record1<String> record : results) {
                    subjectIds.add(record.value1());
                }
            } catch (Exception e) {
                throw new InternalErrorException("There was a problem retrieving the result", e);
            }
        }

        return subjectIds;
    }

    /**
     *
     * @param attributePath
     * @param paramValues array of 'ge1981', 'lt2021', can only have 1 or 2 values, if one prefix is 'equals', there should be only one value. For the other previxes, one or two values represent a range.
     * @return
     */
    private String aqlDateConditions(String attributePath, String[] paramValues) throws Exception {

        if (paramValues.length == 0) {
            throw new Exception("paramValues can't be empty");
        }
        if (paramValues.length > 2) {
            throw new Exception("paramValues can't have more than 2 values");
        }

        String condition = "";

        DateParam parsedDateParam;
        String operator = "";
        String operand = "";
        String value = "";

        for (int i = 0; i < paramValues.length; i++) {

            parsedDateParam = new DateParam(paramValues[i]);
            value = parsedDateParam.getValueAsString();

            // no prefix is 'equals'
            if (parsedDateParam.getPrefix() == null) {

                if (value.length() == 4) { // year only
                    condition = attributePath +" >= '"+ value +"-01-01' AND "+ attributePath +" <= '"+ value +"-12-31'";
                }

            } else {
                switch (parsedDateParam.getPrefix()) {
                    case EQUAL: // FIXME: previx null should also match equals
                        // for equal to value year, the range for that year is created
                        if (value.length() == 4) { // year only
                            condition = attributePath + " >= '" + value + "-01-01' AND " + attributePath + " <= '" + value + "-12-31'";
                        }
                        break;
                    case GREATERTHAN:
                        operator = ">";
                        if (value.length() == 4) { // year only
                            operand = value + "-01-01";
                        }
                        break;
                    case GREATERTHAN_OR_EQUALS:
                        operator = ">=";
                        if (value.length() == 4) { // year only
                            operand = value + "-01-01";
                        }
                        break;
                    case LESSTHAN:
                        operator = "<";
                        if (value.length() == 4) { // year only
                            operand = value + "-12-31";
                        }
                        break;
                    case LESSTHAN_OR_EQUALS:
                        operator = "<=";
                        if (value.length() == 4) { // year only
                            operand = value + "-12-31";
                        }
                        break;
                    default:
                        throw new InternalErrorException("date param operator " + parsedDateParam.getPrefix().name() + " not supported");
                }
            }

            if (i == 0) {
                if (condition.length() > 0) {
                    break; // this will return the condition for the equals prefix
                }
                condition = attributePath +" "+ operator +" '"+ operand +"'";
            } else {
                condition += " AND "+ attributePath +" "+ operator +" '"+ operand +"'";
            }
        }

        return condition;
    }

    /**
     * Represents one code matching a set of templateIds (the same code could be referenced by
     * different paths on different templates)
     */
    private static class HasParamTemplate {

        private final String targetResource;   // Observation, Encounter, Condition
        private final String targetParamName;  // code, date
        private final String value;            // code value or single value of the param name
        private final Set<String> templateIds; // template_ids matching any of the codes, can be empty if there are no matches or if the paramName is not a code, or multiple if different codes in the list match different templates

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