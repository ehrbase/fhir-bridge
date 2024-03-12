package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import org.apache.http.client.HttpClient;
import org.ehrbase.fhirbridge.exception.InputOutputException;
import org.ehrbase.fhirbridge.fhir.common.validation.TerminologyValidationMode;
import org.hl7.fhir.common.hapi.validation.support.CachingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.RemoteTerminologyServiceValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES;

/**
 * {@link Configuration Configuration} for FHIR validation.
 */
@Configuration
@EnableConfigurationProperties(FhirValidationProperties.class)
public class FhirValidationConfiguration {

    private final FhirContext fhirContext = FhirContext.forR4();

    private final FhirValidationProperties properties;

    private final ApplicationContext applicationContext;

    public FhirValidationConfiguration(FhirValidationProperties properties, ApplicationContext applicationContext, HttpClient httpClient) {
        this.properties = properties;
        this.applicationContext = applicationContext;
        fhirContext.getRestfulClientFactory().setHttpClient(httpClient);
    }

    @Bean
    public RequestValidatingInterceptor requestValidatingInterceptor() {
        RequestValidatingInterceptor requestValidatingInterceptor = new RequestValidatingInterceptor();
        requestValidatingInterceptor.setFailOnSeverity(properties.getFailedOnSeverity());
        requestValidatingInterceptor.addValidatorModule(instanceValidator());
        return requestValidatingInterceptor;
    }

    private FhirInstanceValidator instanceValidator() {
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(validationSupport());
        instanceValidator.setAnyExtensionsAllowed(properties.isAnyExtensionsAllowed());
        instanceValidator.setErrorForUnknownProfiles(properties.isErrorForUnknownProfiles());
        instanceValidator.setNoTerminologyChecks(!isTerminologyValidationEnabled());
        return instanceValidator;
    }

    public IValidationSupport validationSupport() {
        ValidationSupportChain validationSupportChain = new ValidationSupportChain();

        // Validates core structure definitions
        DefaultProfileValidationSupport defaultProfileValidationSupport = new DefaultProfileValidationSupport(fhirContext);
        defaultProfileValidationSupport.fetchAllStructureDefinitions();
        defaultProfileValidationSupport.fetchCodeSystem("");
        validationSupportChain.addValidationSupport(defaultProfileValidationSupport);

        // Validates custom profiles (loaded from classpath)
        PrePopulatedValidationSupport prePopulatedValidationSupport = new PrePopulatedValidationSupport(fhirContext);
        IParser parser = fhirContext.newXmlParser();
        try {
            for (Resource resource : applicationContext.getResources("classpath:/profiles/*")) {
                StructureDefinition profile = parser.parseResource(StructureDefinition.class, resource.getInputStream());
                if (properties.isOptionalIdentifier()) {
                    modifyProfile(profile);
                }
                prePopulatedValidationSupport.addStructureDefinition(profile);
            }
        } catch (IOException e) {
            throw new InputOutputException(FhirValidationConfiguration.class, AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES);
        }
        validationSupportChain.fetchAllStructureDefinitions();
        defaultProfileValidationSupport.fetchCodeSystem("");
        validationSupportChain.addValidationSupport(prePopulatedValidationSupport);

        // Validates terminology: CodeSystems and ValueSets (using the internal and/or remote terminology service)
        if (isTerminologyValidationEnabled()) {
            TerminologyValidationMode mode = properties.getTerminology().getMode();
            if (mode == TerminologyValidationMode.REMOTE || mode == TerminologyValidationMode.MIXED) {
                RemoteTerminologyServiceValidationSupport remoteTerminologyServerValidationSupport = new RemoteTerminologyServiceValidationSupport(fhirContext);
                remoteTerminologyServerValidationSupport.setBaseUrl(properties.getTerminology().getServerBaseUrl());
                validationSupportChain.addValidationSupport(remoteTerminologyServerValidationSupport);
            }
            if (mode == TerminologyValidationMode.INTERNAL || mode == TerminologyValidationMode.MIXED) {
                validationSupportChain.addValidationSupport(new InMemoryTerminologyServerValidationSupport(fhirContext));
                validationSupportChain.addValidationSupport(new CommonCodeSystemsTerminologyService(fhirContext));
            }
        }

        return new CachingValidationSupport(validationSupportChain);
    }

    private void modifyProfile(StructureDefinition profile) {
        Predicate<? super ElementDefinition> modPredicate = e -> e.hasPath() && e.getPath().endsWith(".identifier") && e.getMin() > 0;
        Consumer<? super ElementDefinition> modAction = e -> e.setMin(0);

        profile.getSnapshot().getElement().stream().filter(modPredicate).forEach(modAction);
        profile.getDifferential().getElement().stream().filter(modPredicate).forEach(modAction);
    }

    private boolean isTerminologyValidationEnabled() {
        return properties.getTerminology().getMode() != TerminologyValidationMode.NONE;
    }
}
