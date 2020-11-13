package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.FhirValidator;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.fhir.common.validation.TerminologyServerValidationSupport;
import org.ehrbase.fhirbridge.fhir.common.validation.TerminologyValidationMode;
import org.hl7.fhir.common.hapi.validation.support.CachingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties(FhirValidationProperties.class)
public class FhirValidationConfiguration {

    private final FhirValidationProperties properties;

    private final ResourcePatternResolver resourceLoader;


    public FhirValidationConfiguration(FhirValidationProperties properties, ResourcePatternResolver resourceLoader) {
        this.properties = properties;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DefaultCreateResourceRequestValidator defaultCreateResourceRequestValidator(FhirContext fhirContext, FhirValidator fhirValidator) {
        return new DefaultCreateResourceRequestValidator(fhirContext, fhirValidator);
    }

    @Bean
    public FhirValidator fhirValidator(FhirContext fhirContext) {
        FhirValidator fhirValidator = fhirContext.newValidator();

        ValidationSupportChain validationSupport = new ValidationSupportChain();
        validationSupport.addValidationSupport(new DefaultProfileValidationSupport(fhirContext));
        validationSupport.addValidationSupport(prePopulatedValidationSupport(fhirContext));

        TerminologyValidationMode mode = properties.getTerminology().getMode();
        if (mode == TerminologyValidationMode.EMBEDDED) {
            validationSupport.addValidationSupport(new CommonCodeSystemsTerminologyService(fhirContext));
            validationSupport.addValidationSupport(new InMemoryTerminologyServerValidationSupport(fhirContext));
        } else if (mode == TerminologyValidationMode.SERVER) {
            validationSupport.addValidationSupport(terminologyServerValidationSupport(fhirContext));
        }

        CachingValidationSupport cachingValidationSupport = new CachingValidationSupport(validationSupport);

        FhirInstanceValidator fhirInstanceValidator = new FhirInstanceValidator(cachingValidationSupport);
        fhirInstanceValidator.setErrorForUnknownProfiles(false);
        fhirInstanceValidator.setNoTerminologyChecks(properties.getTerminology().getMode() == TerminologyValidationMode.NONE);

        fhirValidator.registerValidatorModule(fhirInstanceValidator);

        return fhirValidator;
    }

    private PrePopulatedValidationSupport prePopulatedValidationSupport(FhirContext fhirContext) {
        PrePopulatedValidationSupport prePopulatedValidationSupport = new PrePopulatedValidationSupport(fhirContext);
        IParser parser = fhirContext.newXmlParser();
        try {
            for (Resource resource : resourceLoader.getResources("classpath:/profiles/*")) {
                InputStream inputStream = resource.getInputStream();
                StructureDefinition structureDefinition = parser.parseResource(StructureDefinition.class, inputStream);
                prePopulatedValidationSupport.addStructureDefinition(structureDefinition);
            }
        } catch (IOException e) {
            throw new FhirBridgeException("Initialization failed", e);
        }
        return prePopulatedValidationSupport;
    }

    private TerminologyServerValidationSupport terminologyServerValidationSupport(FhirContext fhirContext) {
        String serverUrl = properties.getTerminology().getServerUrl();
        return new TerminologyServerValidationSupport(fhirContext, fhirContext.newRestfulGenericClient(serverUrl));
    }
}
