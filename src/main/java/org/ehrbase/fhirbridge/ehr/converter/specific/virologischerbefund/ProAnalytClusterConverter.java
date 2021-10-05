package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProAnalytClusterConverter {

    public ProAnalytCluster convert(Observation observation) throws FHIRException {

        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();

        for (Coding loop : observation.getCode().getCoding()){
            if (loop.hasDisplay() && loop.hasCode()){
                Optional.of(new DvCodedText(loop.getDisplay(), new CodePhrase(new TerminologyId("LOINC", ""), loop.getCode()))).ifPresent(proAnalytCluster::setVirusnachweistest);
            }
        }

        mapValue(observation,proAnalytCluster);

        List <ProAnalytErgebnisStatusElement> proAnalytErgebnisStatusElementList = new ArrayList<>();
        proAnalytErgebnisStatusElementList.add(new ProAnalytErgebnisStatusElementConverter().convert(observation));
        proAnalytCluster.setErgebnisStatus(proAnalytErgebnisStatusElementList);

        mapZugehoerigeLaborprobe(observation).ifPresent(proAnalytCluster::setZugehoerigeLaborprobe);

        return proAnalytCluster;
    }

    private void mapValue (Observation observation, ProAnalytCluster proAnalytCluster){
        if (observation.hasValueCodeableConcept()){
            DvCodedTextParser.parseFHIRCoding(observation.getValueCodeableConcept().getCoding().get(0)).ifPresent(proAnalytCluster::setNachweis);
        } else if (observation.hasValueQuantity()) {
            List<ProAnalytQuantitativesErgebnisElement>  proAnalytQuantitativesErgebnisElementList = new ArrayList<>();
            proAnalytQuantitativesErgebnisElementList.add(new ProAnalytQuantitativesErgebnisElementConverter().convert(observation));
            proAnalytCluster.setQuantitativesErgebnis(proAnalytQuantitativesErgebnisElementList);
            proAnalytCluster.setNachweis(new DvCodedText("Detected (qualifier value)", new CodePhrase(new TerminologyId("SNOMED Clinical Terms", ""), "260373001")));
        } else{
            throw new ConversionException("Observation needs either ValueCodeableConcept or ValueQuantity.");
        }
    }

    private Optional<ProAnalytZugehoerigeLaborprobeChoice> mapZugehoerigeLaborprobe (Observation observation) {
        if (observation.getSpecimen().hasIdentifier()){
            ProAnalytZugehoerigeLaborprobeChoice proAnalytZugehoerigeLaborprobeChoice = new ProAnalytZugehoerigeLaborprobeChoiceConverter().convertDvIdentifier(observation);
            return Optional.of(proAnalytZugehoerigeLaborprobeChoice);
        }else if (observation.getSpecimen().hasTypeElement()){
            ProAnalytZugehoerigeLaborprobeChoice proAnalytZugehoerigeLaborprobeChoice2 = new ProAnalytZugehoerigeLaborprobeChoiceConverter().convertDvUri(observation);
            return Optional.of(proAnalytZugehoerigeLaborprobeChoice2);
        }else {
            return Optional.empty();
        }
    }
}
