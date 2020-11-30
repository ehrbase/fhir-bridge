package org.ehrbase.fhirbridge.fhir.common;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Resource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Profile {

    // DiagnosticReport Profiles

    DIAGNOSTIC_REPORT_LAB(DiagnosticReport.class, "https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/DiagnosticReportLab"),

    // Patient Profiles

    PATIENT(Patient.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/Patient"),

    // Observation Profiles

    BODY_HEIGHT(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/body-height"),

    BLOOD_PRESSURE(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure"),

    BODY_TEMP(Observation.class, "http://hl7.org/fhir/StructureDefinition/bodytemp"),

    BODY_WEIGHT(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/body-weight"),

    CLINICAL_FRAILTY_SCALE(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/frailty-score"),

    CORONARIRUS_NACHWEIS_TEST(Observation.class, "https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest"),

    FIO2(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/inhaled-oxygen-concentration"),

    HEART_RATE(Observation.class, "http://hl7.org/fhir/StructureDefinition/heartrate"),

    PATIENT_IN_ICU(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/patient-in-icu"),

    PREGNANCY_STATUS(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pregnancy-status"),

    OBSERVATION_LAB(Observation.class, "https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab"),

    RESPIRATORY_RATE(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/respiratory-rate"),

    SOFA_SCORE(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sofa-score"),

    SMOKING_STATUS(Observation.class, "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/smoking-status"),

    // Procedure Profiles

    PROCEDURE(Procedure.class, "https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Procedure");

    private final Class<? extends Resource> resourceType;

    private final String uri;

    <T extends Resource> Profile(Class<T> resourceType, String uri) {
        this.resourceType = resourceType;
        this.uri = uri;
    }

    public static <T extends Resource> boolean isDefaultSupported(T resource) {
        return !(resource instanceof DiagnosticReport) &&
                !(resource instanceof Observation) &&
                !(resource instanceof Patient) &&
                !(resource instanceof Procedure);
    }

    public static <T extends Resource> Collection<Profile> resolve(T resource) {
        return resource.getMeta().getProfile().stream()
                .map(uri -> Profile.resolve(uri.getValue()))
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    public static Profile resolve(String uri) {
        for (Profile profile : values()) {
            if (Objects.equals(profile.uri, uri)) {
                return profile;
            }
        }
        return null;
    }

    public static <T extends Resource> Collection<String> getAllSupportedProfileUris(T resource) {
        return Arrays.stream(values())
                .filter(profile -> profile.getResourceType().isAssignableFrom(resource.getClass()))
                .map(Profile::getUri)
                .collect(Collectors.toList());
    }

    public String getUri() {
        return uri;
    }

    public Class<? extends Resource> getResourceType() {
        return resourceType;
    }
}
