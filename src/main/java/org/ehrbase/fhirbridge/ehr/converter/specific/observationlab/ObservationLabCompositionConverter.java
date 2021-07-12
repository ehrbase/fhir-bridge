package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborbefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.List;

public class ObservationLabCompositionConverter extends ObservationToCompositionConverter<GECCOLaborbefundComposition> {

    @Override
    public GECCOLaborbefundComposition convertInternal(@NonNull Observation resource) {
        GECCOLaborbefundComposition composition = new GECCOLaborbefundComposition();
        initialiseLabortestBezeichnungMap();
        composition.setLaborergebnis(new LaborergebnisObservationConverter().convert(resource));
        composition.setStatusDefiningCode(getRegisterEintrag(resource));
        setKategorieValue(resource, composition);
        return composition;
    }


    private void initialiseLabortestBezeichnungMap() {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                LabortestKategorieDefiningCode.getCodesAsMap().put(code.getCode(), code);
            }
        }
    }

    private void setKategorieValue(Observation resource, GECCOLaborbefundComposition composition) {
        for(Coding coding : resource.getCategory().get(0).getCoding()){
            if (coding.getSystem().equals("http://loinc.org")) {
                if (LabortestKategorieDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
                    throw new ConversionException("Unknown LOINC code in observation");
                }
                LaborbefundKategorieElement labortestKategorieElement = new LaborbefundKategorieElement();
                labortestKategorieElement.setValue(LabortestKategorieDefiningCode.getCodesAsMap().get(coding.getCode()).getValue());
                composition.setKategorie(List.of(labortestKategorieElement));
            } else {
                throw new ConversionException("No LOINC code in observation");
            }
        }
    }

    private StatusDefiningCode getRegisterEintrag(Observation resource) {
        switch (resource.getStatus()) {
            case FINAL:
                return StatusDefiningCode.FINAL;
            case CORRECTED:
                return StatusDefiningCode.GEAENDERT;
            case PRELIMINARY:
                return StatusDefiningCode.VORLAEUFIG;
            default:
                return StatusDefiningCode.REGISTRIERT;
        }
    }
}
