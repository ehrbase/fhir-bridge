package org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffsattigungCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode4;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SauerstoffsaettigungConverter extends LaboratoryTestAnalyteConverter {
    public SauerstoffsaettigungConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffsattigungCluster map() {
        SauerstoffsattigungCluster sauerstoffsattigungCluster = new SauerstoffsattigungCluster();
        sauerstoffsattigungCluster.setErgebnisStatusValue(mapErgebnisStatus());
        sauerstoffsattigungCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        sauerstoffsattigungCluster.setAnalytResultatUnits("%");
        sauerstoffsattigungCluster.setAnalytResultatMagnitude(mapValue());
        return sauerstoffsattigungCluster;
    }

    @Override
    UntersuchterAnalytDefiningcode4 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode4 oxygenSaturationInBlood = UntersuchterAnalytDefiningcode4.OXYGEN_SATURATION_IN_BLOOD;
        UntersuchterAnalytDefiningcode4 oxygenSaturationInArterialBlood= UntersuchterAnalytDefiningcode4.OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;

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
