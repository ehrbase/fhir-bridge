package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.FallidentifikationCluster;

import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;
import org.hl7.fhir.r4.model.DiagnosticReport;

import java.util.Optional;

public class VirologischerBefundCompositionConverter extends ObservationToCompositionConverter<VirologischerBefundComposition> {

        @Override
        public VirologischerBefundComposition convertInternal(@NonNull Observation resource) {

            VirologischerBefundComposition composition = new VirologischerBefundComposition();

            VirologischerBefundBundle virologischerBefundBundle = new VirologischerBefundBundle(resource);
            Observation observation = virologischerBefundBundle.getVirologischerBefund();

            DiagnosticReport diagnosticReport = virologischerBefundBundle.getDiagnosticReport();

            observation.setSpecimenTarget(virologischerBefundBundle.getSpecimen());

            mapStatus(composition, diagnosticReport);

            mapFallIdentifikation(diagnosticReport).ifPresent(composition::setFallidentifikation);

            composition.setBefund(new VirologischerBefundObservationConverter().convert(observation));

            return composition;
        }

    private void mapStatus(VirologischerBefundComposition composition, DiagnosticReport diagnosticReport) {
        String codeString = diagnosticReport.getStatusElement().toString();
        composition.setStatusValue(codeString);
    }

    private Optional<FallidentifikationCluster> mapFallIdentifikation(DiagnosticReport diagnosticReport) {

        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();

        if (diagnosticReport.hasEncounter() && diagnosticReport.getEncounter().hasReference()){
            String codeString = diagnosticReport.getEncounter().getReference();
            fallidentifikationCluster.setFallKennungValue(codeString);
            return Optional.of(fallidentifikationCluster);
        } else{
            return Optional.empty();
        }

    }

}
