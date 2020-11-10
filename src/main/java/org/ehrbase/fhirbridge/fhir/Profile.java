package org.ehrbase.fhirbridge.fhir;

import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Profile {

    BODY_TEMP(Observation.class, "http://hl7.org/fhir/StructureDefinition/bodytemp");

    private final Class<? extends Resource> resourceType;

    private final String uri;

    <T extends Resource> Profile(Class<T> resourceType, String uri) {
        this.resourceType = resourceType;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Class<? extends Resource> getResourceType() {
        return resourceType;
    }

    public static <T extends Resource> boolean isDefaultSupported(T resource) {
        return !(resource instanceof DiagnosticReport) && !(resource instanceof Observation);
    }

    public static <T extends Resource> List<Profile> resolveAll(T resource) {
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

//    public static Set<String> getProfileUris() {
//        return Arrays.stream(values())
//                .map(Profile::getUri)
//                .collect(Collectors.toSet());
//    }
//
//    public static Profile resolve(String uri) {
//        for (Profile profile : values()) {
//            if (Objects.equals(profile.uri, uri)) {
//                return profile;
//            }
//        }
//        return null;
//    }
//
//    public static Collection<Profile> resolveAll(Collection<String> uri) {
//        return uri.stream()
//                .map(Profile::resolve)
//                .filter(Objects::isNull)
//                .collect(Collectors.toSet());
//    }
}
