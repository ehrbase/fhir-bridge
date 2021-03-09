package org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SauerstoffpartialdruckConverter extends LaboratoryTestAnalyteConverter {
    public SauerstoffpartialdruckConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffpartialdruckCluster map() {
        SauerstoffpartialdruckCluster sauerstoffpartialdruckCluster = new SauerstoffpartialdruckCluster();
        sauerstoffpartialdruckCluster.setErgebnisStatusValue(mapErgebnisStatus());
        sauerstoffpartialdruckCluster.setAnalytResultatUnits("mmHg");
        sauerstoffpartialdruckCluster.setUntersuchterAnalytDefiningCode(mapUntersuchterAnalyt());
        sauerstoffpartialdruckCluster.setAnalytResultatMagnitude(mapValue());
        return sauerstoffpartialdruckCluster;
    }

    @Override
    protected UntersuchterAnalytDefiningCode2 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningCode2 oxygenBlood = UntersuchterAnalytDefiningCode2.OXYGEN_PARTIAL_PRESSURE_IN_BLOOD;
        UntersuchterAnalytDefiningCode2 oxygenArterial= UntersuchterAnalytDefiningCode2.OXYGEN_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningCode2 oxygenCapillary = UntersuchterAnalytDefiningCode2.OXYGEN_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(oxygenBlood.getCode())) {
                return oxygenBlood;
            } else if (code.equals(oxygenArterial.getCode())) {
                return oxygenArterial;
            } else if (code.equals(oxygenCapillary.getCode())) {
                return oxygenCapillary;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }
}

