package org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.WurdeDieAktivitatDurchgefuhrtDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.HashMap;

public class PatientAufDerIntensivstationObservationConverter extends ObservationToObservationConverter<PatientAufDerIntensivstationObservation> {
    private static final HashMap<String, WurdeDieAktivitatDurchgefuhrtDefiningCode> aktivitatDurchgefuehrtDefiningcodeMap
            = new HashMap<>();

    static {
        for (WurdeDieAktivitatDurchgefuhrtDefiningCode code : WurdeDieAktivitatDurchgefuhrtDefiningCode.values()) {
            if (code.getTerminologyId().equals("SNOMED Clinical Terms")) {
                aktivitatDurchgefuehrtDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    @Override
    protected PatientAufDerIntensivstationObservation convertInternal(Observation resource) {
        PatientAufDerIntensivstationObservation patientAufDerIntensivstation = new PatientAufDerIntensivstationObservation();
        patientAufDerIntensivstation.setNameDerAktivitaetValue("Behandlung auf der Intensivstation");
        patientAufDerIntensivstation.setWirdWurdeDieAktivitaetDurchgefuehrt(mapWurdeDieAktivitatDurchgefuhrt(resource));
        return patientAufDerIntensivstation;
    }

    private DvCodedText mapWurdeDieAktivitatDurchgefuhrt(Observation fhirObservation) {
        Coding coding = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        if (coding.getSystem().equals("http://snomed.info/sct") && aktivitatDurchgefuehrtDefiningcodeMap.containsKey(coding.getCode())) {
            return aktivitatDurchgefuehrtDefiningcodeMap.get(coding.getCode()).toDvCodedText();
        }

        throw new ConversionException("Aktivität durchgeführt has invalid code " + coding.getCode());
    }
}
