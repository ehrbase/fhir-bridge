package org.ehrbase.fhirbridge.fhir.support;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.PrimitiveType;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Resources {

    public static final String RFC_4122_SYSTEM = "urn:ietf:rfc:4122";

    private Resources() {
    }

    public static void addIdentifier(Identifier identifier, Resource resource) {
        if (resource instanceof Condition) {
            ((Condition) resource).addIdentifier(identifier);
        } else if (resource instanceof DiagnosticReport) {
            ((DiagnosticReport) resource).addIdentifier(identifier);
        } else if (resource instanceof Observation) {
            ((Observation) resource).addIdentifier(identifier);
        } else if (resource instanceof Patient) {
            ((Patient) resource).addIdentifier(identifier);
        } else if (resource instanceof Procedure) {
            ((Procedure) resource).addIdentifier(identifier);
        }
    }

    public static List<String> getProfileUris(Resource resource) {
        return resource.getMeta().getProfile()
                .stream()
                .map(CanonicalType::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public static Optional<Identifier> getSubjectIdentifier(Resource resource) {
        Identifier subjectIdentifier = null;

        if (resource instanceof Condition) {
            subjectIdentifier = ((Condition) resource).getSubject().getIdentifier();
        } else if (resource instanceof DiagnosticReport) {
            subjectIdentifier = ((DiagnosticReport) resource).getSubject().getIdentifier();
        } else if (resource instanceof Observation) {
            subjectIdentifier = ((Observation) resource).getSubject().getIdentifier();
        } else if (resource instanceof Patient) {
            subjectIdentifier = ((Patient) resource).getIdentifier()
                    .stream()
                    .filter(identifier -> Objects.equals(identifier.getSystem(), RFC_4122_SYSTEM))
                    .findFirst()
                    .orElse(null);
        } else if (resource instanceof Procedure) {
            subjectIdentifier = ((Procedure) resource).getSubject().getIdentifier();
        } else if (resource instanceof QuestionnaireResponse) {
            subjectIdentifier = ((QuestionnaireResponse) resource).getSubject().getIdentifier();
        }

        return Optional.ofNullable(subjectIdentifier);
    }

    public static boolean hasProfile(Resource resource, Profile profile) {
        return hasAnyProfile(resource, profile);
    }

    public static boolean hasAnyProfile(Resource resource, Profile... profiles) {
        Set<String> c1 = resource.getMeta().getProfile()
                .stream()
                .map(PrimitiveType::getValue)
                .collect(Collectors.toSet());
        Set<String> c2 = Arrays.stream(profiles)
                .map(Profile::getUri)
                .collect(Collectors.toSet());

        return !Collections.disjoint(c1, c2);
    }
}
