package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class PatientToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Patient, C> {

    @Override
    public C convert(@NonNull Patient resource) {
        C composition = super.convert(resource);
        Extension extensionAge = resource.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age");
        composition.setStartTimeValue(TimeConverter.convertAgeExtensionTime(extensionAge));
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
}
