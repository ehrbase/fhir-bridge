package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.config.DaoConfig;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoCodeSystem;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoConceptMap;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoSearchParameter;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoValueSet;
import ca.uhn.fhir.jpa.config.r4.BaseR4Config;
import ca.uhn.fhir.jpa.dao.JpaResourceDao;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoCodeSystemR4;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoConceptMapR4;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoSearchParameterR4;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoValueSetR4;
import ca.uhn.fhir.jpa.model.config.PartitionSettings;
import ca.uhn.fhir.jpa.model.entity.ModelConfig;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ConceptMap;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.ValueSet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableConfigurationProperties(FhirJpaProperties.class)
@Import({BaseR4Config.class})
@EntityScan(basePackages = {
        "ca.uhn.fhir.jpa.entity",
        "ca.uhn.fhir.jpa.model.entity"
})
public class FhirJpaConfiguration {

    private final FhirJpaProperties properties;

    public FhirJpaConfiguration(FhirJpaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DaoConfig daoConfig() {
        return new DaoConfig();
    }

    @Bean
    public ModelConfig modelConfig() {
        ModelConfig config = new ModelConfig();
        config.setAllowExternalReferences(properties.isAllowExternalReferences());
        return config;
    }

    @Bean
    public PartitionSettings partitionSettings() {
        return new PartitionSettings();
    }

    // TODO: Check SimpleBatchConfiguration
    @Primary
    @Bean(name = "hapiTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean(name = "myConditionDaoR4")
    public IFhirResourceDao<Condition> conditionReportDao(FhirContext context) {
        JpaResourceDao<Condition> conditionReportDao = new JpaResourceDao<>();
        conditionReportDao.setResourceType(Condition.class);
        conditionReportDao.setContext(context);
        return conditionReportDao;
    }

    @Bean(name = "myDiagnosticReportDaoR4")
    public IFhirResourceDao<DiagnosticReport> diagnosticReportDao(FhirContext context) {
        JpaResourceDao<DiagnosticReport> diagnosticReportDao = new JpaResourceDao<>();
        diagnosticReportDao.setResourceType(DiagnosticReport.class);
        diagnosticReportDao.setContext(context);
        return diagnosticReportDao;
    }

    @Bean(name = "myObservationDaoR4")
    public IFhirResourceDao<Observation> observationDao(FhirContext context) {
        JpaResourceDao<Observation> observationDao = new JpaResourceDao<>();
        observationDao.setResourceType(Observation.class);
        observationDao.setContext(context);
        return observationDao;
    }

    @Bean(name = "myAuditEventDaoR4")
    public IFhirResourceDao<AuditEvent> auditEventDao(FhirContext context) {
        JpaResourceDao<AuditEvent> auditEventDao = new JpaResourceDao<>();
        auditEventDao.setResourceType(AuditEvent.class);
        auditEventDao.setContext(context);
        return auditEventDao;
    }

    @Bean(name = "myCodeSystemDaoR4")
    public IFhirResourceDaoCodeSystem<CodeSystem, Coding, CodeableConcept> codeSystemDao(FhirContext context) {
        FhirResourceDaoCodeSystemR4 codeSystemDao = new FhirResourceDaoCodeSystemR4();
        codeSystemDao.setResourceType(CodeSystem.class);
        codeSystemDao.setContext(context);
        return codeSystemDao;
    }

    @Bean(name = "myValueSetDaoR4")
    public IFhirResourceDaoValueSet<ValueSet, Coding, CodeableConcept> valueSetDao(FhirContext context) {
        FhirResourceDaoValueSetR4 valueSetDao = new FhirResourceDaoValueSetR4();
        valueSetDao.setResourceType(ValueSet.class);
        valueSetDao.setContext(context);
        return valueSetDao;
    }

    @Bean(name = "myConceptMapDaoR4")
    public IFhirResourceDaoConceptMap<ConceptMap> conceptMapDao(FhirContext context) {
        FhirResourceDaoConceptMapR4 conceptMapDao = new FhirResourceDaoConceptMapR4();
        conceptMapDao.setResourceType(ConceptMap.class);
        conceptMapDao.setContext(context);
        return conceptMapDao;
    }

    @Bean(name = "mySearchParameterDaoR4")
    public IFhirResourceDaoSearchParameter<SearchParameter> searchParameterDao(FhirContext context) {
        FhirResourceDaoSearchParameterR4 searchParameterDao = new FhirResourceDaoSearchParameterR4();
        searchParameterDao.setResourceType(SearchParameter.class);
        searchParameterDao.setContext(context);
        return searchParameterDao;
    }
}