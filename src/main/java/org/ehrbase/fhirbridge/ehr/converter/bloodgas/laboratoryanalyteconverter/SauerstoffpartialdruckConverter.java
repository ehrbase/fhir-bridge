package org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode2;
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
        sauerstoffpartialdruckCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        sauerstoffpartialdruckCluster.setAnalytResultatMagnitude(mapValue());
        return sauerstoffpartialdruckCluster;
    }

    @Override
    protected UntersuchterAnalytDefiningcode2 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode2 oxygenBlood = UntersuchterAnalytDefiningcode2.OXYGEN_PARTIAL_PRESSURE_IN_BLOOD;
        UntersuchterAnalytDefiningcode2 oxygenArterial= UntersuchterAnalytDefiningcode2.OXYGEN_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningcode2 oxygenCapillary = UntersuchterAnalytDefiningcode2.OXYGEN_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD;

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

