package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.Converter;
import org.hl7.fhir.r4.model.Reference;

public class ReferenceToPartyIdentifiedConverter implements Converter<Reference, PartyIdentified> {

    @Override
    public PartyIdentified convert(Reference source) {
        if (source == null) {
            return null;
        }

        PartyIdentified partyIdentified = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        if (source.hasReference()) {
            identifier.setId(source.getReference());
        } else if (source.hasIdentifier()) {
            identifier.setAssigner(source.getIdentifier().getSystem());
            identifier.setId(source.getIdentifier().getValue());
        } else {
            throw new ConversionException("Reference should have either a reference or an identifier");
        }
        partyIdentified.addIdentifier(identifier);
        return partyIdentified;
    }
}
