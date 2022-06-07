package org.ehrbase.fhirbridge.ihe.xds.converter;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentReference;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.PatientInfo;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp;

import javax.activation.DataHandler;
import java.util.List;

public class DocumentConverter {

    public static List<Document> convert(DocumentReference documentReference, CompositionEntity compositionEntity) {
        Document document = new Document();
        document.setDocumentEntry(getDocumentEntry(documentReference));
        document.setDataHandler(new DataHandler(compositionEntity, "application/xml"));
        return List.of(document);
    }


    private static DocumentEntry getDocumentEntry(DocumentReference documentReference) {
        DocumentEntry documentEntry = new DocumentEntry();
        documentEntry.setUniqueId(documentReference.getIdentifier().get(0).getId());
        documentEntry.setTypeCode(getTypeCode(documentReference));
        documentEntry.setClassCode(getClassCode(documentReference));
        documentEntry.setPatientId(new Identifiable(documentReference.getSubject().getReference()));
        documentEntry.getConfidentialityCodes().add(getConfidentialityCode(documentReference));
        documentEntry.setMimeType("application/xml");
        setDataFromContent(documentEntry, documentReference);
     //   documentEntry.setFormatCode(new Code(documentReference.getFo));
        setEventCodeList(documentEntry, documentReference);
        return documentEntry;
    }

    private static void setEventCodeList(DocumentEntry documentEntry, DocumentReference documentReference) {
        setEventCodes(documentEntry, documentReference);
        setFacilityTypeCode(documentEntry, documentReference);
        setPracticeSettingCode(documentEntry, documentReference);
        setSourcePatientId(documentEntry, documentReference);
    }

    private static void setSourcePatientId(DocumentEntry documentEntry, DocumentReference documentReference) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.getIds().add(new Identifiable(documentReference.getContext().getSourcePatientInfo().getReference()));
        documentEntry.setSourcePatientInfo(patientInfo);
    }

    private static void setPracticeSettingCode(DocumentEntry documentEntry, DocumentReference documentReference) {
        for(Coding coding : documentReference.getContext().getPracticeSetting().getCoding()){
            documentEntry.getEventCodeList().add(codingToCode(coding));
        }
    }

    private static void setFacilityTypeCode(DocumentEntry documentEntry, DocumentReference documentReference) {
       for(Coding coding : documentReference.getContext().getFacilityType().getCoding()){
           documentEntry.getEventCodeList().add(codingToCode(coding));
       }
    }

    private static void setEventCodes(DocumentEntry documentEntry, DocumentReference documentReference) {
        for(CodeableConcept event :  documentReference.getContext().getEvent()){
            for(Coding coding : event.getCoding()){
                documentEntry.getEventCodeList().add(codingToCode(coding));
            }
        }
    }

    private static void setDataFromContent(DocumentEntry documentEntry, DocumentReference documentReference) {
        Attachment content = documentReference.getContent().get(0).getAttachment();
        documentEntry.setLanguageCode(content.getLanguage());
        documentEntry.setTitle(new LocalizedString(content.getTitle(), content.getLanguage(), "UTF-8"));
        Timestamp timestamp = new Timestamp();
        timestamp.setDateTime(content.getCreationElement().getValueAsCalendar().toZonedDateTime());
        documentEntry.setCreationTime(timestamp);
    }

    private static Code getConfidentialityCode(DocumentReference documentReference) {
        Coding coding = documentReference.getSecurityLabel().get(0).getCoding().get(0);
        return new Code(coding.getCode(), new LocalizedString(coding.getDisplay()), coding.getSystem());
    }

    private static Code getTypeCode(DocumentReference documentReference) {
        Coding coding = documentReference.getType().getCoding().get(0);
        return new Code(coding.getCode(), new LocalizedString(coding.getDisplay()), coding.getSystem());
    }

    private static Code getClassCode(DocumentReference documentReference) {
        Coding coding = documentReference.getCategory().get(0).getCoding().get(0);
        return new Code(coding.getCode(), new LocalizedString(coding.getDisplay()), coding.getSystem());
    }

    private static Code codingToCode(Coding coding){
            Code code = new Code();
            code.setCode(coding.getCode());
            LocalizedString display = new LocalizedString();
            display.setLang("de-DE");
            display.setCharset("UTF-8");
            display.setValue(coding.getDisplay());
            code.setDisplayName(display);
            code.setSchemeName(code.getSchemeName());
            return code;
        }
}
