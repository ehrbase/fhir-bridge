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
import ca.uhn.fhir.jpa.searchparam.registry.SearchParamRegistryImpl;
import ca.uhn.fhir.rest.server.util.ISearchParamRegistry;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.ConceptMap;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.Device;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Group;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.ValueSet;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link Configuration Configuration} for HAPI FHIR JPA Server.
 *
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(HapiFhirJpaProperties.class)
@SuppressWarnings("java:S6212")
public class HapiFhirJpaConfiguration extends BaseR4Config {

    @Bean
    public DaoConfig daoConfig(HapiFhirJpaProperties properties) {
        DaoConfig config = new DaoConfig();
        config.setAllowInlineMatchUrlReferences(properties.isAllowInlineMatchUrlReferences());
        config.setReuseCachedSearchResultsForMillis(null);
        config.setResourceServerIdStrategy(DaoConfig.IdStrategyEnum.UUID);
        return config;
    }

    @Bean
    public ModelConfig modelConfig(HapiFhirJpaProperties properties) {
        var config = new ModelConfig();
        config.setAllowExternalReferences(properties.isAllowExternalReferences());
        return config;
    }

    @Bean
    public PartitionSettings partitionSettings() {
        return new PartitionSettings();
    }

    @Bean
    public JpaResourceDao<DocumentReference> documentReferenceDao() {
        JpaResourceDao<DocumentReference> documentReferenceDao = new JpaResourceDao<>();
        documentReferenceDao.setResourceType(DocumentReference.class);
        documentReferenceDao.setSearchParamRegistry(searchParamRegistry());
        return documentReferenceDao;
    }

    @Bean
    public IFhirResourceDao<AuditEvent> auditEventDao() {
        JpaResourceDao<AuditEvent> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(AuditEvent.class);
        resourceDao.setContext(fhirContext());
        resourceDao.setSearchParamRegistry(searchParamRegistry());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Condition> conditionDao() {
        JpaResourceDao<Condition> conditionDao = new JpaResourceDao<>();
        conditionDao.setResourceType(Condition.class);
        conditionDao.setContext(fhirContext());
        conditionDao.setSearchParamRegistry(searchParamRegistry());
        return conditionDao;
    }

    @Bean
    public IFhirResourceDao<Composition> compositionDao() {
        JpaResourceDao<Composition> compositionDao = new JpaResourceDao<>();
        compositionDao.setResourceType(Composition.class);
        compositionDao.setContext(fhirContext());
        compositionDao.setSearchParamRegistry(searchParamRegistry());
        return compositionDao;
    }

    @Bean
    public IFhirResourceDao<Consent> consentDao() {
        JpaResourceDao<Consent> consentDao = new JpaResourceDao<>();
        consentDao.setResourceType(Consent.class);
        consentDao.setContext(fhirContext());
        consentDao.setSearchParamRegistry(searchParamRegistry());
        return consentDao;
    }

    @Bean
    public IFhirResourceDao<Device> deviceDao() {
        JpaResourceDao<Device> deviceDao = new JpaResourceDao<>();
        deviceDao.setResourceType(Device.class);
        deviceDao.setContext(fhirContext());
        deviceDao.setSearchParamRegistry(searchParamRegistry());
        return deviceDao;
    }

    @Bean
    public IFhirResourceDao<DiagnosticReport> diagnosticReportDao() {
        JpaResourceDao<DiagnosticReport> diagnosticReportDao = new JpaResourceDao<>();
        diagnosticReportDao.setResourceType(DiagnosticReport.class);
        diagnosticReportDao.setContext(fhirContext());
        diagnosticReportDao.setSearchParamRegistry(searchParamRegistry());
        return diagnosticReportDao;
    }

    @Bean
    public IFhirResourceDao<Encounter> encounterDao() {
        JpaResourceDao<Encounter> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Encounter.class);
        resourceDao.setContext(fhirContext());
        resourceDao.setSearchParamRegistry(searchParamRegistry());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Group> groupDao() {
        JpaResourceDao<Group> groupDao = new JpaResourceDao<>();
        groupDao.setResourceType(Group.class);
        groupDao.setContext(fhirContext());
        groupDao.setSearchParamRegistry(searchParamRegistry());
        return groupDao;
    }

    @Bean
    public IFhirResourceDao<Immunization> immunizationDao() {
        JpaResourceDao<Immunization> resourceDao = new JpaResourceDao<>();
        resourceDao.setResourceType(Immunization.class);
        resourceDao.setContext(fhirContext());
        resourceDao.setSearchParamRegistry(searchParamRegistry());
        return resourceDao;
    }

    @Bean
    public IFhirResourceDao<Location> locationDao() {
        JpaResourceDao<Location> locationDao = new JpaResourceDao<>();
        locationDao.setResourceType(Location.class);
        locationDao.setContext(fhirContext());
        locationDao.setSearchParamRegistry(searchParamRegistry());
        return locationDao;
    }

    @Bean
    public IFhirResourceDao<MedicationStatement> medicationStatementDao() {
        JpaResourceDao<MedicationStatement> medicationStatementDao = new JpaResourceDao<>();
        medicationStatementDao.setResourceType(MedicationStatement.class);
        medicationStatementDao.setContext(fhirContext());
        medicationStatementDao.setSearchParamRegistry(searchParamRegistry());
        return medicationStatementDao;
    }

    @Bean
    public IFhirResourceDao<Observation> observationDao() {
        JpaResourceDao<Observation> observationDao = new JpaResourceDao<>();
        observationDao.setResourceType(Observation.class);
        observationDao.setContext(fhirContext());
        observationDao.setSearchParamRegistry(searchParamRegistry());
        return observationDao;
    }

    @Bean
    public IFhirResourceDao<Patient> patientDao() {
        JpaResourceDao<Patient> patientDao = new JpaResourceDao<>();
        patientDao.setResourceType(Patient.class);
        patientDao.setContext(fhirContext());
        patientDao.setSearchParamRegistry(searchParamRegistry());
        return patientDao;
    }

    @Bean
    public IFhirResourceDao<Procedure> procedureDao() {
        JpaResourceDao<Procedure> procedureDao = new JpaResourceDao<>();
        procedureDao.setResourceType(Procedure.class);
        procedureDao.setContext(fhirContext());
        procedureDao.setSearchParamRegistry(searchParamRegistry());
        return procedureDao;
    }

    @Bean
    public IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao() {
        JpaResourceDao<QuestionnaireResponse> questionnaireResponseDao = new JpaResourceDao<>();
        questionnaireResponseDao.setResourceType(QuestionnaireResponse.class);
        questionnaireResponseDao.setContext(fhirContext());
        questionnaireResponseDao.setSearchParamRegistry(searchParamRegistry());
        return questionnaireResponseDao;
    }

    @Bean(name = "myCodeSystemDaoR4")
    public IFhirResourceDaoCodeSystem<CodeSystem, Coding, CodeableConcept> codeSystemDao() {
        var codeSystemDao = new FhirResourceDaoCodeSystemR4();
        codeSystemDao.setResourceType(CodeSystem.class);
        codeSystemDao.setContext(fhirContext());
        codeSystemDao.setSearchParamRegistry(searchParamRegistry());
        return codeSystemDao;
    }

    @Bean(name = "myValueSetDaoR4")
    public IFhirResourceDaoValueSet<ValueSet, Coding, CodeableConcept> valueSetDao() {
        var valueSetDao = new FhirResourceDaoValueSetR4();
        valueSetDao.setResourceType(ValueSet.class);
        valueSetDao.setContext(fhirContext());
        valueSetDao.setSearchParamRegistry(searchParamRegistry());
        return valueSetDao;
    }

    @Bean(name = "myConceptMapDaoR4")
    public IFhirResourceDaoConceptMap<ConceptMap> conceptMapDao() {
        var conceptMapDao = new FhirResourceDaoConceptMapR4();
        conceptMapDao.setResourceType(ConceptMap.class);
        conceptMapDao.setContext(fhirContext());
        conceptMapDao.setSearchParamRegistry(searchParamRegistry());
        return conceptMapDao;
    }

    @Bean(name = "mySearchParameterDaoR4")
    public IFhirResourceDaoSearchParameter<SearchParameter> searchParameterDao() {
        var searchParameterDao = new FhirResourceDaoSearchParameterR4();
        searchParameterDao.setResourceType(SearchParameter.class);
        searchParameterDao.setContext(fhirContext());
        searchParameterDao.setSearchParamRegistry(searchParamRegistry());
        return searchParameterDao;
    }

    @Bean
    @Primary
    public ISearchParamRegistry searchParamRegistry() {
        return new SearchParamRegistryImpl();
    }
}
