package org.ehrbase.fhirbridge.ihe.xds.converter;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentReference;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;

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
        documentReference.getContent();
   //     documentEntry.setLanguageCode(documentReference.getLanguage());
     //   documentEntry.setTitle(documentReference.get());
        return documentEntry;
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
}
