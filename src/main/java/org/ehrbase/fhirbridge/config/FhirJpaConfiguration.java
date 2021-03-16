/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.config;

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
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Group;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * {@link Configuration Configuration} for HAPI FHIR JPA Server module.
 *
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(FhirJpaProperties.class)
public class FhirJpaConfiguration extends BaseR4Config {

    private final FhirJpaProperties fhirJpaProperties;

    private final JpaProperties jpaProperties;

    private final DataSource dataSource;

    public FhirJpaConfiguration(FhirJpaProperties fhirJpaProperties, JpaProperties jpaProperties, DataSource dataSource) {
        this.fhirJpaProperties = fhirJpaProperties;
        this.jpaProperties = jpaProperties;
        this.dataSource = dataSource;
    }

    @Bean
    public DaoConfig daoConfig() {
        DaoConfig daoConfig = new DaoConfig();
        daoConfig.setAllowInlineMatchUrlReferences(fhirJpaProperties.isAllowInlineMatchUrlReferences());
        daoConfig.setAutoCreatePlaceholderReferenceTargets(fhirJpaProperties.isAutoCreatePlaceholderReferences());
        daoConfig.setPopulateIdentifierInAutoCreatedPlaceholderReferenceTargets(fhirJpaProperties.isPopulateIdentifierInAutoCreatedPlaceholderReferences());
        return daoConfig;
    }

    @Bean
    public ModelConfig modelConfig() {
        ModelConfig config = new ModelConfig();
        config.setAllowExternalReferences(fhirJpaProperties.isAllowExternalReferences());
        return config;
    }

    @Bean
    public PartitionSettings partitionSettings() {
        return new PartitionSettings();
    }

    @Bean
    @Override
    protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = super.entityManagerFactory();
        entityManagerFactory.setPersistenceUnitName("HAPI_PU");
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaPropertyMap(jpaProperties.getProperties());
        entityManagerFactory.setPackagesToScan(
                "ca.uhn.fhir.jpa.model.entity",
                "ca.uhn.fhir.jpa.entity",
                "org.ehrbase.fhirbridge.fhir.support"
        );
        return entityManagerFactory;
    }

    @Primary
    @Bean
    public PlatformTransactionManager hapiTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public IFhirResourceDao<AuditEvent> auditEventDao() {
        JpaResourceDao<AuditEvent> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(AuditEvent.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Condition> conditionDao() {
        JpaResourceDao<Condition> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Condition.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Consent> consentDao() {
        JpaResourceDao<Consent> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Consent.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Device> deviceDao() {
        JpaResourceDao<Device> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Device.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<DiagnosticReport> diagnosticReportDao() {
        JpaResourceDao<DiagnosticReport> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(DiagnosticReport.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Group> groupDao() {
        JpaResourceDao<Group> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Group.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Location> locationDao() {
        JpaResourceDao<Location> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Location.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<MedicationStatement> medicationStatementDao() {
        JpaResourceDao<MedicationStatement> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(MedicationStatement.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Observation> observationDao() {
        JpaResourceDao<Observation> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Observation.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Patient> patientDao() {
        JpaResourceDao<Patient> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Patient.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Procedure> procedureDao() {
        JpaResourceDao<Procedure> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Procedure.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao() {
        JpaResourceDao<QuestionnaireResponse> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(QuestionnaireResponse.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Encounter> encounterDao() {
        JpaResourceDao<Encounter> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Encounter.class);
        resourceDao.setContext(fhirContext());
        return resourceDao;
    }

    @Bean(name = "myCodeSystemDaoR4")
    public IFhirResourceDaoCodeSystem<CodeSystem, Coding, CodeableConcept> codeSystemDao() {
        FhirResourceDaoCodeSystemR4 codeSystemDao = new FhirResourceDaoCodeSystemR4();
        codeSystemDao.setResourceType(CodeSystem.class);
        codeSystemDao.setContext(fhirContext());
        return codeSystemDao;
    }

    @Bean(name = "myValueSetDaoR4")
    public IFhirResourceDaoValueSet<ValueSet, Coding, CodeableConcept> valueSetDao() {
        FhirResourceDaoValueSetR4 valueSetDao = new FhirResourceDaoValueSetR4();
        valueSetDao.setResourceType(ValueSet.class);
        valueSetDao.setContext(fhirContext());
        return valueSetDao;
    }

    @Bean(name = "myConceptMapDaoR4")
    public IFhirResourceDaoConceptMap<ConceptMap> conceptMapDao() {
        FhirResourceDaoConceptMapR4 conceptMapDao = new FhirResourceDaoConceptMapR4();
        conceptMapDao.setResourceType(ConceptMap.class);
        conceptMapDao.setContext(fhirContext());
        return conceptMapDao;
    }

    @Bean(name = "mySearchParameterDaoR4")
    public IFhirResourceDaoSearchParameter<SearchParameter> searchParameterDao() {
        FhirResourceDaoSearchParameterR4 searchParameterDao = new FhirResourceDaoSearchParameterR4();
        searchParameterDao.setResourceType(SearchParameter.class);
        searchParameterDao.setContext(fhirContext());
        return searchParameterDao;
    }
}
