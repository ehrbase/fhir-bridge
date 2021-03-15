package org.ehrbase.fhirbridge.ehr.converter.specificconverters.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.PhWertCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningCode3;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class PhWertConverter extends LaboratoryTestAnalyteConverter {

    public PhWertConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public PhWertCluster map() {
        PhWertCluster phWertCluster = new PhWertCluster();
        phWertCluster.setErgebnisStatusValue(mapErgebnisStatus());
        phWertCluster.setUntersuchterAnalytDefiningCode(mapUntersuchterAnalyt());
        phWertCluster.setAnalytResultatUnits("pH");
        phWertCluster.setAnalytResultatMagnitude(mapValue());
        return phWertCluster;
    }

    @Override
    UntersuchterAnalytDefiningCode3 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningCode3 phSerumOrPlasma = UntersuchterAnalytDefiningCode3.PH_OF_SERUM_OR_PLASMA;
        UntersuchterAnalytDefiningCode3 phVenousBlood= UntersuchterAnalytDefiningCode3.PH_OF_VENOUS_BLOOD;
        UntersuchterAnalytDefiningCode3 phCapillaryBlood = UntersuchterAnalytDefiningCode3.PH_OF_CAPILLARY_BLOOD;
        UntersuchterAnalytDefiningCode3 phArterialBlood = UntersuchterAnalytDefiningCode3.PH_OF_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningCode3 phMixedVenousBlood = UntersuchterAnalytDefiningCode3.PH_OF_MIXED_VENOUS_BLOOD;
        UntersuchterAnalytDefiningCode3 phBlood = UntersuchterAnalytDefiningCode3.PH_OF_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(phSerumOrPlasma.getCode())) {
                return phSerumOrPlasma;
            } else if (code.equals(phVenousBlood.getCode())) {
                return phVenousBlood;
            } else if (code.equals(phCapillaryBlood.getCode())) {
                return phCapillaryBlood;
            }else if (code.equals(phArterialBlood.getCode())) {
                return phArterialBlood;
            }else if (code.equals(phMixedVenousBlood.getCode())) {
                return phMixedVenousBlood;
            }else if (code.equals(phBlood.getCode())) {
                return phBlood;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }


}


