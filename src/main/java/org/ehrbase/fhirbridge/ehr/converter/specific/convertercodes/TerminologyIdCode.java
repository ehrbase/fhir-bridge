package org.ehrbase.fhirbridge.ehr.converter.specific.convertercodes;

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
