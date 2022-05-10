package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.AntwortDefiningCode2;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.Frage2Cluster;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class Frage2ClusterConverter implements FrageClusterConverterI<Frage2Cluster> {

    @Override
    public Frage2Cluster convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item) {
        Frage2Cluster frage2Cluster = new Frage2Cluster();
        frage2Cluster.setAntwortDefiningCode(AntwortDefiningCode2.getCodesAsMap().get(item.getAnswer().get(0).getValueCoding().getCode()));
        return frage2Cluster;
    }
}
