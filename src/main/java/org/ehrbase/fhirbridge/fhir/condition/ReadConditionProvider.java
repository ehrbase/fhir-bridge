package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.TokenParam;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Condition;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadConditionProvider extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ReadConditionProvider.class);

    @Read
    public Condition read(@IdParam IIdType id, RequestDetails requestDetails,
                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.trace("Find condition using @Read and id '{}'", id.getIdPart());
        ParameterValue<String> compositionId = new ParameterValue<>("compositionId", id.getIdPart());
        return requestResource(compositionId, null, Condition.class, httpServletRequest, httpServletResponse, requestDetails);
    }

    @Search
    public Condition search(@RequiredParam(name = Condition.SP_IDENTIFIER) TokenParam identifier, RequestDetails requestDetails,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.trace("Find condition using @Search and identifier '{}'", identifier.getValue());
        ParameterValue<String> compositionId = new ParameterValue<>("compositionId", identifier.getValue());
        return requestResource(compositionId, null, Condition.class, httpServletRequest, httpServletResponse, requestDetails);
    }
}
