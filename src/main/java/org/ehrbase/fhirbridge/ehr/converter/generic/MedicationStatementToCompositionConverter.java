package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class MedicationStatementToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<MedicationStatement, C> {

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull MedicationStatement statement) {
        C composition = super.convert(statement);
        composition.setStartTimeValue(TimeConverter.convertMedicationStatmentTime(statement)); // StartTimeValue
        TimeConverter.convertMedicationStatementEndTime(statement).ifPresent(composition::setEndTimeValue);
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(MedicationStatement statement) {
        if (!statement.hasInformationSource()) {
            return new PartySelf();
        }
        return converter.convert(statement.getInformationSource());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(MedicationStatement statement) {
        if (!statement.hasInformationSource() ||
                !Resources.isReferenceType(statement.getInformationSource(), ResourceType.Organization)) {
            return Optional.empty();
        }
        return Optional.of(converter.convert(statement.getInformationSource()));
    }
}
