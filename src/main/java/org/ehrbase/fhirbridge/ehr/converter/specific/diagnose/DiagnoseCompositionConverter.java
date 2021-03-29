package org.ehrbase.fhirbridge.ehr.converter.specific.diagnose;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.DiagnoseComposition;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

public class DiagnoseCompositionConverter extends ConditionToCompositionConverter<DiagnoseComposition> {

    @Override
    public DiagnoseComposition convertInternal(@NonNull Condition resource) {
        DiagnoseComposition composition = new DiagnoseComposition();
        composition.setProblemDiagnose(new ProblemDiagnoseEvaluationConverter().convert(resource));
        return composition;
    }
}
