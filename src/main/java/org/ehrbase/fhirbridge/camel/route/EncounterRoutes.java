package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.support.Encounters;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Encounter} resource.
 *
 * @since 1.0.0
 */
@Component
public class EncounterRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        super.configure();

        // 'Create Encounter' route definition
        from("encounter-create:consumer?fhirContext=#fhirContext")
                .onCompletion()
                    .process("auditCreateResourceProcessor")
                .end()
                .process("resourceProfileValidator")
                .to("direct:process-encounter");

        // 'Find Encounter' route definition
        from("encounter-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
                .choice()
                    .when(isSearchOperation())
                        .to("bean:encounterDao?method=search(${body}, ${headers.FhirRequestDetails})")
                        .process("bundleProviderResponseProcessor")
                    .otherwise()
                        .to("bean:encounterDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // Internal routes definition
        from("direct:process-encounter")
                .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method("encounterDao", "create(${body}, ${headers.FhirRequestDetails})"))
                .process("ehrIdLookupProcessor")
                .setHeader(FhirBridgeConstants.PROFILE, method(Encounters.class, "getProfileByKontaktEbene"))
                .choice()
                    .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.KONTAKT_GESUNDHEIT_ABTEILUNG))
                        .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
                        .to("ehr-composition:compositionEndpoint?operation=mergeCompositionEntity")
                        .process("resourceResponseProcessor")
                    .otherwise()
                        .to("bean:fhirResourceConversionService?method=convertDefaultEncounter(${body})")
                        .to("ehr-composition:compositionEndpoint?operation=mergeCompositionEntity")
                        .process("resourceResponseProcessor");

        // @formatter:on
    }
}