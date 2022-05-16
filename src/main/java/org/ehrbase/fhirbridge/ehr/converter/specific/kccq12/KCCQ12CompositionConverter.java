package org.ehrbase.fhirbridge.ehr.converter.specific.kccq12;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.UCCAppFragebogenDatenComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class KCCQ12CompositionConverter extends QuestionnaireResponseToCompositionConverter<UCCAppFragebogenDatenComposition> {

    @Override
    protected UCCAppFragebogenDatenComposition convertInternal(QuestionnaireResponse questionnaireResponse) {
        UCCAppFragebogenDatenComposition uccAppFragebogenDatenComposition = new UCCAppFragebogenDatenComposition();
        uccAppFragebogenDatenComposition.setGesamtergebnis(new GesamtErgebnisConverter().convert(questionnaireResponse));
        return uccAppFragebogenDatenComposition;
    }
}
