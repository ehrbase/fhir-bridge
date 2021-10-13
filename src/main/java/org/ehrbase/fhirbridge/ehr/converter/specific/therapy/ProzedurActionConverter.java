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

package org.ehrbase.fhirbridge.ehr.converter.specific.therapy;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToProcedureActionConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.CurrentStateDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.MedizingeraetCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("java:S6212")
public class ProzedurActionConverter extends ProcedureToProcedureActionConverter<ProzedurAction>
        implements TherapyConverter {

    @Override
    protected ProzedurAction convertInternal(Procedure procedure) {
        ProzedurAction result = new ProzedurAction();
        convertCode(procedure).ifPresent(result::setNameDerProzedur);
        convertBodySite(procedure).ifPresent(result::setKoerperstelleValue);
        result.setMedizingeraet(convertUsedCode(procedure));
        convertCode(procedure).ifPresent(result::setNameDerProzedur);
        convertCategory(procedure).ifPresent(result::setArtDerProzedur);
        convertExtension(procedure).ifPresent(result::setDurchfuehrungsabsichtValue);
        convertNote(procedure).ifPresent(result::setKommentarValue);
        result.setCurrentStateDefiningCode(CurrentStateDefiningCode.PLANNED);
        return result;
    }

    private Optional<String> convertBodySite(Procedure procedure) { //TODO fix when codes are replaced in the template
        Optional<Coding> coding = procedure.getBodySite()
                .stream()
                .flatMap(concept -> concept.getCoding().stream())
                .filter(cding -> cding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst();
        if(coding.isPresent() && coding.get().hasDisplay()){
            return Optional.of(coding.get().getDisplay());
        }else if(coding.isPresent() && coding.get().hasCode()){
            return Optional.of(coding.get().getCode());
        }
        return Optional.empty();

    }

    private List<MedizingeraetCluster> convertUsedCode(Procedure procedure) {
        List<MedizingeraetCluster> medizingeraetClusters = new ArrayList<>();
        for(CodeableConcept codeableConcept:procedure.getUsedCode()){
            for(Coding coding:codeableConcept.getCoding()){
                MedizingeraetCluster medizingeraetCluster = new MedizingeraetCluster();
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(medizingeraetCluster::setGeraetename);
                medizingeraetClusters.add(medizingeraetCluster);
            }

        }
        return medizingeraetClusters;
    }

    private Optional<DvCodedText> convertCategory(Procedure procedure) {
        for(Coding coding:procedure.getCategory().getCoding()){
            return DvCodedTextParser.parseFHIRCoding(coding);
        }
        return Optional.empty();
    }

    private Optional<String> convertExtension(Procedure procedure) {
        if (!procedure.hasExtension()) {
            return Optional.empty();
        }

        return procedure.getExtension()
                .stream()
                .filter(extension -> extension.getUrl().equals("https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Durchfuehrungsabsicht"))
                .findFirst()
                .map(extension -> ((Coding) extension.getValue()).getCode());
    }

    private Optional<String> convertNote(Procedure procedure) {
        if (!procedure.hasNote()) {
            return Optional.empty();
        }
        return Optional.of(procedure.getNote()
                .stream()
                .map(Annotation::getText)
                .collect(Collectors.joining(", ")));
    }
}
