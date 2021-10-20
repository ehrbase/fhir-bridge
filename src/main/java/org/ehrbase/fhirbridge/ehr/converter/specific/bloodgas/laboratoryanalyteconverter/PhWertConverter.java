package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.PhWertCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckErgebnisStatusElement;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class PhWertConverter extends LaboratoryTestAnalyteConverter {

    public PhWertConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public PhWertCluster map() {
        PhWertCluster phWertCluster = new PhWertCluster();
        phWertCluster.setErgebnisStatus(mapPhClusterErgebnisStatus());
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(fhirObservation.getCode().getCoding().get(0))
                .ifPresent(phWertCluster::setBezeichnungDesAnalyts);
        convertAnalytErgebnis(phWertCluster);
        convertAnalytErgebnis(phWertCluster);
        return phWertCluster;
    }

    private List<SauerstoffpartialdruckErgebnisStatusElement> mapPhClusterErgebnisStatus() {
        SauerstoffpartialdruckErgebnisStatusElement sauerstoffpartialdruckErgebnisStatusElement = new SauerstoffpartialdruckErgebnisStatusElement();
        sauerstoffpartialdruckErgebnisStatusElement.setValue(mapErgebnisStatus());
        return List.of(sauerstoffpartialdruckErgebnisStatusElement);
    }

    @Override
    void convertAnalytErgebnis(LocatableEntity locatableEntity) {
        if (fhirObservation.hasValue()) {
            ((PhWertCluster) locatableEntity).setAnalytErgebnisUnits("pH");
            ((PhWertCluster) locatableEntity).setAnalytErgebnisMagnitude(mapValue());
        } else {
            ((PhWertCluster) locatableEntity).setAnalytErgebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

}


