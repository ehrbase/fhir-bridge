package org.ehrbase.fhirbridge.service;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.Parameters;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.UriType;
import org.springframework.cache.annotation.Cacheable;

public class TerminologyService {

    private final IGenericClient client;

    public TerminologyService(IGenericClient client) {
        this.client = client;
    }

    @Cacheable(cacheNames = "terminologyDisplays", key = "#system + '_' + #code")
    public String getDisplay(String system, String code) {
        Parameters in = new Parameters();
        in.addParameter(CodeSystem.SP_URL, new UriType(system));
        in.addParameter(CodeSystem.SP_CODE, new StringType(code));

        Parameters out = client.operation()
                .onType(CodeSystem.class)
                .named("validate-code")
                .withParameters(in)
                .execute();

        StringType display = (StringType) out.getParameter("display");
        if (display == null) {
            throw new TerminologyServiceException("Cannot find display for coding: system=" + system + ", code=" + code);
        }

        return display.getValue();
    }
}
