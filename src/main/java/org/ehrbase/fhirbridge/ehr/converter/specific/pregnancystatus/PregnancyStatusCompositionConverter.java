package org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PregnancyStatusCompositionConverter extends ObservationToCompositionConverter<SchwangerschaftsstatusComposition> {

    @Override
    public SchwangerschaftsstatusComposition convertInternal(@NonNull Observation resource) {
        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();
        composition.setSchwangerschaftsstatus(new SchwangerschaftsstatusObservationConverter().convert(resource));
        return composition;
    }

}
