package org.ehrbase.fhirbridge.fhir.bundle.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UCCSensordatenVitalSignsBundleConverter extends AbstractBundleConverter<Composition> {

    @Override
    public Composition convert(@NonNull Bundle bundle) {
        Composition composition = getRoot(bundle, Profile.UCC_SENSORDATEN_VITALSIGNS);
        Map<String, Resource> resources = mapResources(bundle);

        List<Resource> contains = new ArrayList<>();
        for (Composition.SectionComponent component : composition.getSection()) {
            for (Reference reference : component.getEntry()) {
                Resource resource = resources.get(reference.getReference());
                if (resource == null) {
                    throw new UnprocessableEntityException("Resource '" + reference.getReference() + "' is missing");
                }
                resource.setId((String) null);
                reference.setReference(null);
                reference.setResource(resource);
                contains.add(resource);
            }
        }

        composition.setContained(contains);
        //observation.setContained(contains);
        return composition;
    }
}
