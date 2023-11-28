package org.ehrbase.fhirbridge.fhir.bundle.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiagnosticReportLabConverter extends AbstractBundleConverter<DiagnosticReport> {

    @Override
    public DiagnosticReport convert(@NonNull Bundle bundle) {
        DiagnosticReport diagnosticReport = getRoot(bundle, Profile.DIAGNOSTIC_REPORT_LAB);
        Map<String, Resource> urlToResourceMap = mapResources(bundle);
        List<Resource> contains = new ArrayList<>();

        for (Reference reference : diagnosticReport.getResult()) {
            Observation observation = (Observation) urlToResourceMap.get(reference.getReference());
            if (observation == null) {
                throw new UnprocessableEntityException("Resource '" + reference.getReference() + "' is missing");
            }
            observation.setId((String) null);
            observation = transformSpecimen(observation, urlToResourceMap);
            contains.add(observation);
        }

        // TODO: Do we have to process all elements here?
        diagnosticReport.setContained(contains);
        return diagnosticReport;
    }

    private Observation transformSpecimen(Observation observation, Map<String, Resource> urlToResourceMap) {
        if (observation.hasSpecimen()){
            Specimen specimen = (Specimen) urlToResourceMap.get(observation.getSpecimen());
            observation.addContained(specimen);
        }
        return observation;
    }

}
