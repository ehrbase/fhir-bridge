package org.ehrbase.fhirbridge.ehr.converter.pulseoximetry;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ContextConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PulseOximetryConverter extends AbstractCompositionConverter<Observation, PulsoxymetrieComposition> {

    @Override
    public PulsoxymetrieComposition convert(@NonNull Observation observation) {
        PulsoxymetrieComposition composition = new PulsoxymetrieComposition();
        mapCommonAttributes(observation, composition);
        new PulseOximetryCodeChecker().checkIfPulseOximetry(observation);
        new ContextConverter().mapStatus(composition, observation);
        mapKategorie(composition, observation);
        mapPulsoxymetrieObservation(composition, observation);
        setMandatoryFields(composition, observation);
        return composition;
    }

    private void setMandatoryFields(PulsoxymetrieComposition composition, Observation observation) {
        composition.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        composition.setEndTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
    }

    private void mapKategorie(PulsoxymetrieComposition composition, Observation observation) {
        if (observation.getCategory().size() > 1) {
            throw new UnprocessableEntityException("Fhir-Bridge does not support more then one Category Code value");
        }
        composition.setKategorieValue(observation.getCategory().get(0).getCoding()
                .get(0).getCode());
    }

    private void mapPulsoxymetrieObservation(PulsoxymetrieComposition composition, Observation observation) {
        PulsoxymetrieObservation pulsoxymetrieObservation = new PulsoxymetrieObservation();
        pulsoxymetrieObservation.setLanguage(Language.DE);
        pulsoxymetrieObservation.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        pulsoxymetrieObservation.setOriginValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        pulsoxymetrieObservation.setSubject(new PartySelf());

        DvProportion dvProportion = new DvProportion(observation.getValueQuantity().getValue().doubleValue(), 100.0, 2L);
        pulsoxymetrieObservation.setSpo(dvProportion);
        composition.setPulsoxymetrie(pulsoxymetrieObservation);
    }
}
