package org.ehrbase.fhirbridge.ihe.xds.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ITI41Converter {
    private static final Logger LOG = LoggerFactory.getLogger(ITI41Converter.class);

    protected static Identifiable getPatientId(Reference subject) {
        if(subject.hasReference()){
            return new Identifiable(subject.getReference().replace("Patient/",""));
        }else{
           return convertPatientIdentifierToIdentifiable(subject.getIdentifier());
        }
    }

    protected static Identifiable convertPatientIdentifierToIdentifiable(Identifier identifier){
        try {
            return new Identifiable(identifier.getValue(),
                    new Oid(replaceUrnOid(identifier.getSystem())));
        } catch (GSSException e) {
            LOG.error("System of patient identifier is not a valid OID error:" + e);
            throw new UnprocessableEntityException("System of patient identifier is not a valid OID.");
        }
    }

    protected static String replaceUrnOid(String oid){
        return oid.replace("urn:oid:", "");
    }
}
