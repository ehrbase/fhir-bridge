package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12.fragenconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public interface FrageClusterConverterI <F extends LocatableEntity> {
    F convert(QuestionnaireResponse.QuestionnaireResponseItemComponent item);
}
