package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class D4lQuestionnaireCompositionConverter implements CompositionConverter<D4LQuestionnaireComposition, QuestionnaireResponse> {


    @Override
    public QuestionnaireResponse fromComposition(D4LQuestionnaireComposition composition) throws CompositionConversionException {
        //TODO Implement
        return null;
    }

    @Override
    public D4LQuestionnaireComposition toComposition(QuestionnaireResponse object) throws CompositionConversionException {

        return new D4LQuestionnaireComposition();
    }
}
