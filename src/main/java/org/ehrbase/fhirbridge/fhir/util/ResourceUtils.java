package org.ehrbase.fhirbridge.fhir.util;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceUtils {

    public static ResourceType getResourceType(IBaseResource resource) {
        if (resource instanceof Resource) {
            return (((Resource) resource).getResourceType());
        }
        return null;
    }

    public static List<String> getProfiles(Resource resource) {
        return resource.getMeta().getProfile()
                .stream()
                .map(CanonicalType::getValue)
                .collect(Collectors.toUnmodifiableList());
    }
}
