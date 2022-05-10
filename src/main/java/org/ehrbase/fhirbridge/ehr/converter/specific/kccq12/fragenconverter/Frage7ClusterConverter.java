package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode6;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage7Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage7ClusterConverter implements FrageClusterConverterI<Frage7Cluster> {
    @Override
    public Frage7Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage7Cluster frage7Cluster = new Frage7Cluster();
        frage7Cluster.setAntwortDefiningCode(AntwortDefiningCode6.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage7Cluster;
    }
}
