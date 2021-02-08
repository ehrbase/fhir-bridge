package org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode3;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class KohlendioxidpartialdruckConverter extends LaboratoryTestAnalyteConverter {

    public KohlendioxidpartialdruckConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public KohlendioxidpartialdruckCluster map() {
        KohlendioxidpartialdruckCluster kohlendioxidpartialdruckCluster = new KohlendioxidpartialdruckCluster();
        kohlendioxidpartialdruckCluster.setErgebnisStatusValue(mapErgebnisStatus());
        kohlendioxidpartialdruckCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        kohlendioxidpartialdruckCluster.setAnalytResultatUnits("mmHg");
        kohlendioxidpartialdruckCluster.setAnalytResultatMagnitude(mapValue());

        return kohlendioxidpartialdruckCluster;
    }

    protected UntersuchterAnalytDefiningcode3 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode3 carbonDioxideBlood = UntersuchterAnalytDefiningcode3.CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_BLOOD;
        UntersuchterAnalytDefiningcode3 carbonDioxideArterial= UntersuchterAnalytDefiningcode3.CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningcode3 carbonDioxideCapillary = UntersuchterAnalytDefiningcode3.CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_CAPILLARY_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(carbonDioxideBlood.getCode())) {
                return carbonDioxideBlood;
            } else if (code.equals(carbonDioxideArterial.getCode())) {
                return carbonDioxideArterial;
            } else if (code.equals(carbonDioxideCapillary.getCode())) {
                return carbonDioxideCapillary;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }



}
