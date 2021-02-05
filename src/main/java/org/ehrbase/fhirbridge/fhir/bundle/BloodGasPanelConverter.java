package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * Converter for 'BloodBasPanel' profile that convert a Bundle in an Observation with contained resources.
 */
public class BloodGasPanelConverter extends AbstractBundleConverter<Observation> {

    @Override
    public Observation convert(@NonNull Bundle bundle) {
        Observation observation = getRoot(bundle, Profile.BLOOD_GAS_PANEL);
        Map<String, Resource> resources = mapResources(bundle);

        for (Reference reference : observation.getHasMember()) {
            Resource resource = resources.get(reference.getReference());
            if (resource == null) {
                throw new UnprocessableEntityException("Resource '" + reference.getReference() + "' is missing");
            }

            reference.setResource(resource);
            reference.setReference(null);
        }

        // TODO: Do we have to process all elements here?

        return observation;
    }
}
