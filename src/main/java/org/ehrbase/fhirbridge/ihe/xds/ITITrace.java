package org.ehrbase.fhirbridge.ihe.xds;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.DocumentManifest;
import org.hl7.fhir.r4.model.DocumentReference;

public class ITITrace {
    private final DocumentManifest documentManifest;
    private final DocumentReference documentReference;
    private CompositionEntity compositionEntity;

    public ITITrace(DocumentManifest documentManifest, DocumentReference documentReference) {
        this.documentManifest = documentManifest;
        this.documentReference = documentReference;
    }

    public CompositionEntity getCompositionEntity() {
        return compositionEntity;
    }

    public void setCompositionEntity(CompositionEntity compositionEntity) {
        this.compositionEntity = compositionEntity;
    }

    public DocumentManifest getDocumentManifest() {
        return documentManifest;
    }

    public DocumentReference getDocumentReference() {
        return documentReference;
    }
}


