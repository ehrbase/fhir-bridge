package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.core.domain.PatientId;
import org.ehrbase.fhirbridge.core.repository.PatientIdRepository;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Reference;
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
@SuppressWarnings("java:S6212")
public class EhrIdLookupProcessor implements Processor, MessageSourceAware {

    private final OpenEhrClient openEhrClient;

    private final PatientIdRepository patientIdRepository;

    private MessageSourceAccessor messages;

    public EhrIdLookupProcessor(OpenEhrClient openEhrClient, PatientIdRepository patientIdRepository) {
        this.openEhrClient = openEhrClient;
        this.patientIdRepository = patientIdRepository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Resource resource = exchange.getIn().getMandatoryBody(Resource.class);

        Identifier identifier;
        if (Resources.isPatient(resource)) {
            identifier = handlePatientIdentifier((Patient) resource);
        } else if (Resources.isQuestionnaireResponse(resource) && Resources.isCovid19Questionnaire((QuestionnaireResponse) resource)) {
            identifier = generateRandomIdentifier();
        } else {
            identifier = handleOtherResourceIdentifier(resource);
        }

        UUID ehrId = findEhrId(identifier)
                .orElseGet(() -> createEhr(identifier));

        exchange.getIn().setHeader(CompositionConstants.EHR_ID, ehrId);
    }

    private Identifier handlePatientIdentifier(Patient patient) {
        if (!patient.hasIdentifier()) {
            throw new UnprocessableEntityException("Patient should have one identifier");
        } else if (patient.getIdentifier().size() > 1) {
            throw new UnprocessableEntityException("Patient has more than one identifier");
        }

        return patient.getIdentifier().get(0);
    }

    private Identifier handleOtherResourceIdentifier(Resource resource) {
        Reference subject = Resources.getSubject(resource)
                .orElseThrow(UnprocessableEntityException::new);

        if (!subject.hasIdentifier()) {
            throw new UnprocessableEntityException(messages.getMessage("validation.subject.identifierRequired"));
        }

        return subject.getIdentifier();
    }

    private Identifier generateRandomIdentifier() {
        PatientId patientId = patientIdRepository.save(new PatientId());
        return new Identifier()
                .setSystem(Resources.RFC_4122_SYSTEM)
                .setValue(patientId.getUuidAsString());
    }

    private Optional<UUID> findEhrId(Identifier identifier) {
        String aql = "SELECT e/ehr_id/value FROM ehr e WHERE e/ehr_status/subject/external_ref/id/value = $value " +
                "AND e/ehr_status/subject/external_ref/id/scheme = $scheme";
        Query<Record1<UUID>> query = Query.buildNativeQuery(aql, UUID.class);

        ParameterValue<String> value = new ParameterValue<>("value", identifier.getValue());
        ParameterValue<String> scheme = new ParameterValue<>("scheme", identifier.getSystem());
        List<Record1<UUID>> result = openEhrClient.aqlEndpoint().execute(query, value, scheme);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0).value1());
    }

    private UUID createEhr(Identifier identifier) {
        GenericId id = new GenericId(identifier.getValue(), identifier.getSystem());
        PartyRef externalRef = new PartyRef(id, "DEMOGRAPHIC", "PERSON");
        PartySelf subject = new PartySelf(externalRef);
        EhrStatus ehrStatus = new EhrStatus("openEHR-EHR-EHR_STATUS.generic.v1", new DvText("EHR Status"), subject, true, true, null);
        return openEhrClient.ehrEndpoint().createEhr(ehrStatus);
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
