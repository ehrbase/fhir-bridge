package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class ProcedureToCompositionConverter<C extends CompositionEntity>
        extends CompositionConverter<Procedure, C> {

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull Procedure resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertProcedureTime(resource));
        TimeConverter.convertProcedureEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Procedure procedure) {
        if (!procedure.hasPerformer()) {
            return new PartySelf();
        }
        return converter.convert(procedure.getPerformerFirstRep().getActor());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Procedure procedure) {
        return procedure.getPerformer()
                .stream()
                .filter(Procedure.ProcedurePerformerComponent::hasOnBehalfOf)
                .findFirst()
                .map(Procedure.ProcedurePerformerComponent::getOnBehalfOf)
                .map(converter::convert);
    }
}