package org.ehrbase.fhirbridge.fhir.common.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.ConceptValidationOptions;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.context.support.ValidationSupportContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.util.ParametersUtil;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBaseParameters;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.Parameters;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.ValueSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
public class TerminologyServerValidationSupport implements IValidationSupport, MessageSourceAware {

    private final Logger LOG = LoggerFactory.getLogger(TerminologyServerValidationSupport.class);

    private final FhirContext context;

    private final IGenericClient client;

    private MessageSourceAccessor messages;

    public TerminologyServerValidationSupport(FhirContext context, IGenericClient client) {
        Assert.notNull(context, "FhirContext must not be null");
        Assert.notNull(client, "IGenericClient must not be null");
        this.context = context;
        this.client = client;
    }

    @Cacheable(cacheNames = "validateCodeInValueSet", key = "#theCodeSystem + '_' + #theCode + '_'+ #theValueSet.url")
    @Override
    public CodeValidationResult validateCodeInValueSet(ValidationSupportContext theValidationSupportContext, ConceptValidationOptions theOptions,
                                                       String theCodeSystem, String theCode, String theDisplay, @Nonnull IBaseResource theValueSet) {

        String valueSetUrl = ((ValueSet) theValueSet).getUrl();
        if (!theOptions.isInferSystem()) {
            LOG.debug("Perform '/ValueSet/$validate-code' operation: system={}, code={}, url={}", theCodeSystem, theCode, valueSetUrl);

            IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
            ParametersUtil.addParameterToParametersUri(getFhirContext(), requestParams, "system", theCodeSystem);
            ParametersUtil.addParameterToParametersString(getFhirContext(), requestParams, "code", theCode);
            ParametersUtil.addParameterToParameters(getFhirContext(), requestParams, "valueSet", theValueSet);

            Parameters responseParams = client
                    .operation()
                    .onType(ValueSet.class)
                    .named("validate-code")
                    .withParameters(requestParams)
                    .returnResourceType(Parameters.class)
                    .execute();

            BooleanType result = (BooleanType) responseParams.getParameter("result");
            if (result.booleanValue()) {
                StringType display = (StringType) responseParams.getParameter("display");
                return new CodeValidationResult()
                        .setCode(theCode)
                        .setDisplay(display.getValue());
            } else {
                return new CodeValidationResult()
                        .setSeverity(IssueSeverity.WARNING)
                        .setMessage(messages.getMessage("validation.terminology.validateCode", new Object[]{theCode, theCodeSystem, valueSetUrl}));
            }
        } else {
            LOG.debug("Perform '/ValueSet/$expand' operation: url={}", valueSetUrl);

            IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
            ParametersUtil.addParameterToParameters(getFhirContext(), requestParams, "valueSet", theValueSet);

            ValueSet valueSet = client
                    .operation()
                    .onType(ValueSet.class)
                    .named("expand")
                    .withParameters(requestParams)
                    .returnResourceType(ValueSet.class)
                    .execute();

            ValueSet.ValueSetExpansionComponent expansion = valueSet.getExpansion();
            for (ValueSet.ValueSetExpansionContainsComponent contains : expansion.getContains()) {
                if (StringUtils.equals(theCode, contains.getCode())) {
                    return new CodeValidationResult()
                            .setCode(contains.getCode())
                            .setDisplay(contains.getDisplay());
                }
            }

            return new CodeValidationResult()
                    .setSeverity(IssueSeverity.ERROR)
                    .setMessage(messages.getMessage("validation.terminology.expand", new Object[]{theCode, valueSetUrl}));
        }
    }

    @Cacheable(cacheNames = "codeSystemSupported", key = "#theSystem")
    @Override
    public boolean isCodeSystemSupported(ValidationSupportContext theValidationSupportContext, String theSystem) {
        LOG.debug("Perform '/CodeSystem/_search' operation: url={}", theSystem);

        IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
        ParametersUtil.addParameterToParametersUri(getFhirContext(), requestParams, "url", theSystem);

        Bundle response = client.search()
                .forResource(CodeSystem.class)
                .where(CodeSystem.URL.matches().value(theSystem))
                .returnBundle(Bundle.class)
                .execute();

        return (response.getTotal() > 0);
    }

    @Cacheable(cacheNames = "validateCode", key = "#theCodeSystem + '_' + #theCode + '_'+ #theValueSetUrl")
    @Override
    public CodeValidationResult validateCode(ValidationSupportContext theValidationSupportContext, ConceptValidationOptions theOptions,
                                             String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        if (StringUtils.isEmpty(theValueSetUrl)) {
            try {
                LOG.debug("Perform '/CodeSystem/$lookup' operation: system={}, code={}", theCodeSystem, theCode);

                IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
                ParametersUtil.addParameterToParametersUri(getFhirContext(), requestParams, "system", theCodeSystem);
                ParametersUtil.addParameterToParametersString(getFhirContext(), requestParams, "code", theCode);

                Parameters responseParams = client
                        .operation()
                        .onType(CodeSystem.class)
                        .named("lookup")
                        .withParameters(requestParams)
                        .returnResourceType(Parameters.class)
                        .execute();

                StringType name = (StringType) responseParams.getParameter("name");
                StringType version = (StringType) responseParams.getParameter("version");
                StringType display = (StringType) responseParams.getParameter("display");

                return new CodeValidationResult()
                        .setCode(theCode)
                        .setCodeSystemName(name.getValue())
                        .setCodeSystemVersion(version.getValue())
                        .setDisplay(display.getValue());
            } catch (ResourceNotFoundException e) {
                return new CodeValidationResult()
                        .setSeverity(IssueSeverity.ERROR)
                        .setMessage(messages.getMessage("validation.terminology.lookup", new Object[]{theCode, theCodeSystem}));
            }
        }

        return null;
    }

    @Override
    public FhirContext getFhirContext() {
        return context;
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
}
