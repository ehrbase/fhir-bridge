package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.converterCodes.TerminologyIdCode;

public enum ClinicalFrailtyBerurteilung {

    SEHR_FIT(new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0005")))),
    DURCHSCHNITTLICH_AKTIV(new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0006")))),
    GUT_ZURECHTKOMMEND(new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0007")))),
    VULNERABEL(new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0008")))),
    GERINGGRADIG_FRAIL(new DvOrdinal(5L,
            new DvCodedText("5", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0009")))),
    MITTELGRADIG_FRAIL(new DvOrdinal(6L,
            new DvCodedText("6", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0010")))),
    AUSGEPRAEGT_FRAIL(new DvOrdinal(7L,
            new DvCodedText("7", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0011")))),
    EXTREM_FRAIL(new DvOrdinal(8L,
            new DvCodedText("8", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0012")))),
    TERMINAL_ERKRANKT(new DvOrdinal(9L,
            new DvCodedText("9", new CodePhrase(new TerminologyId(TerminologyIdCode.LOCAL.getTerminologyId()), "at0013"))));

    private final DvOrdinal berurteilung;

    ClinicalFrailtyBerurteilung(DvOrdinal berurteilung) {
        this.berurteilung = berurteilung;
    }

    public DvOrdinal getBerurteilung() {
        return berurteilung;
    }
}
