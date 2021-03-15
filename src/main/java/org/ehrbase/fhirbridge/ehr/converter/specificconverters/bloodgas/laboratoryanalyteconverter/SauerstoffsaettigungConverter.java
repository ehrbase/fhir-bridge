package org.ehrbase.fhirbridge.ehr.converter.specificconverters.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffsaettigungCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningCode4;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SauerstoffsaettigungConverter extends LaboratoryTestAnalyteConverter {
    public SauerstoffsaettigungConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffsaettigungCluster map() {
        SauerstoffsaettigungCluster sauerstoffsattigungCluster = new SauerstoffsaettigungCluster();
        sauerstoffsattigungCluster.setErgebnisStatusValue(mapErgebnisStatus());
        sauerstoffsattigungCluster.setUntersuchterAnalytDefiningCode(mapUntersuchterAnalyt());
        sauerstoffsattigungCluster.setAnalytResultatUnits("%");
        sauerstoffsattigungCluster.setAnalytResultatMagnitude(mapValue());
        return sauerstoffsattigungCluster;
    }

    @Override
    UntersuchterAnalytDefiningCode4 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningCode4 oxygenSaturationInBlood = UntersuchterAnalytDefiningCode4.OXYGEN_SATURATION_IN_BLOOD;
        UntersuchterAnalytDefiningCode4 oxygenSaturationInArterialBlood= UntersuchterAnalytDefiningCode4.OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(oxygenSaturationInBlood.getCode())) {
                return oxygenSaturationInBlood;
            } else if (code.equals(oxygenSaturationInArterialBlood.getCode())) {
                return oxygenSaturationInArterialBlood;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }

}
