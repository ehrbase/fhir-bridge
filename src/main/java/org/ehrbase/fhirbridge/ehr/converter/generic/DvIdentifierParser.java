package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.hl7.fhir.r4.model.Identifier;

public class DvIdentifierParser {

    public static DvIdentifier parseIdentifierIntoDvIdentifier(Identifier identifier) {
        DvIdentifier dvIdentifier = new DvIdentifier();
        setDvIdentifierAssinger(dvIdentifier, identifier);
        setDvIdentifierId(dvIdentifier, identifier);
        setDvIdentifierType(dvIdentifier, identifier);
        return dvIdentifier;
    }


    private static void setDvIdentifierType(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasAssigner()) {
            dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        } else {
            dvIdentifier.setAssigner("");
        }
    }

    private static void setDvIdentifierId(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasId()) {
            dvIdentifier.setId(identifier.getId());
        } else {
            dvIdentifier.setId("");
        }
    }

    private static void setDvIdentifierAssinger(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasType()) {
            dvIdentifier.setType(identifier.getType().getText());
        } else {
            dvIdentifier.setType("");
        }
    }
}
