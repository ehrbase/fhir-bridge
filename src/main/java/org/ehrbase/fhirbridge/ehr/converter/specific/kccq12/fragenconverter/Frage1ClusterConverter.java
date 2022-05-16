package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage1Cluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage1aCluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage1bCluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage1cCluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage1ClusterConverter implements FrageClusterConverterI<Frage1Cluster> {

    @Override
    public Frage1Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent responseItem) {
        Frage1Cluster frage1Cluster = new Frage1Cluster();
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem : responseItem.getItem()) {
            switch (nestedItem.getLinkId()) {
                case "1a":
                    frage1Cluster.setFrage1a(convertQuestion1a(nestedItem));
                    break;
                case "1b":
                    frage1Cluster.setFrage1b(convertQuestion1b(nestedItem));
                    break;
                case "1c":
                    frage1Cluster.setFrage1c(convertQuestion1c(nestedItem));
                    break;
                default:
                    throw new ConversionException("Question number (LinkId)  is not supported!");
            }
        }
        return frage1Cluster;
    }

    private Frage1aCluster convertQuestion1a(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage1aCluster frage1aCluster = new Frage1aCluster();
        frage1aCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage1aCluster;
    }

    private Frage1bCluster convertQuestion1b(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage1bCluster frage1bCluster = new Frage1bCluster();
        frage1bCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage1bCluster;
    }

    private Frage1cCluster convertQuestion1c(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage1cCluster frage1cCluster = new Frage1cCluster();
        frage1cCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage1cCluster;
    }

}
