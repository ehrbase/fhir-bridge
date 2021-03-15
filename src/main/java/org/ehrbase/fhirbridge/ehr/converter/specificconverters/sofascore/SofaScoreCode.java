package org.ehrbase.fhirbridge.ehr.converter.specificconverters.sofascore;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.specificconverters.convertercodes.TerminologyIdCode;

public enum SofaScoreCode {

    ATEMFREQUENZ_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0016")))),
    ATEMFREQUENZ_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0017")))),
    ATEMFREQUENZ_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0018")))),
    ATEMFREQUENZ_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0019")))),
    NERVENSYSTEM_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0020")))),
    NERVENSYSTEM_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0021")))),
    NERVENSYSTEM_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0022")))),
    NERVENSYSTEM_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0023")))),
    HERZKREISLAUFSYSTEM_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0024")))),
    HERZKREISLAUFSYSTEM_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0025")))),
    HERZKREISLAUFSYSTEM_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0026")))),
    HERZKREISLAUFSYSTEM_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0027")))),
    LEBERFUNKTIONS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0028")))),
    LEBERFUNKTIONS_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0029")))),
    LEBERFUNKTIONS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0030")))),
    LEBERFUNKTIONS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0031")))),
    BLUTGERINNUNGS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0032")))),
    BLUTGERINNUNGS_SCORE_2(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0033")))),
    BLUTGERINNUNGS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0034")))),
    BLUTGERINNUNGS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0035")))),
    NIERENFUNKTIONS_SCORE_1(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0036")))),
    NIERENFUNKTIONS_SCORE_2(new DvOrdinal(2L, new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0037")))),
    NIERENFUNKTIONS_SCORE_3(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0038")))),
    NIERENFUNKTIONS_SCORE_4(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0039"))));


    private final DvOrdinal value;

    SofaScoreCode(DvOrdinal dvOrdinal) {
        this.value = dvOrdinal;
    }

    public DvOrdinal getValue() {
        return value;
    }


}
