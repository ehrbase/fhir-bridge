package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentManifest;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Author;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Person;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class SubmissionSetConverter extends ITI41Converter{

    public static SubmissionSet convert(DocumentManifest documentManifest) {
        SubmissionSet submissionSet = new SubmissionSet();
        Timestamp timestamp = new Timestamp();
        timestamp.setDateTime(OffsetDateTime.now().toZonedDateTime());
        submissionSet.setSubmissionTime(timestamp);
        submissionSet.setAuthor(getAuthor(documentManifest.getAuthor()));
        submissionSet.setUniqueId(documentManifest.getMasterIdentifier().getId());
        submissionSet.setEntryUuid(documentManifest.getIdentifier().get(0).getValue());
        submissionSet.setSourceId(documentManifest.getSource());
        submissionSet.setContentTypeCode(getContentType(documentManifest));
        submissionSet.setPatientId(getPatientId(documentManifest.getSubject()));
        return submissionSet;
    }


    private static Code getContentType(DocumentManifest documentManifest) {
        Coding coding = documentManifest.getType().getCoding().get(0);
        return new Code(coding.getCode(), new LocalizedString(coding.getDisplay()), coding.getSystem());
    }

    private static Author getAuthor(List<Reference> authors) {
        for (Reference authorEntry : authors) {
            Author author = new Author();
            authorEntry.getResource();
            author.setAuthorPerson(getPerson(authorEntry));
            return author;
        }
        throw new UnprocessableEntityException("no Author was defined in the MHD Bundle");
    }

    private static Person getPerson(Reference authorEntry) {
        if (authorEntry.getResource().getClass().equals(PractitionerRole.class)) {
            PractitionerRole practitionerRole = (PractitionerRole) authorEntry.getResource();
            Person person = new Person();
            Optional<String> id = practitionerRole.getCode().stream().filter(CodeableConcept::hasCoding)
                    .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                    .map(Coding::getCode)
                    .findFirst();
/*            Optional<String> name = practitionerRole.getCode().stream().filter(CodeableConcept::hasCoding)
                    .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                    .map(Coding::getDisplay)
                    .findFirst();
            person.setName(new Name(name.get()));*/
            person.setId(new Identifiable(id.get())); //Always present
            return person;
        } else {
            throw new UnprocessableEntityException("Only PractionerRole is currently supported for DocumentManifest Author");
        }
    }


}
