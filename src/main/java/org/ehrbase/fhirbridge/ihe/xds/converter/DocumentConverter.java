package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.sun.istack.ByteArrayDataSource;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentReference;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp;

import javax.activation.DataHandler;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class DocumentConverter extends ITI41Converter {

    public static Document convert(DocumentReference documentReference, CompositionEntity compositionEntity) {
        Document document = new Document();
        document.setDocumentEntry(getDocumentEntry(documentReference));
        document.setDataHandler(getDataHandler(compositionEntity));
        return document;
    }

    private static DataHandler getDataHandler(CompositionEntity compositionEntity) {
        byte[] encodedBytes = getCanonical(compositionEntity).getBytes(StandardCharsets.UTF_8);
        ByteArrayDataSource compositionInBytes = new ByteArrayDataSource(encodedBytes, "application/json");
        return new DataHandler(compositionInBytes);
    }

    private static String getCanonical(CompositionEntity compositionEntity) {
        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();
        Unflattener unflattener = new Unflattener(resourceTemplateProvider);
        RMObject rmObject = unflattener.unflatten(compositionEntity);
        CanonicalJson canonicalJson = new CanonicalJson();
        return canonicalJson.marshal(rmObject);
    }

    private static String getFlattenedJson(CompositionEntity compositionEntity) {
        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();
        Unflattener unflattener = new Unflattener(resourceTemplateProvider);
        RMObject rmObject = unflattener.unflatten(compositionEntity);
        CanonicalJson canonicalJson = new CanonicalJson();
        String compositionJson = canonicalJson.marshal(rmObject); //super dump but currently i dont see any other way
        Composition composition = canonicalJson.unmarshal(compositionJson);
        if(composition.getArchetypeDetails().getTemplateId() == null){
            throw new UnprocessableEntityException("No Template id could be found within the composition, therefore base64 is not possible");
        }
        return new FlatJasonProvider(resourceTemplateProvider)
                .buildFlatJson(FlatFormat.SIM_SDT, composition.getArchetypeDetails().getTemplateId().toString())
                .marshal(rmObject);
    }

    private static DocumentEntry getDocumentEntry(DocumentReference documentReference) {
        DocumentEntry documentEntry = new DocumentEntry();
        documentEntry.setUniqueId(documentReference.getIdentifier().get(0).getId());
        documentEntry.setTypeCode(getTypeCode(documentReference));
        documentEntry.setClassCode(getClassCode(documentReference));
        documentEntry.setPatientId(getPatientId(documentReference.getSubject()));
        documentEntry.getConfidentialityCodes().add(getConfidentialityCode(documentReference));
        documentEntry.setMimeType("application/json");
        getPracticeSettingCode(documentReference).ifPresent(documentEntry::setPracticeSettingCode);
        getHealthCareFacilityCode(documentReference).ifPresent(documentEntry::setHealthcareFacilityTypeCode);
        setDataFromContent(documentEntry, documentReference);
        setEventCodeList(documentEntry, documentReference);
        return documentEntry;
    }

    private static Optional<Code> getHealthCareFacilityCode(DocumentReference documentReference) {
        for(Coding coding : documentReference.getContext().getFacilityType().getCoding()){
            if(coding.hasCode()){
                return Optional.of(codingToCode(coding));
            }
        }
        return Optional.empty();
    }

    private static Optional<Code> getPracticeSettingCode(DocumentReference documentReference) {
        for(Coding coding : documentReference.getContext().getPracticeSetting().getCoding()){
            if(coding.hasCode()){
                return Optional.of(codingToCode(coding));
            }
        }
        return Optional.empty();
    }

    private static void setEventCodeList(DocumentEntry documentEntry, DocumentReference documentReference) {
        setEventCodes(documentEntry, documentReference);
        setSourcePatientId(documentEntry, documentReference);
    }

    private static void setSourcePatientId(DocumentEntry documentEntry, DocumentReference documentReference) {
        documentEntry.setSourcePatientId(getPatientId(documentReference.getContext().getSourcePatientInfo()));
    }

    private static void setEventCodes(DocumentEntry documentEntry, DocumentReference documentReference) {
        for(CodeableConcept event :  documentReference.getContext().getEvent()){
            for(Coding coding : event.getCoding()){
                documentEntry.getEventCodeList().add(codingToCode(coding));
            }
        }
    }

    private static void setDataFromContent(DocumentEntry documentEntry, DocumentReference documentReference) {
        Attachment attachment = documentReference.getContent().get(0).getAttachment();
        DocumentReference.DocumentReferenceContentComponent content = documentReference.getContent().get(0);
        documentEntry.setLanguageCode(attachment.getLanguage());
        documentEntry.setTitle(new LocalizedString(attachment.getTitle(), attachment.getLanguage(), "UTF-8"));
        documentEntry.setFormatCode(getFormatCode(content));
        Timestamp timestamp = new Timestamp();
        timestamp.setDateTime(attachment.getCreationElement().getValueAsCalendar().toZonedDateTime());
        documentEntry.setCreationTime(timestamp);
    }


    private static Code getFormatCode(DocumentReference.DocumentReferenceContentComponent content) {
        return codingToCode(content.getFormat());
    }

    private static Code getConfidentialityCode(DocumentReference documentReference) {
        return codingToCode(documentReference.getSecurityLabel().get(0).getCoding().get(0));
    }

    private static Code getTypeCode(DocumentReference documentReference) {
        Coding coding = documentReference.getType().getCoding().get(0);
        return codingToCode(coding);
    }

    private static Code getClassCode(DocumentReference documentReference) {
        Coding coding = documentReference.getCategory().get(0).getCoding().get(0);
        return codingToCode(coding);
    }

    private static Code codingToCode(Coding coding){
            Code result = new Code();
            result.setCode(coding.getCode());
            LocalizedString display = new LocalizedString();
            display.setLang("de-DE");
            display.setCharset("UTF-8");
            display.setValue(coding.getDisplay());
            result.setDisplayName(display);
            result.setSchemeName(coding.getSystem());
            return result;
        }
}
