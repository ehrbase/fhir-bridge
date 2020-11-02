package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.config.DaoConfig;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoCodeSystem;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoConceptMap;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoValueSet;
import ca.uhn.fhir.jpa.config.r4.BaseR4Config;
import ca.uhn.fhir.jpa.dao.JpaResourceDao;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoCodeSystemR4;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoConceptMapR4;
import ca.uhn.fhir.jpa.dao.r4.FhirResourceDaoValueSetR4;
import ca.uhn.fhir.jpa.model.config.PartitionSettings;
import ca.uhn.fhir.jpa.model.entity.ModelConfig;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ConceptMap;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.ValueSet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@Import({BaseR4Config.class})
@EntityScan(basePackages = {
        "ca.uhn.fhir.jpa.entity",
        "ca.uhn.fhir.jpa.model.entity"
})
public class FhirJpaConfiguration {

    @Bean
    public DaoConfig daoConfig() {
        return new DaoConfig();
    }

    @Bean
    public ModelConfig modelConfig() {
        ModelConfig config = new ModelConfig();
        config.setAllowExternalReferences(true);
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

    @Bean(name = "myConditionReportDaoR4")
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

    @Bean(name = "myCodeSystemDaoR4")
    public IFhirResourceDaoCodeSystem<CodeSystem, Coding, CodeableConcept> codeSystemDao(FhirContext context) {
        FhirResourceDaoCodeSystemR4 codeSystemDao = new FhirResourceDaoCodeSystemR4();
        codeSystemDao.setResourceType(org.hl7.fhir.r4.model.CodeSystem.class);
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
}