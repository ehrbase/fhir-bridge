package org.ehrbase.fhirbridge.ehr.converter.patientinicu;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.util.HashMap;

public class PatientInIcuCompositionConverter implements CompositionConverter<PatientAufICUComposition, Observation> {
    private static final HashMap<String, WurdeDieAktivitatDurchgefuhrtDefiningcode> aktivitatDurchgefuehrtDefiningcodeMap
            = new HashMap<>();


    static {
        for (WurdeDieAktivitatDurchgefuhrtDefiningcode code : WurdeDieAktivitatDurchgefuhrtDefiningcode.values()) {
            if (code.getTerminologyId().equals("SNOMED Clinical Terms")) {
                aktivitatDurchgefuehrtDefiningcodeMap.put(code.getCode(), code);
            }
        }
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
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else {
            throw new UnprocessableEntityException("Status has invalid code " + status.toCode());
        }
    }

    private static void setMandatoryFields(PatientAufICUComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
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
}
