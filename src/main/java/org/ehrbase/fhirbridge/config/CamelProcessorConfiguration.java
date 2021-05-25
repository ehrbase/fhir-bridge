package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.ehrbase.fhirbridge.camel.processor.ProvideResourcePersistenceProcessor;
import org.ehrbase.fhirbridge.core.repository.ResourceMapRepository;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelProcessorConfiguration {

    private final ResourceMapRepository resourceMapRepository;

    public CamelProcessorConfiguration(ResourceMapRepository resourceMapRepository) {
        this.resourceMapRepository = resourceMapRepository;
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Condition> provideConditionPersistenceProcessor(IFhirResourceDao<Condition> conditionDao) {
        return new ProvideResourcePersistenceProcessor<>(conditionDao, Condition.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Consent> provideConsentPersistenceProcessor(IFhirResourceDao<Consent> consentDao) {
        return new ProvideResourcePersistenceProcessor<>(consentDao, Consent.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<DiagnosticReport> provideDiagnosticReportPersistenceProcessor(IFhirResourceDao<DiagnosticReport> diagnosticReportDao) {
        return new ProvideResourcePersistenceProcessor<>(diagnosticReportDao, DiagnosticReport.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Observation> provideObservationPersistenceProcessor(IFhirResourceDao<Observation> observationDao) {
        return new ProvideResourcePersistenceProcessor<>(observationDao, Observation.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<MedicationStatement> provideMedicationStatementPersistenceProcessor(IFhirResourceDao<MedicationStatement> medicationStatementDao) {
        return new ProvideResourcePersistenceProcessor<>(medicationStatementDao, MedicationStatement.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Patient> providePatientPersistenceProcessor(IFhirResourceDao<Patient> patientDao) {
        return new ProvideResourcePersistenceProcessor<>(patientDao, Patient.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Procedure> provideProcedurePersistenceProcessor(IFhirResourceDao<Procedure> procedureDao) {
        return new ProvideResourcePersistenceProcessor<>(procedureDao, Procedure.class, resourceMapRepository);
    }

    @Bean
    public ProvideResourcePersistenceProcessor<QuestionnaireResponse> provideQuestionnaireResponsePersistenceProcessor(IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao) {
        return new ProvideResourcePersistenceProcessor<>(questionnaireResponseDao, QuestionnaireResponse.class, resourceMapRepository);
    }
}
