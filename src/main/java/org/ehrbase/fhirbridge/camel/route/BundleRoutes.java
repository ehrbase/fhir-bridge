package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.processor.BundleResponseProcessor;
import org.ehrbase.fhirbridge.fhir.bundle.DiagnosticReportLabConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.validator.BloodGasPanelBundleValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.DiagnosticReportLabBundleValidator;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.support.Bundles;
import org.springframework.stereotype.Component;

@Component
public class BundleRoutes extends RouteBuilder {

    private final BundleResponseProcessor bundleResponseProcessor = new BundleResponseProcessor();


    @Override
    public void configure() {
        // @formatter:off
        from("fhir-provide-bundle:consumer?fhirContext=#fhirContext")
                .setHeader(FhirBridgeConstants.PROFILE, method(Bundles.class, "getTransactionProfile"))
                .choice()
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.BLOOD_GAS_PANEL))
                .to("direct:process-blood-gas-panel-bundle")
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.DIAGNOSTIC_REPORT_LAB))
                .to("direct:process-diagnostic-report-lab-bundle")
                .otherwise()
                .throwException(new UnprocessableEntityException("Unsupported transaction: provided Bundle should have a resource that " +
                        "uses on of the following profiles: " + Profile.BLOOD_GAS_PANEL.getUri() + ", " + Profile.DIAGNOSTIC_REPORT_LAB.getUri()));

        from("direct:process-blood-gas-panel-bundle")
                .bean(BloodGasPanelBundleValidator.class)
                .bean(BloodGasPanelConverter.class, "convert")
                .to("direct:process-observation")
                .process(bundleResponseProcessor);

        from("direct:process-diagnostic-report-lab-bundle")
                .bean(DiagnosticReportLabBundleValidator.class)
                .bean(DiagnosticReportLabConverter.class, "convert")
                .to("direct:process-diagnostic-report")
                .process(bundleResponseProcessor);
        // @formatter:on
    }
}
