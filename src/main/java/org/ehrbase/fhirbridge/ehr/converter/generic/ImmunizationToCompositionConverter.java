package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class ImmunizationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Immunization, C>{

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull Immunization resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertImmunizationTime(resource)); // occurenceTime
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Immunization immunization) {
        if (!immunization.hasPerformer()) {
            return new PartySelf();
        }
        return new ReferenceToPartyIdentifiedConverter().convert(immunization.getPerformerFirstRep().getActor());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Immunization immunization) {
        return immunization.getPerformer()
                .stream()
                .map(Immunization.ImmunizationPerformerComponent::getActor)
                .filter(reference -> Resources.isReferenceType(reference, ResourceType.Organization))
                .findFirst()
                .map(converter::convert);
    }
}
