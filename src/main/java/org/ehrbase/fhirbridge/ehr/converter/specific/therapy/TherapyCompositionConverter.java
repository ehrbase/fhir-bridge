package org.ehrbase.fhirbridge.ehr.converter.specific.therapy;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.GECCOProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeccoProzedurKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;

public class TherapyCompositionConverter extends ProcedureToCompositionConverter<GECCOProzedurComposition> {

    @Override
    protected GECCOProzedurComposition convertInternal(Procedure resource) {
        GECCOProzedurComposition result = new GECCOProzedurComposition();
        mapCategory(result, resource);
        switch (resource.getStatus()) {
            case UNKNOWN:
                result.setUnbekannteProzedur(new UnbekannteProzedurEvaluationConverter().convert(resource));
                break;
            case NOTDONE:
                result.setNichtDurchgefuehrteProzedur(new NichtDurchgefuehrteProzedurEvaluationConverter().convert(resource));
                break;
            case ENTEREDINERROR:
                throw new ConversionException("Invalid status");
            default:
                ProzedurAction prozedurAction = new ProzedurActionConverter().convert(resource);
                prozedurAction.setArtDerProzedurDefiningCode(result.getKategorie().get(0).getValue());
                result.setProzedur(prozedurAction);
        }

        return result;
    }

    private void mapCategory(GECCOProzedurComposition composition, Procedure procedure) {
        // Map Kategorie
        composition.setKategorie(new ArrayList<>());
        for (Coding coding : procedure.getCategory().getCoding()) {
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && KategorieDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
                GeccoProzedurKategorieElement element = new GeccoProzedurKategorieElement();
                element.setValue(KategorieDefiningCode.getCodesAsMap().get(coding.getCode()));
                composition.getKategorie().add(element);
            }
        }
    }

}
