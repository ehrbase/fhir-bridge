package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IpfFhirRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("fhir://service?audit=false")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()))
            .to("log:IPF-FHIR-LOGGER?showAll=true");
        // @formatter:on
    }

    @Bean
    public CustomFhirComponent<GenericFhirAuditDataset> fhir(FhirTransactionConfiguration<GenericFhirAuditDataset> transactionConfiguration) {
        CustomFhirComponent<GenericFhirAuditDataset> fhir = new CustomFhirComponent<>();
        fhir.setFhirTransactionConfiguration(transactionConfiguration);
        return fhir;
    }

    @Bean
    public FhirTransactionConfiguration<GenericFhirAuditDataset> transactionConfiguration() {
        return new FhirTransactionConfiguration<>(
                "fhir",
                "DiagnosticReport Provider",
                false,
                null,
                null,
                FhirVersionEnum.R4,
                new DiagnosticReportProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION
        );
    }
}
