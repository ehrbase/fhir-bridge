package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LabratoryTestResultConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setJedesEreignis(mapBefunde(resource));
        return befundObservation;
    }

    private List<BefundJedesEreignisChoice> mapBefunde(Observation observation) {
        List<BefundJedesEreignisChoice> befundJedesEreignisChoices = new ArrayList<>();
        for (Optional<Immunoassay> optionalObservation : new AntiBodyPanel(observation).getAllNonPanel()) {
            optionalObservation.ifPresent(immunoassay -> befundJedesEreignisChoices.add(new BefundJedesEreignisPointEventConverter(immunoassay).convert(observation)));
        }
        return befundJedesEreignisChoices;
    }
}
