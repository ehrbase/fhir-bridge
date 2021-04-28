package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.covid19therapie;

import liquibase.pro.packaged.C;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedicationPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.MedicationStatement;

public class Covid19TherapiePointEventConverter extends GeccoMedicationPointEventConverter<Covid19TherapieBeliebigesEreignisPointEvent> {
    @Override
    protected Covid19TherapieBeliebigesEreignisPointEvent convertInternal(MedicationStatement resource) {
        Covid19TherapieBeliebigesEreignisPointEvent covid19TherapieBeliebigesEreignisPointEvent = new Covid19TherapieBeliebigesEreignisPointEvent();

        return covid19TherapieBeliebigesEreignisPointEvent;
    }

}
