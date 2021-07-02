package org.ehrbase.fhirbridge.config.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.ehrbase.fhirbridge.camel.processor.ProvideResourcePersistenceProcessor;
import org.ehrbase.fhirbridge.core.repository.ResourceCompositionRepository;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfiguration {

    private final ResourceCompositionRepository resourceCompositionRepository;

    public ProcessorConfiguration(ResourceCompositionRepository resourceCompositionRepository) {
        this.resourceCompositionRepository = resourceCompositionRepository;
    }

    @Bean
    public ProvideResourcePersistenceProcessor<DiagnosticReport> provideDiagnosticReportPersistenceProcessor(IFhirResourceDao<DiagnosticReport> diagnosticReportDao) {
        return new ProvideResourcePersistenceProcessor<>(diagnosticReportDao, DiagnosticReport.class, resourceCompositionRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Encounter> provideEncounterPersistenceProcessor(IFhirResourceDao<Encounter> encounterDao) {
        return new ProvideResourcePersistenceProcessor<>(encounterDao, Encounter.class, resourceCompositionRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Observation> provideObservationPersistenceProcessor(IFhirResourceDao<Observation> observationDao) {
        return new ProvideResourcePersistenceProcessor<>(observationDao, Observation.class, resourceCompositionRepository);
    }
}
