package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode3;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage4Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage4ClusterConverter implements FrageClusterConverterI<Frage4Cluster> {
    @Override
    public Frage4Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage4Cluster frage4Cluster = new Frage4Cluster();
        frage4Cluster.setAntwortDefiningCode(AntwortDefiningCode3.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage4Cluster;
    }
}
