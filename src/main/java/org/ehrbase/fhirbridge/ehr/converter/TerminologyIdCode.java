package org.ehrbase.fhirbridge.ehr.converter;

public enum TerminologyIdCode {

    LOCAL("local");

    private final String terminologyId;

    TerminologyIdCode(String terminologyId){
        this.terminologyId = terminologyId;
    }

    public String getTerminologyId(){
        return terminologyId;
    }

}
