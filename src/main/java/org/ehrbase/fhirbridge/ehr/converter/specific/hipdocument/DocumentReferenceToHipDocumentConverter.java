/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.ehr.converter.specific.hipdocument;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvURI;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.HIPDocumentComposition;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataCluster;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataIdentifikatorElement;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.MediendateiCluster;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DocumentReference;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Converts a {@link DocumentReference} to a {@link HIPDocumentComposition}.
 */
public class DocumentReferenceToHipDocumentConverter extends CompositionConverter<DocumentReference, HIPDocumentComposition> {

    @Override
    protected HIPDocumentComposition convertInternal(DocumentReference documentReference) {
        HIPDocumentComposition composition = new HIPDocumentComposition();
        composition.setStartTimeValue(getStartTime(documentReference));
        getMetadata(documentReference).ifPresent(composition::setHipMetadata);
        composition.setMediendatei(getMultimedia(documentReference));
        return composition;
    }

    private ZonedDateTime getStartTime(DocumentReference documentReference) {
        if (documentReference.getDate() == null) {
            return ZonedDateTime.now();
        }
        return documentReference.getDate().toInstant().atZone(ZoneId.systemDefault());
    }

    private Optional<HipMetadataCluster> getMetadata(DocumentReference documentReference) {
        if (!documentReference.hasType() && !documentReference.hasMasterIdentifier()) {
            return Optional.empty();
        }

        HipMetadataCluster metadata = new HipMetadataCluster();
        getType(documentReference).ifPresent(metadata::setKategorieValue);
        getIdentifiers(documentReference).ifPresent(metadata::setIdentifikator);
        return Optional.of(metadata);
    }

    private MediendateiCluster getMultimedia(DocumentReference documentReference) {
        Attachment attachment = documentReference.getContentFirstRep().getAttachment();

        DvMultimedia multimedia = new DvMultimedia();
        multimedia.setUri(new DvURI(attachment.getUrl()));
        multimedia.setMediaType(new CodePhrase(new TerminologyId("IANA_media-types"), attachment.getContentType()));
        multimedia.setSize(attachment.getSize());

        MediendateiCluster result = new MediendateiCluster();
        result.setMediendateiInhalt(multimedia);
        result.setMediendateiInhaltValue(attachment.getTitle());
        result.setBeschreibungValue(documentReference.getDescription());
        getCreation(attachment).ifPresent(result::setErstelltValue);
        return result;
    }

    private Optional<String> getType(DocumentReference documentReference) {
        if (!documentReference.hasType() || (!documentReference.getType().hasCoding() && !documentReference.getType().hasText())) {
            return Optional.empty();
        }

        CodeableConcept type = documentReference.getType();
        if (type.hasCoding() && type.getCodingFirstRep().hasCode()) {
            Coding coding = type.getCodingFirstRep();
            return Optional.of(coding.getCode());
        } else if (type.hasText()) {
            return Optional.of(type.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<HipMetadataIdentifikatorElement>> getIdentifiers(DocumentReference documentReference) {
        if (!documentReference.hasMasterIdentifier()) {
            return Optional.empty();
        }

        DvIdentifier id = new DvIdentifier();
        id.setAssigner(documentReference.getMasterIdentifier().getSystem());
        id.setId(documentReference.getMasterIdentifier().getValue());
        HipMetadataIdentifikatorElement idElem = new HipMetadataIdentifikatorElement();
        idElem.setValue(id);
        return Optional.of(List.of(idElem));
    }

    private Optional<ZonedDateTime> getCreation(Attachment attachment) {
        if (!attachment.hasCreation()) {
            return Optional.empty();
        }
        return Optional.of(attachment.getCreation().toInstant().atZone(ZoneId.systemDefault()));
    }
}
