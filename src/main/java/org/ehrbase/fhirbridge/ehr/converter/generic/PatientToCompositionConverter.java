package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class PatientToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Patient, C> {

    @Override
    public C convert(@NonNull Patient resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertPatientDateTime(resource));
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Patient resource) {
        return new PartySelf();
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Patient resource) {
        return Optional.empty();
    }

    @Override
    protected List<DvIdentifier> subjectIdentifiers(Patient patient) {
        List<DvIdentifier> identifiers = new ArrayList<>();

        var id = new DvIdentifier();
        id.setAssigner("fhir_patient_id");
        id.setId(patient.getId());
        identifiers.add(id);

        var identifier = new DvIdentifier();
        identifier.setType("fhir_patient_identifier");
        identifier.setId(patient.getIdentifierFirstRep().getValue());

        identifiers.add(identifier);
        return identifiers;
    }
}
