package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.parser.IParser;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
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

    private final FhirContext fhirContext;

    private final ResourcePatternResolver resourceLoader;

    private final ApplicationContext applicationContext;


    public FhirValidationConfiguration(FhirValidationProperties properties, FhirContext fhirContext, ResourcePatternResolver resourceLoader, ApplicationContext applicationContext) {
        this.properties = properties;
        this.fhirContext = fhirContext;
        this.resourceLoader = resourceLoader;
        this.applicationContext = applicationContext;
    }

    @Bean
    public DefaultCreateResourceRequestValidator defaultCreateResourceRequestValidator(FhirContext fhirContext) {
        ValidationSupportChain validationSupport = new ValidationSupportChain();
        validationSupport.addValidationSupport(new DefaultProfileValidationSupport(fhirContext));
        validationSupport.addValidationSupport(prePopulatedValidationSupport());

        DefaultCreateResourceRequestValidator defaultCreateResourceRequestValidator = new DefaultCreateResourceRequestValidator(fhirContext);

        return defaultCreateResourceRequestValidator;
    }

//    @Bean
//    public FhirValidator fhirValidator(FhirContext fhirContext) {
//        Collection<IValidationSupport> validationSupports = applicationContext.getBeansOfType(IValidationSupport.class).values();
//        ValidationSupportChain validationSupport = new ValidationSupportChain();
//        validationSupport.addValidationSupport(new DefaultProfileValidationSupport(fhirContext));
//        validationSupport.addValidationSupport(prePopulatedValidationSupport());
//
//        FhirInstanceValidator fhirInstanceValidator = new FhirInstanceValidator(validationSupport);
//        fhirInstanceValidator.setErrorForUnknownProfiles(false);
//        fhirInstanceValidator.setNoTerminologyChecks(properties.getTerminology().getMode() == TerminologyValidationMode.NONE);
//        FhirValidator fhirValidator = fhirContext.newValidator();
//        fhirValidator.registerValidatorModule(fhirInstanceValidator);
//        return fhirValidator;
//    }

    //    @Bean(name = "fhirBridgeValidator")
//    public FhirValidator fhirValidator(FhirContext fhirContext, ResourcePatternResolver resourceLoader,
////                                       DefaultProfileValidationSupport defaultProfileValidationSupport,
////                                       PrePopulatedValidationSupport prePopulatedValidationSupport,
//                                       @Autowired(required = false) TerminologyServerValidationSupport terminologyServerValidationSupport) {
//
//        ValidationSupportChain validationSupport = new ValidationSupportChain();
//        validationSupport.addValidationSupport(new DefaultProfileValidationSupport(fhirContext));
//        PrePopulatedValidationSupport prePopulatedValidationSupport = new PrePopulatedValidationSupport(fhirContext);
//        IParser parser = fhirContext.newXmlParser();
//        try {
//            for (Resource resource : resourceLoader.getResources("classpath:/profiles/*")) {
//                InputStream inputStream = resource.getInputStream();
//                StructureDefinition structureDefinition = parser.parseResource(StructureDefinition.class, inputStream);
//                prePopulatedValidationSupport.addStructureDefinition(structureDefinition);
//            }
//        } catch (IOException e) {
//            throw new FhirBridgeException("Initialization failed", e);
//        }
//        validationSupport.addValidationSupport(prePopulatedValidationSupport);
//
//        TerminologyValidationMode mode = properties.getTerminology().getMode();
//        if (mode == TerminologyValidationMode.EMBEDDED) {
//            validationSupport.addValidationSupport(new CommonCodeSystemsTerminologyService(fhirContext));
//            validationSupport.addValidationSupport(new InMemoryTerminologyServerValidationSupport(fhirContext));
//        } else if (mode == TerminologyValidationMode.SERVER) {
//            validationSupport.addValidationSupport(terminologyServerValidationSupport);
//        }
//
////        CachingValidationSupport cachingValidationSupport = new CachingValidationSupport(validationSupport);
//
//        FhirInstanceValidator fhirInstanceValidator = new FhirInstanceValidator(validationSupport);
//        fhirInstanceValidator.setBestPracticeWarningLevel(IResourceValidator.BestPracticeWarningLevel.Hint);
//        fhirInstanceValidator.setErrorForUnknownProfiles(true);
//        fhirInstanceValidator.setNoTerminologyChecks(properties.getTerminology().getMode() == TerminologyValidationMode.NONE);
//
//        FhirValidator fhirValidator = fhirContext.newValidator();
//        fhirValidator.registerValidatorModule(fhirInstanceValidator);
//        return fhirValidator;
//    }
//
    private PrePopulatedValidationSupport prePopulatedValidationSupport() {
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
//
//    @Bean
//    @ConditionalOnProperty(name = "fhir-bridge.fhir.validation.terminology.mode", havingValue = "server")
//    public TerminologyServerValidationSupport terminologyServerValidationSupport(FhirContext fhirContext) {
//        String serverUrl = properties.getTerminology().getServerUrl();
//        return new TerminologyServerValidationSupport(fhirContext, fhirContext.newRestfulGenericClient(serverUrl));
//    }
}
