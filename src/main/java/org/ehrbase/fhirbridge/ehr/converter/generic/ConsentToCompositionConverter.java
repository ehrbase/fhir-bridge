package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Consent;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class ConsentToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Consent, C> {

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull Consent resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertConsentTime(resource));
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Consent consent) {
        if (!consent.hasPerformer()) {
            return new PartySelf();
        }
        return converter.convert(consent.getPerformerFirstRep());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Consent consent) {
        if (!consent.hasOrganization()) {
            return Optional.empty();
        }
        return Optional.of(converter.convert(consent.getOrganizationFirstRep()));
    }

}
