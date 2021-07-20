package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.VirusnachweistestDefiningCode;


import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class ProAnalytClusterConverter {

    public ProAnalytCluster convert(Observation observation) throws FHIRException {

        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();

        mapVirusnachweistest(observation, proAnalytCluster);

        if (observation.hasValueCodeableConcept()){
            mapNachweistest(observation, proAnalytCluster);
        } else if (observation.hasValueQuantity()) {
            List<ProAnalytQuantitativesErgebnisElement>  proAnalytQuantitativesErgebnisElementList = new ArrayList<>();
            proAnalytQuantitativesErgebnisElementList.add(new ProAnalytQuantitativesErgebnisElementConverter().convert(observation));
            proAnalytCluster.setQuantitativesErgebnis(proAnalytQuantitativesErgebnisElementList);
            proAnalytCluster.setNachweisDefiningCode(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE);
        } else{
            throw new ConversionException("Observation needs either ValueCodeableConcept or ValueQuantity.");
        }

        List <ProAnalytErgebnisStatusElement> proAnalytErgebnisStatusElementList = new ArrayList<>();
        proAnalytErgebnisStatusElementList.add(new ProAnalytErgebnisStatusElementConverter().convert(observation));
        proAnalytCluster.setErgebnisStatus(proAnalytErgebnisStatusElementList);

        /**
         * ZugehoerigeLaborprobe is optional in openEHR and will only be mapped if one of the required resources is present.
         */
        if (observation.getSpecimen().hasIdentifier()){
            ProAnalytZugehoerigeLaborprobeChoice proAnalytZugehoerigeLaborprobeChoice = new ProAnalytZugehoerigeLaborprobeChoiceConverter().convertDvIdentifier(observation);
            proAnalytCluster.setZugehoerigeLaborprobe(proAnalytZugehoerigeLaborprobeChoice);
        }else if (observation.getSpecimen().hasTypeElement()){
            ProAnalytZugehoerigeLaborprobeChoice proAnalytZugehoerigeLaborprobeChoice2 = new ProAnalytZugehoerigeLaborprobeChoiceConverter().convertDvUri(observation);
            proAnalytCluster.setZugehoerigeLaborprobe(proAnalytZugehoerigeLaborprobeChoice2);
        }

        return proAnalytCluster;
    }

    private void mapVirusnachweistest(Observation observation, ProAnalytCluster proAnalytCluster){
        if (!observation.getCode().hasCoding() || observation.getCode().getCoding().size() != 1){
            throw new ConversionException("Code of Observation needs to have exactly one instance of Coding.");
        }
        switch (observation.getCode().getCoding().get(0).getCode()){
            case "94500-6":
                proAnalytCluster.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
                break;
            case "94558-4":
                proAnalytCluster.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_AG);
                break;
            case "94745-7":
                proAnalytCluster.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_CYCLE_THRESHOLD_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
                break;
            default:
                throw new ConversionException("Value code Virusnachweistest " + observation.getCode().getCoding().get(0).getCode() + " is not supported");
        }
    }

    private void mapNachweistest(Observation observation, ProAnalytCluster proAnalytCluster) throws FHIRException {
        if (observation.getValueCodeableConcept().getCoding().size() != 1){
            throw new ConversionException("ValueCodeableConcept of Observation needs to have exactly one instance of Coding.");
        }
        switch(observation.getValueCodeableConcept().getCoding().get(0).getCode()){
            case "260373001":
                proAnalytCluster.setNachweisDefiningCode(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE);
                break;
            case "419984006":
                proAnalytCluster.setNachweisDefiningCode(NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE);
                break;
            case "260415000":
                proAnalytCluster.setNachweisDefiningCode(NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE);
                break;
            default:
                throw new ConversionException("Value code Nachweistest " + observation.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }
    }

}