package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
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
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class SubmissionSetConverter extends ITI41Converter {
    private static final Logger LOG = LoggerFactory.getLogger(SubmissionSetConverter.class);


    public static SubmissionSet convert(DocumentManifest documentManifest) {
        SubmissionSet submissionSet = new SubmissionSet();
        submissionSet.setSubmissionTime(new Timestamp(documentManifest.getCreatedElement().getValueAsCalendar().toZonedDateTime(),
                convertPrecision(documentManifest.getCreatedElement().getPrecision())));
        getAuthor(documentManifest.getAuthor()).ifPresent(submissionSet::setAuthor);
        submissionSet.setUniqueId(documentManifest.getMasterIdentifier().getValue());
        submissionSet.setEntryUuid(documentManifest.getIdentifier().get(0).getValue());
        submissionSet.setSourceId(documentManifest.getSource());
        submissionSet.setContentTypeCode(getContentType(documentManifest));
        submissionSet.setPatientId(getPatientId(documentManifest.getSubject()));
        return submissionSet;
    }

    private static Timestamp.Precision convertPrecision(TemporalPrecisionEnum precision) {
        if(precision == TemporalPrecisionEnum.YEAR){
            return Timestamp.Precision.YEAR;
        }else if(precision == TemporalPrecisionEnum.MONTH){
            return Timestamp.Precision.MONTH;
        }else if(precision == TemporalPrecisionEnum.MINUTE){
            return Timestamp.Precision.MINUTE;
        }else if(precision == TemporalPrecisionEnum.SECOND){
            return Timestamp.Precision.SECOND;
        }else if(precision == TemporalPrecisionEnum.MILLI){
            LOG.warn("precision mini is not supported for the timestamp of submissionTime, second is the precisest. Therefore it will be mapped.");
            return Timestamp.Precision.SECOND;
        }else{
            throw new UnprocessableEntityException("Created time could not be to submissions time. The precision could not be mapped.");
        }
    }


    private static Code getContentType(DocumentManifest documentManifest) {
        Coding coding = documentManifest.getType().getCoding().get(0);
        return new Code(coding.getCode(), new LocalizedString(coding.getDisplay()), replaceUrnOid(coding.getSystem()));
    }

    private static Optional<Author> getAuthor(List<Reference> authors) {
        for (Reference authorEntry : authors) {
            Optional<Identifiable> authorRole = getAuthorRoleIdentifiable(authorEntry);
            if (authorRole.isPresent()) {
                Author author = new Author();
                author.getAuthorRole().add(authorRole.get());
                return Optional.of(author);
            }
        }
        return Optional.empty();
    }

    private static Optional<Identifiable> getAuthorRoleIdentifiable(Reference authorEntry) {
        if (authorEntry.getResource().getClass().equals(PractitionerRole.class)) {
            return practitionerRoleToIdentifiable(authorEntry);
        } else {
            LOG.warn("Only PractitionerRole is currently supported for DocumentManifest, therefore nothing is mapped");
            return Optional.empty();
        }
    }

    private static Optional<Identifiable> practitionerRoleToIdentifiable(Reference authorEntry) {
        PractitionerRole practitionerRole = (PractitionerRole) authorEntry.getResource();
        Optional<Coding> coding = practitionerRole.getCode().stream().filter(CodeableConcept::hasCoding)
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .findFirst();
        if (coding.isPresent()) {
            return codingToIdentifiable(coding.get());
        } else {
            LOG.warn("Only PractitionerRole is currently supported for DocumentManifest, therefore nothing is mapped");
            return Optional.empty();
        }
    }

    private static Optional<Identifiable> codingToIdentifiable(Coding coding) {
        try {
            return Optional.of(new Identifiable(coding.getCode(),new Oid(replaceUrnOid(coding.getSystem())))); //always present
        } catch (GSSException e) {
            LOG.error("Error occured when for the OID of Author role: " + e);
            throw new UnprocessableEntityException("OID system code of practitionerRole seems not to be valid");
        }
    }

}
