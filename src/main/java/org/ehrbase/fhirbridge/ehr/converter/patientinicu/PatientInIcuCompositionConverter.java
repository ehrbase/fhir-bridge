package org.ehrbase.fhirbridge.ehr.converter.patientinicu;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.HashMap;

public class PatientInIcuCompositionConverter extends AbstractCompositionConverter<Observation, PatientAufICUComposition> {
    private static final HashMap<String, WurdeDieAktivitatDurchgefuhrtDefiningcode> aktivitatDurchgefuehrtDefiningcodeMap
            = new HashMap<>();


    static {
        for (WurdeDieAktivitatDurchgefuhrtDefiningcode code : WurdeDieAktivitatDurchgefuhrtDefiningcode.values()) {
            if (code.getTerminologyId().equals("SNOMED Clinical Terms")) {
                aktivitatDurchgefuehrtDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    private static void setStatus(PatientAufICUComposition composition, Observation fhirObservation) {
        Observation.ObservationStatus status = fhirObservation.getStatus();

        if (status.equals(Observation.ObservationStatus.FINAL)) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else {
            throw new UnprocessableEntityException("Status has invalid code " + status.toCode());
        }
    }

    private static PatientAufDerIntensivstationObservation mapPatientAufIntensivstation(Observation fhirObservation) {
        PatientAufDerIntensivstationObservation patientAufDerIntensivstation = new PatientAufDerIntensivstationObservation();
        patientAufDerIntensivstation.setNameDerAktivitaetValue("Behandlung auf der Intensivstation");

        patientAufDerIntensivstation.setWirdWurdeDieAktivitaetDurchgefuehrt(mapWurdeDieAktivitatDurchgefuhrt(fhirObservation));

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        patientAufDerIntensivstation.setLanguage(Language.DE);
        patientAufDerIntensivstation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setSubject(new PartySelf());

        return patientAufDerIntensivstation;
    }

    private static DvCodedText mapWurdeDieAktivitatDurchgefuhrt(Observation fhirObservation) {
        Coding coding = fhirObservation.getValueCodeableConcept().getCoding().get(0);

        if (coding.getSystem().equals("http://snomed.info/sct") && aktivitatDurchgefuehrtDefiningcodeMap.containsKey(coding.getCode())) {
            return aktivitatDurchgefuehrtDefiningcodeMap.get(coding.getCode()).toDvCodedText();
        }

        throw new UnprocessableEntityException("Aktivität durchgeführt has invalid code " + coding.getCode());
    }

    @Override
    public PatientAufICUComposition convert(@NonNull Observation observation) {
        PatientAufICUComposition composition = new PatientAufICUComposition();

        setStatus(composition, observation);

        composition.setPatientAufDerIntensivstation(mapPatientAufIntensivstation(observation));
        composition.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        return composition;
    }
}
