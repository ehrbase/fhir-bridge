package org.ehrbase.fhirbridge.ehr.converter.sofascore;

public enum SofaScoreTerminologyIds {

    LOCAL("local");

    private final String terminologyId;

    SofaScoreTerminologyIds(String terminologyId){
        this.terminologyId = terminologyId;
    }

    public String getTerminologyId(){
        return terminologyId;
    }

}
