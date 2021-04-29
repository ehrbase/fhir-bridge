package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.lang.NonNull;

public abstract class ProcedureToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Procedure, C> {

    @Override
    public C convert(@NonNull Procedure resource) {
        C composition = super.convert(resource);

        // Mandatory
        composition.setStartTimeValue(TimeConverter.convertProcedureTime(resource));
        composition.setComposer(getComposerOrDefault(resource)); // Composer

        // Optional
        TimeConverter.convertProcedureEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue

        return composition;
    }

    protected PartyProxy getComposerOrDefault(Procedure resource) {
        return resource.getPerformer()
                .stream()
                .map(reference -> {
                    PartyIdentified composer = new PartyIdentified();
                    DvIdentifier identifier = new DvIdentifier();
                    if (reference.hasActor() && reference.getActor().hasReference()) {
                        identifier.setId(reference.getActor().getReference());
                    }
                    composer.addIdentifier(identifier);
                    return (PartyProxy) composer;
                })
                .findFirst()
                .orElse(new PartySelf());
    }

}