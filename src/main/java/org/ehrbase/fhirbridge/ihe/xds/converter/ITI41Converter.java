package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Reference;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;

public abstract class ITI41Converter {

    protected static Identifiable getPatientId(Reference subject) {
        if(subject.hasReference()){
            return new Identifiable(subject.getReference().replace("Patient/",""));
        }else{
            try {
                return new Identifiable(subject.getIdentifier().getValue(), new Oid(subject.getIdentifier().getSystem()));
            } catch (GSSException e) {
                throw new UnprocessableEntityException("System of patient identifier is not a valid OID.");
            }
        }
    }
}
