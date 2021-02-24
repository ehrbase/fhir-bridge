package org.ehrbase.fhirbridge.ehr.converter.geccoDiagnose;

import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.NameDerKoerperstelleDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.NameDesProblemsDerDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.ProblemDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.SchweregradDefiningCode;

import java.util.HashMap;
import java.util.Map;

class GeccoDiagnoseCodeDefiningCodeMaps {

    private static final Map<String, KategorieDefiningCode> kategorieMap = new HashMap<>();
    private static final Map<String, ProblemDiagnoseDefiningCode> problemDiagnoseMap = new HashMap<>();
    private static final Map<String, NameDesProblemsDerDiagnoseDefiningCode> nameDesProblemDiagnoseMap = new HashMap<>();
    private static final Map<String, NameDerKoerperstelleDefiningCode> koerperstelleMap = new HashMap<>();
    private static final Map<String, SchweregradDefiningCode> schweregradMap = new HashMap<>();

    static {
        for (KategorieDefiningCode kategorie : KategorieDefiningCode.values()) {
            kategorieMap.put(kategorie.getCode(), kategorie);
        }

        for (ProblemDiagnoseDefiningCode problem : ProblemDiagnoseDefiningCode.values()) {
            problemDiagnoseMap.put(problem.getCode(), problem);
        }

        for (NameDesProblemsDerDiagnoseDefiningCode problem : NameDesProblemsDerDiagnoseDefiningCode.values()) {
            nameDesProblemDiagnoseMap.put(problem.getCode(), problem);
        }

        for (NameDerKoerperstelleDefiningCode koerperstelleDefiningCode : NameDerKoerperstelleDefiningCode.values()) {
            koerperstelleMap.put(koerperstelleDefiningCode.getCode(), koerperstelleDefiningCode);
        }

        for (SchweregradDefiningCode schweregrad : SchweregradDefiningCode.values()) {
            schweregradMap.put(schweregrad.getCode(), schweregrad);
        }
    }

    static Map<String, KategorieDefiningCode> getKategorieMap() {
        return kategorieMap;
    }

    static Map<String, ProblemDiagnoseDefiningCode> getProblemDiagnoseMap() {
        return problemDiagnoseMap;
    }

    static Map<String, NameDesProblemsDerDiagnoseDefiningCode> getNameDesProblemDiagnoseMap() {
        return nameDesProblemDiagnoseMap;
    }

    static Map<String, NameDerKoerperstelleDefiningCode> getKoerperstelleMap() {
        return koerperstelleMap;
    }

    static Map<String, SchweregradDefiningCode> getSchweregradMap() {
        return schweregradMap;
    }
}
