package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode4;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage5Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage5ClusterConverter implements FrageClusterConverterI<Frage5Cluster> {
    @Override
    public Frage5Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage5Cluster frage5Cluster = new Frage5Cluster();
        frage5Cluster.setAntwortDefiningCode(AntwortDefiningCode4.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage5Cluster;
    }
}
