package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.WurdeDieAktivitatDurchgefuhrtDefiningcode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.util.HashMap;
import java.util.Map;

public class PatientInIcuCompositionConverter implements CompositionConverter<PatientAufICUComposition, Observation> {
    private static final Map<String, WurdeDieAktivitatDurchgefuhrtDefiningcode> aktivitatDurchgefuehrtDefiningcodeMap
            = new HashMap<>();

    static {
        for (WurdeDieAktivitatDurchgefuhrtDefiningcode code : WurdeDieAktivitatDurchgefuhrtDefiningcode.values()) {
            if (code.getTerminologyId().equals("SNOMED Clinical Terms")) {
                aktivitatDurchgefuehrtDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    @Override
    public Observation fromComposition(PatientAufICUComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public PatientAufICUComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        PatientAufICUComposition composition = new PatientAufICUComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        composition.setFeederAudit(fa);

        setStatus(composition, observation);

        composition.setPatientAufDerIntensivstation(mapPatientAufIntensivstation(observation));
        composition.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        setMandatoryFields(composition);

        return composition;
    }

    private static void setStatus(PatientAufICUComposition composition, Observation fhirObservation) {
        Observation.ObservationStatus status = fhirObservation.getStatus();

        if (status.equals(Observation.ObservationStatus.FINAL)) {
            composition.setStatusDefiningcode(StatusDefiningcode.FINAL);
        } else {
            throw new UnprocessableEntityException("Status has invalid code " + status.toCode());
        }
    }

    private static void setMandatoryFields(PatientAufICUComposition composition) {
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartySelf()); //FIXME: sensible value
    }

    private static PatientAufDerIntensivstationObservation mapPatientAufIntensivstation(Observation fhirObservation) {
        PatientAufDerIntensivstationObservation patientAufDerIntensivstation = new PatientAufDerIntensivstationObservation();
        patientAufDerIntensivstation.setNameDerAktivitatValue("Behandlung auf der Intensivstation");

        patientAufDerIntensivstation.setWurdeDieAktivitatDurchgefuhrtDefiningcode(mapWurdeDieAktivitatDurchgefuhrt(fhirObservation));

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        patientAufDerIntensivstation.setLanguage(Language.DE);
        patientAufDerIntensivstation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setSubject(new PartySelf());

        return patientAufDerIntensivstation;
    }

    private static WurdeDieAktivitatDurchgefuhrtDefiningcode mapWurdeDieAktivitatDurchgefuhrt(Observation fhirObservation) {
        Coding coding = fhirObservation.getValueCodeableConcept().getCoding().get(0);

        if (coding.getSystem().equals("http://snomed.info/sct") && aktivitatDurchgefuehrtDefiningcodeMap.containsKey(coding.getCode())) {
            return aktivitatDurchgefuehrtDefiningcodeMap.get(coding.getCode());
        }

        throw new UnprocessableEntityException("Aktivität durchgeführt has invalid code " + coding.getCode());
    }

}
