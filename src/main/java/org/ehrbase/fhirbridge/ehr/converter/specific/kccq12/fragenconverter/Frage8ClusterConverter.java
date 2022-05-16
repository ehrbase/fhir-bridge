package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage8Cluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage8aCluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage8bCluster;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage8cCluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage8ClusterConverter implements FrageClusterConverterI<Frage8Cluster> {
    @Override
    public Frage8Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent responseItem) {
        Frage8Cluster frage8Cluster = new Frage8Cluster();
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem : responseItem.getItem()) {
            switch (nestedItem.getLinkId()) {
                case "8a":
                    frage8Cluster.setFrage8a(convertQuestion8a(nestedItem));
                    break;
                case "8b":
                    frage8Cluster.setFrage8b(convertQuestion8b(nestedItem));
                    break;
                case "8c":
                    frage8Cluster.setFrage8c(convertQuestion8c(nestedItem));
                    break;
                default:
                    throw new ConversionException("Question number (LinkId)  is not supported!");
            }
        }
        return frage8Cluster;
    }

    private Frage8aCluster convertQuestion8a(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage8aCluster frage8aCluster = new Frage8aCluster();
        frage8aCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage8aCluster;
    }

    private Frage8bCluster convertQuestion8b(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage8bCluster frage8bCluster = new Frage8bCluster();
        frage8bCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage8bCluster;
    }

    private Frage8cCluster convertQuestion8c(QuestionnaireResponse.QuestionnaireResponseItemComponent nestedItem) {
        Frage8cCluster frage8cCluster = new Frage8cCluster();
        frage8cCluster.setAntwortDefiningCode(AntwortDefiningCode.getCodesAsMap().get(nestedItem.getAnswer().get(0).getValueCoding().getCode()));
        return frage8cCluster;
    }

}
