package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.SauerstoffsaettigungCluster;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class SauerstoffsaettigungConverter extends LaboratoryTestAnalyteConverter {
    public SauerstoffsaettigungConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffsaettigungCluster map() {
        SauerstoffsaettigungCluster sauerstoffsaettigungCluster = new SauerstoffsaettigungCluster();
        sauerstoffsaettigungCluster.setErgebnisStatus(mapSauerstoffsaettigungErgebnisStatus());
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(fhirObservation.getCode().getCoding().get(0))
                .ifPresent(sauerstoffsaettigungCluster::setBezeichnungDesAnalyts);
        convertAnalytErgebnis(sauerstoffsaettigungCluster);
        return sauerstoffsaettigungCluster;

    }

    @Override
    void convertAnalytErgebnis(LocatableEntity locatableEntity) {
        if (fhirObservation.hasValue()) {
            ((SauerstoffsaettigungCluster) locatableEntity).setAnalytErgebnisUnits("%");
            ((SauerstoffsaettigungCluster) locatableEntity).setAnalytErgebnisMagnitude(mapValue());
        } else {
            ((SauerstoffsaettigungCluster) locatableEntity).setAnalytErgebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private List<SauerstoffpartialdruckErgebnisStatusElement> mapSauerstoffsaettigungErgebnisStatus() {
        SauerstoffpartialdruckErgebnisStatusElement sauerstoffpartialdruckErgebnisStatusElement = new SauerstoffpartialdruckErgebnisStatusElement();
        sauerstoffpartialdruckErgebnisStatusElement.setValue(mapErgebnisStatus());
        return List.of(sauerstoffpartialdruckErgebnisStatusElement);
    }

}
