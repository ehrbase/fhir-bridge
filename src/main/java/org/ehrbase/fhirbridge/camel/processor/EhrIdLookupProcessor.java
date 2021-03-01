package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.fhir.support.PatientIdRepository;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link Processor} that lookups for the EhrId corresponding to the subject identifier provided in the resource.
 */
@Component
public class EhrIdLookupProcessor implements Processor, MessageSourceAware {

    private final OpenEhrClient openEhrClient;

    private final PatientIdRepository patientIdRepository;

    private MessageSourceAccessor messages;

    public EhrIdLookupProcessor(OpenEhrClient openEhrClient, PatientIdRepository patientIdRepository) {
        this.openEhrClient = openEhrClient;
        this.patientIdRepository = patientIdRepository;
    }

    @Override
    public void process(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);
        String subject = Resources.getSubjectIdentifier(resource, Optional.of(openEhrClient), Optional.of(patientIdRepository))
                .map(Identifier::getValue)
                .orElseThrow(() -> new UnprocessableEntityException(messages.getMessage("validation.subject.identifierRequired")));

        // @formatter:off
        Query<Record1<UUID>> query = Query.buildNativeQuery(
                "SELECT e/ehr_id/value " +
                "FROM ehr e " +
                "WHERE e/ehr_status/subject/external_ref/id/value = $subject", UUID.class);
        // @formatter:on

        List<Record1<UUID>> result = openEhrClient.aqlEndpoint().execute(query, new ParameterValue<>("subject", subject));
        if (result.isEmpty()) {
            throw new UnprocessableEntityException(messages.getMessage("validation.subject.ehrIdNotFound", new Object[]{subject}));
        }

        exchange.getIn().setHeader(CompositionConstants.EHR_ID, result.get(0).value1());
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
