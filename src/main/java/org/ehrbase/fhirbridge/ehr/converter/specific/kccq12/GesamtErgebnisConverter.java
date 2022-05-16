package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage1ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage2ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage3ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage4ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage5ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage6ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage7ClusterConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter.Frage8ClusterConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.GesamtergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.GesamtstatusCluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class GesamtErgebnisConverter extends QuestionnaireResponseToObservationConverter<GesamtergebnisObservation> {

    @Override
    protected GesamtergebnisObservation convertInternal(QuestionnaireResponse questionnaireResponse) {
        GesamtergebnisObservation gesamtergebnisObservation = new GesamtergebnisObservation();
        gesamtergebnisObservation.setGesamtstatus(mapGesamtstatusCluster(questionnaireResponse));
        return gesamtergebnisObservation;
    }

    private GesamtstatusCluster mapGesamtstatusCluster(QuestionnaireResponse questionnaireResponse) {
        GesamtstatusCluster gesamtstatusCluster = new GesamtstatusCluster();
        for(QuestionnaireResponse.QuestionnaireResponseItemComponent item: questionnaireResponse.getItem()){
            switch (item.getLinkId()) {
                case "1":
                    gesamtstatusCluster.setFrage1(new Frage1ClusterConverter().convert(item));
                    break;
                case "2":
                    gesamtstatusCluster.setFrage2(new Frage2ClusterConverter().convert(item));
                    break;
                case "3":
                    gesamtstatusCluster.setFrage3(new Frage3ClusterConverter().convert(item));
                    break;
                case "4":
                    gesamtstatusCluster.setFrage4(new Frage4ClusterConverter().convert(item));
                    break;
                case "5":
                    gesamtstatusCluster.setFrage5(new Frage5ClusterConverter().convert(item));
                    break;
                case "6":
                    gesamtstatusCluster.setFrage6(new Frage6ClusterConverter().convert(item));
                    break;
                case "7":
                    gesamtstatusCluster.setFrage7(new Frage7ClusterConverter().convert(item));
                    break;
                case "8":
                    gesamtstatusCluster.setFrage8(new Frage8ClusterConverter().convert(item));
                    break;
                default:
                   throw new ConversionException("Question number (LinkId)  is not supported!");
            }
        }
        return gesamtstatusCluster;
    }


}
