package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.DiabetesEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.HerzerkrankungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.VorhandenDefiningCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class VorhandenerDefiningCodeConverter {

    static void setVorhandenerDefiningCode(String code, Object evaluationObject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method vorhandenerDefiningCodeSetter = getSetMethodForVorhandenDefiningCode(evaluationObject);
        if (code.equals(VorhandenDefiningCode.JA.getCode())) {
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningCode.JA);
        } else if (code.equals(VorhandenDefiningCode.NEIN.getCode())) {
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningCode.NEIN);
        } else if (code.equals(VorhandenDefiningCode.ICH_WEISS_ES_NICHT.getCode())) {
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningCode.ICH_WEISS_ES_NICHT);
        } else {
            throw new ConversionException("The code " + code + " for Question: " + evaluationObject.getClass().getName().split("\\.")[6].replace("Evaluation", "") + " cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
        }
    }

    private static Method getSetMethodForVorhandenDefiningCode(Object evaluationObject) throws NoSuchMethodException {
        if (evaluationObject.getClass() == DiabetesEvaluation.class) {
            return DiabetesEvaluation.class.getDeclaredMethod("setVorhandenDefiningCode", VorhandenDefiningCode.class);
        } else if (evaluationObject.getClass() == ChronischeLungenkrankheitEvaluation.class) {
            return ChronischeLungenkrankheitEvaluation.class.getDeclaredMethod("setVorhandenDefiningCode", VorhandenDefiningCode.class);
        } else if (evaluationObject.getClass() == HerzerkrankungEvaluation.class) {
            return HerzerkrankungEvaluation.class.getDeclaredMethod("setVorhandenDefiningCode", VorhandenDefiningCode.class);
        } else if (evaluationObject.getClass() == AdipositasEvaluation.class) {
            return AdipositasEvaluation.class.getDeclaredMethod("setVorhandenDefiningCode", VorhandenDefiningCode.class);
        } else {
            throw new NoSuchMethodException("Class " + evaluationObject.getClass().getCanonicalName() + " is not supported for this method, only the classes DiabetesEvaluation, ChronischeLungenkrankheitEvaluation, HerzerkrankungEvaluation and AdipositasEvaluation are");
        }
    }
}
