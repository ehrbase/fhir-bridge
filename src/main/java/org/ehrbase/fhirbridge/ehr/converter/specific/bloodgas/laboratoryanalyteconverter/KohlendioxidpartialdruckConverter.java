package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckCluster;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckErgebnisStatusElement;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class KohlendioxidpartialdruckConverter extends LaboratoryTestAnalyteConverter {

    public KohlendioxidpartialdruckConverter(Observation fhirObservation) {
        super(fhirObservation);
    }

    public KohlendioxidpartialdruckCluster map() {
        KohlendioxidpartialdruckCluster kohlendioxidpartialdruckCluster = new KohlendioxidpartialdruckCluster();
        kohlendioxidpartialdruckCluster.setErgebnisStatus(mapKohlendioxidErgebnisStatus());
        DvCodedTextParser.parseFHIRCoding(fhirObservation.getCode().getCoding().get(0)).ifPresent(kohlendioxidpartialdruckCluster::setBezeichnungDesAnalyts);
        convertAnalytErgebnis(kohlendioxidpartialdruckCluster);
        return kohlendioxidpartialdruckCluster;
    }

    @Override
    void convertAnalytErgebnis(LocatableEntity locatableEntity) {
        if(fhirObservation.hasValue()){
            ((KohlendioxidpartialdruckCluster) locatableEntity).setAnalytErgebnisUnits("mmHg");
            ((KohlendioxidpartialdruckCluster) locatableEntity).setAnalytErgebnisMagnitude(mapValue());
        }else{
            ((KohlendioxidpartialdruckCluster) locatableEntity).setAnalytErgebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private List<KohlendioxidpartialdruckErgebnisStatusElement> mapKohlendioxidErgebnisStatus() {
        KohlendioxidpartialdruckErgebnisStatusElement kohlendioxidpartialdruckErgebnisStatusElement = new KohlendioxidpartialdruckErgebnisStatusElement();
        kohlendioxidpartialdruckErgebnisStatusElement.setValue(mapErgebnisStatus());
        return List.of(kohlendioxidpartialdruckErgebnisStatusElement);
    }
}
