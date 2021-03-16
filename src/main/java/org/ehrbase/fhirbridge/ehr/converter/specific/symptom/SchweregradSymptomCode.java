package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradSymptomCode implements EnumValueSet {
    N442452003("442452003", "442452003", "SNOMED Clinical Terms", "442452003"),

    N24484000("24484000", "24484000", "SNOMED Clinical Terms", "24484000"),

    N255604002("255604002", "255604002", "SNOMED Clinical Terms", "255604002"),

    N6736007("6736007", "6736007", "SNOMED Clinical Terms", "6736007");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    SchweregradSymptomCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value ;
    }

    public String getDescription() {
        return this.description ;
    }

    public String getTerminologyId() {
        return this.terminologyId ;
    }

    public String getCode() {
        return this.code ;
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
