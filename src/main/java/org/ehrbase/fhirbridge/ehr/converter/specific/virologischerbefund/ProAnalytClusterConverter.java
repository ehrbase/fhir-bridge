package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.VirusnachweistestDefiningCode;


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
            proAnalytCluster.setVirusnachweistestDefiningCode(getVirusCode(loop.getCode()));
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
            proAnalytCluster.setNachweisDefiningCode(getNachweisCode(observation.getValueCodeableConcept().getCoding().get(0).getCode()));
        } else if (observation.hasValueQuantity()) {
            List<ProAnalytQuantitativesErgebnisElement>  proAnalytQuantitativesErgebnisElementList = new ArrayList<>();
            proAnalytQuantitativesErgebnisElementList.add(new ProAnalytQuantitativesErgebnisElementConverter().convert(observation));
            proAnalytCluster.setQuantitativesErgebnis(proAnalytQuantitativesErgebnisElementList);
            proAnalytCluster.setNachweisDefiningCode(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE);
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

    private VirusnachweistestDefiningCode getVirusCode (String code){
        if(VirusnachweistestDefiningCode.getCodesAsMap().containsKey(code)){
            return VirusnachweistestDefiningCode.getCodesAsMap().get(code);
        } else{
            throw new ConversionException("Value code for Virusnachweistest is not supported");
        }
    }

    private NachweisDefiningCode getNachweisCode (String code){
        if(NachweisDefiningCode.getCodesAsMap().containsKey(code)){
            return NachweisDefiningCode.getCodesAsMap().get(code);
        } else{
            throw new ConversionException("Value code for Nachweistest is not supported");
        }
    }

}
