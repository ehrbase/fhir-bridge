package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.specific.TerminologyIdCode;


public enum SofaScoreCode {

    ATEMFREQUENZ_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("PaO₂/FiO₂ ≥ 400 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0005")))),
    ATEMFREQUENZ_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("PaO₂/FiO₂ 300-399 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0006")))),
    ATEMFREQUENZ_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("PaO₂/FiO₂ 200-299 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0007")))),
    ATEMFREQUENZ_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("Mechanisch belüftet und PaO₂/FiO₂ 100-199 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0008")))),
    ATEMFREQUENZ_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("Mechanisch belüftet und PaO₂/FiO₂ < 100 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0009")))),

    HERZKREISLAUFSYSTEM_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("MAD ≥ 70 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0011")))),
    HERZKREISLAUFSYSTEM_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("MAD < 70 mmHg", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0012")))),
    HERZKREISLAUFSYSTEM_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("DA ≤ 5; Dobutamin", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0013")))),
    HERZKREISLAUFSYSTEM_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("DA > 5; NA/A ≤ 0,1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0014")))),
    HERZKREISLAUFSYSTEM_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("NA/A > 0,1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0015")))),

    NERVENSYSTEM_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("GCS 15", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0017")))),
    NERVENSYSTEM_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("GCS 13 - 14", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0018")))),
    NERVENSYSTEM_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("GCS 10 - 12", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0019")))),
    NERVENSYSTEM_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("GCS 6 - 9", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0020")))),
    NERVENSYSTEM_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("GCS < 6", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0021")))),

    NIERENFUNKTIONS_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("Kreatinin < 1,2 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0023")))),
    NIERENFUNKTIONS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("Kreatinin 1,2-1,9 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0024")))),
    NIERENFUNKTIONS_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("Kreatinin 2,0-3,4 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0025")))),
    NIERENFUNKTIONS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("Kreatinin 3,5-4,9 mg/dL oder UOP < 500 mL/24h", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0026")))),
    NIERENFUNKTIONS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("Kreatinin ≥ 5,0 mg/dL oder UOP < 200 mL/24h", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0027")))),


    LEBERFUNKTIONS_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("Bilirubin < 1,2 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0029")))),
    LEBERFUNKTIONS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("Bilirubin 1,2-1,9 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0030")))),
    LEBERFUNKTIONS_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("Bilirubin 2,0-5,9 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0031")))),
    LEBERFUNKTIONS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("Bilirubin 6,0-11,9 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0032")))),
    LEBERFUNKTIONS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("Bilirubin ≥ 12,0 mg/dL", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0033")))),

    BLUTGERINNUNGS_SCORE_0(new DvOrdinal(0L,
            new DvCodedText("Thrombozyten ≥ 150 (x10³/µL)", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0035")))),
    BLUTGERINNUNGS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("Thrombozyten < 150 (x10³/µL)", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0036")))),
    BLUTGERINNUNGS_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("Thrombozyten < 100 (x10³/µL)", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0037")))),
    BLUTGERINNUNGS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("Thrombozyten < 50 (x10³/µL)", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0038")))),
    BLUTGERINNUNGS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("Thrombozyten < 20 (x10³/µL)", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0039"))));


    private final DvOrdinal value;

    SofaScoreCode(DvOrdinal dvOrdinal) {
        this.value = dvOrdinal;
    }

    public DvOrdinal getValue() {
        return value;
    }


}