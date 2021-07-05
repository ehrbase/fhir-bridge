package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum RauchverhaltenDefiningCode implements EnumValueSet {
    LA189763("Current every day smoker", "Current every day smoker", "LOINC", "LA18976-3"),

    LA189789("Never smoker", "Never smoker", "LOINC", "LA18978-9"),

    LA159204("Former smoker", "Former smoker", "LOINC", "LA15920-4"),

    LA189805("Unknown if ever smoked", "Unknown if ever smoked", "LOINC", "LA18980-5");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    RauchverhaltenDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }

    public DvCodedText toDvCodedText(){
        DvCodedText dvCodedText = new DvCodedText();
        CodePhrase codePhrase = new CodePhrase();
        codePhrase.setCodeString(code);
        codePhrase.setTerminologyId(new TerminologyId(terminologyId));
        dvCodedText.setDefiningCode(codePhrase);
        dvCodedText.setValue(value);
        return dvCodedText;
    }

}
