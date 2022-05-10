package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode3;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage3Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage3ClusterConverter implements FrageClusterConverterI<Frage3Cluster> {
    @Override
    public Frage3Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage3Cluster frage3Cluster = new Frage3Cluster();
        frage3Cluster.setAntwortDefiningCode(AntwortDefiningCode3.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage3Cluster;
    }
}
