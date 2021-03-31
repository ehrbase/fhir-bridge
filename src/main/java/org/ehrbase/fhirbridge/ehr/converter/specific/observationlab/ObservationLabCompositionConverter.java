package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ObservationLabCompositionConverter extends ObservationToCompositionConverter<GECCOLaborbefundComposition> {

    @Override
    public GECCOLaborbefundComposition convertInternal(@NonNull Observation resource) {
        GECCOLaborbefundComposition composition = new GECCOLaborbefundComposition();
        initialiseLabortestBezeichnungMap(resource);
        composition.setLaborergebnis(new LaborergebnisObservationConverter().convert(resource));
        composition.setStatusDefiningCode(getRegisterEintrag(resource));
        setKategorieValue(resource, composition);
        if (!resource.getMethod().isEmpty() && !resource.getMethod().getCoding().isEmpty()) {
            DvText testmethode = new DvText();
            testmethode.setValue(resource.getMethod().getCoding().get(0).getDisplay());
        }
        return composition;
    }

    private void initialiseLabortestBezeichnungMap(Observation resource) {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                LabortestKategorieDefiningCode.getCodesAsMap().put(code.getCode(), code);
            }
        }
    }

    private void setKategorieValue(Observation resource, GECCOLaborbefundComposition composition) {
        if (resource.getCategory().get(0).getCoding().get(0).getSystem().equals("http://loinc.org")) {
            String loincCode = resource.getCategory().get(0).getCoding().get(0).getCode();
            LabortestKategorieDefiningCode categoryDefiningcode = LabortestKategorieDefiningCode.getCodesAsMap().get(loincCode);

            if (categoryDefiningcode == null) {
                throw new ConversionException("Unknown LOINC code in observation");
            }

            composition.setKategorieValue(categoryDefiningcode.getValue());
        } else {
            throw new ConversionException("No LOINC code in observation");
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
