package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentManifest;
import org.hl7.fhir.r4.model.PractitionerRole;
import org.hl7.fhir.r4.model.Reference;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Author;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SubmissionSetConverter extends ITI41Converter {
    private static final Logger LOG = LoggerFactory.getLogger(SubmissionSetConverter.class);


    public static SubmissionSet convert(DocumentManifest documentManifest) {
        SubmissionSet submissionSet = new SubmissionSet();
        submissionSet.setSubmissionTime(documentManifest.getCreated().toString());
        getAuthor(documentManifest.getAuthor()).ifPresent(submissionSet::setAuthor);
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

    private static Optional<Author> getAuthor(List<Reference> authors) {
        for (Reference authorEntry : authors) {
            Optional<Identifiable> authorRole = getAuthorRole(authorEntry);
            if (authorRole.isPresent()) {
                Author author = new Author();
                author.getAuthorRole().add(authorRole.get());
                return Optional.of(author);
            }
        }
        return Optional.empty();
    }

    private static Optional<Identifiable> getAuthorRole(Reference authorEntry) {
        if (authorEntry.getResource().getClass().equals(PractitionerRole.class)) {
            return practitionerToAuthorRole(authorEntry);
        } else {
            LOG.warn("Only PractitionerRole is currently supported for DocumentManifest, therefore nothing is mapped");
            return Optional.empty();
        }
    }

    private static Optional<Identifiable> practitionerToAuthorRole(Reference authorEntry) {
        PractitionerRole practitionerRole = (PractitionerRole) authorEntry.getResource();
        Optional<Coding> coding = practitionerRole.getCode().stream().filter(CodeableConcept::hasCoding)
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .findFirst();
        if (coding.isPresent()) {
            return codingToAuthorRole(coding.get());
        } else {
            LOG.warn("Only PractitionerRole is currently supported for DocumentManifest, therefore nothing is mapped");
            return Optional.empty();
        }
    }

    private static Optional<Identifiable> codingToAuthorRole(Coding coding) {
        try {
            return Optional.of(new Identifiable(coding.getCode(), new Oid(coding.getSystem()))); //always present
        } catch (GSSException e) {
            throw new UnprocessableEntityException("OID system code of practitionerRole seems not to be valid");
        }
    }

}
