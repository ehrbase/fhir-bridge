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

package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.BundleResponseProcessor;
import org.ehrbase.fhirbridge.camel.processor.ITI65Processor;
import org.ehrbase.fhirbridge.fhir.bundle.converter.AntiBodyPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.DiagnosticReportLabConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCAppProDatenBundleConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCSensordatenActivityBundleConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.UCCSensordatenVitalSignsBundleConverter;
import org.ehrbase.fhirbridge.fhir.bundle.converter.VirologischerBefundConverter;
import org.ehrbase.fhirbridge.fhir.bundle.validator.AntiBodyPanelBundleValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.BloodGasPanelBundleValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.DiagnosticReportLabBundleValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.Iti65BundleValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.UCCAppProDatenValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.UCCSensorDatenValidator;
import org.ehrbase.fhirbridge.fhir.bundle.validator.VirologischerBefundBundleValidator;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.support.Bundles;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of {@link RouteBuilder} that configures the route definitions for transaction.
 *
 * @since 1.0.0
 */
@Component
@SuppressWarnings("java:S1192")
public class TransactionRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("bundle-provide:consumer?fhirContext=#fhirContext")
                .setHeader(CamelConstants.PROFILE, method(Bundles.class, "getTransactionProfile"))
                .choice()
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.ITI65))
                        .to("direct:processITI65")
                .otherwise()
                .to("direct:process-bundle");

        from("direct:processITI65")
                .bean(Iti65BundleValidator.class)
                .bean(ITI65Processor.class)
                .choice()
                    .when(header(CamelConstants.PROFILE).isEqualTo(Optional.empty())) // If no Bundle is contained within MHD
                        .to("direct:provideResource")
                        .process(BundleResponseProcessor.BEAN_ID)
                .otherwise()
                    .to("direct:process-bundle");

        from("direct:process-bundle")
                .choice()
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.ANTI_BODY_PANEL))
                        .bean(AntiBodyPanelBundleValidator.class)
                        .bean(AntiBodyPanelConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.BLOOD_GAS_PANEL))
                        .bean(BloodGasPanelBundleValidator.class)
                        .bean(BloodGasPanelConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.DIAGNOSTIC_REPORT_LAB))
                        .bean(DiagnosticReportLabBundleValidator.class)
                        .bean(DiagnosticReportLabConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.VIROLOGISCHER_BEFUND))
                        .bean(VirologischerBefundBundleValidator.class)
                        .bean(VirologischerBefundConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.UCC_SENSORDATEN_STEPS))
                        .bean(UCCSensorDatenValidator.class)
                        .bean(UCCSensordatenActivityBundleConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.UCC_SENSORDATEN_VITALSIGNS))
                        .bean(UCCSensorDatenValidator.class)
                        .bean(UCCSensordatenVitalSignsBundleConverter.class, "convert")
                    .when(header(CamelConstants.PROFILE).isEqualTo(Profile.UCC_APP_PRO_DATEN))
                        .bean(UCCAppProDatenValidator.class)
                        .bean(UCCAppProDatenBundleConverter.class, "convert")
                    .otherwise()
                    .throwException(new UnprocessableEntityException("Unsupported transaction: provided Bundle should have a resource that " +
                            "uses on of the following profiles: " +
                            Profile.BLOOD_GAS_PANEL.getUri() +
                            ", " + Profile.DIAGNOSTIC_REPORT_LAB.getUri() +
                            ", " + Profile.ANTI_BODY_PANEL.getUri() +
                            ", " + Profile.VIROLOGISCHER_BEFUND.getUri() +
                            ", " + Profile.UCC_SENSORDATEN_STEPS.getUri() +
                            ", " + Profile.UCC_SENSORDATEN_VITALSIGNS.getUri() +
                            ", " + Profile.UCC_APP_PRO_DATEN.getUri()
                    ))
                    .end()
                    .to("direct:provideResource")
                    .process(BundleResponseProcessor.BEAN_ID);
        // @formatter:on
    }
}


