package org.ehrbase.fhirbridge.ihe.xds.converter;

import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;

public abstract class ITI41Converter {

    protected static Identifiable getPatientId(Reference subject) {
        if(subject.hasReference()){
            return new Identifiable(subject.getReference().replace("Patient/",""));
        }else{
            return new Identifiable(subject.getIdentifier().getValue());
        }
    }
}
