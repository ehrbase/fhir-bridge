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

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToProcedureActionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.CurrentStateDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeraetenameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KoerperstelleDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.MedizingeraetCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("java:S6212")
public class ProzedurActionConverter extends ProcedureToProcedureActionConverter<ProzedurAction>
        implements TherapyConverter {

    @Override
    protected ProzedurAction convertInternal(Procedure procedure) {
        ProzedurAction result = new ProzedurAction();
        result.setNameDerProzedurDefiningCode(convertCode(procedure));
        convertBodySite(procedure).ifPresent(result::setKoerperstelleDefiningCode);
        result.setMedizingeraet(convertUsedCode(procedure));
        result.setArtDerProzedurDefiningCode(convertCategory(procedure));
        convertExtension(procedure).ifPresent(result::setDurchfuehrungsabsichtValue);
        convertNote(procedure).ifPresent(result::setKommentarValue);
        result.setCurrentStateDefiningCode(CurrentStateDefiningCode.PLANNED);
        return result;
    }

    private Optional<KoerperstelleDefiningCode> convertBodySite(Procedure procedure) {
        return procedure.getBodySite()
                .stream()
                .flatMap(concept -> concept.getCoding().stream())
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(coding -> {
                    KoerperstelleDefiningCode code = KoerperstelleDefiningCode.getCodesAsMap().get(coding.getCode());
                    if (code == null) {
                        throw new ConversionException("Invalid body site");
                    }
                    return code;
                });
    }

    private List<MedizingeraetCluster> convertUsedCode(Procedure procedure) {
        return procedure.getUsedCode()
                .stream()
                .flatMap(concept -> concept.getCoding().stream())
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .map(coding -> {
                    GeraetenameDefiningCode code = GeraetenameDefiningCode.getCodesAsMap().get(coding.getCode());
                    if (code == null) {
                        throw new ConversionException("Invalid medical device code");
                    }
                    MedizingeraetCluster medizingeraetCluster = new MedizingeraetCluster();
                    medizingeraetCluster.setGeraetenameDefiningCode(GeraetenameDefiningCode.getCodesAsMap().get(coding.getCode()));
                    return medizingeraetCluster;
                })
                .collect(Collectors.toList());
    }

    private KategorieDefiningCode convertCategory(Procedure procedure) {
        return procedure.getCategory()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .map(coding -> KategorieDefiningCode.getCodesAsMap().get(coding.getCode()))
                .findFirst()
                .orElseThrow(() -> new ConversionException("Invalid category"));
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
