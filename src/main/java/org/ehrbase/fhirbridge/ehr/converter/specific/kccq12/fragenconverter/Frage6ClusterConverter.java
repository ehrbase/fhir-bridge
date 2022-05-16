package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode5;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage6Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage6ClusterConverter implements FrageClusterConverterI<Frage6Cluster> {
    @Override
    public Frage6Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage6Cluster frage6Cluster = new Frage6Cluster();
        frage6Cluster.setAntwortDefiningCode(AntwortDefiningCode5.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage6Cluster;
    }
}
