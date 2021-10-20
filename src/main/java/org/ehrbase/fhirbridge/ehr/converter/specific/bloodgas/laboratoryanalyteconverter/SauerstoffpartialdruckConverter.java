package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckErgebnisStatusElement;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class SauerstoffpartialdruckConverter extends LaboratoryTestAnalyteConverter {
    public SauerstoffpartialdruckConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffpartialdruckCluster map() {
        SauerstoffpartialdruckCluster sauerstoffpartialdruckCluster = new SauerstoffpartialdruckCluster();
        sauerstoffpartialdruckCluster.setErgebnisStatus(mapSauerstoffpartialdruckErgebnisStatus());
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(fhirObservation.getCode().getCoding().get(0))
                .ifPresent(sauerstoffpartialdruckCluster::setBezeichnungDesAnalyts);
        convertAnalytErgebnis(sauerstoffpartialdruckCluster);
        return sauerstoffpartialdruckCluster;
    }

    @Override
    void convertAnalytErgebnis(LocatableEntity locatableEntity) {
        if (fhirObservation.hasValue()) {
            ((SauerstoffpartialdruckCluster) locatableEntity).setAnalytErgebnisUnits("mmHg");
            ((SauerstoffpartialdruckCluster) locatableEntity).setAnalytErgebnisMagnitude(mapValue());
        } else {
            ((SauerstoffpartialdruckCluster) locatableEntity).setAnalytErgebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private List<SauerstoffpartialdruckErgebnisStatusElement> mapSauerstoffpartialdruckErgebnisStatus() {
        SauerstoffpartialdruckErgebnisStatusElement sauerstoffpartialdruckErgebnisStatusElement = new SauerstoffpartialdruckErgebnisStatusElement();
        sauerstoffpartialdruckErgebnisStatusElement.setValue(mapErgebnisStatus());
        return List.of(sauerstoffpartialdruckErgebnisStatusElement);
    }
}

