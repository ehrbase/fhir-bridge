package org.ehrbase.fhirbridge.service;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import com.nedap.archie.rm.ehr.EhrStatus;
import org.ehrbase.client.openehrclient.EhrEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.core.domain.PatientEhr;
import org.ehrbase.fhirbridge.core.repository.PatientEhrRepository;
import org.ehrbase.fhirbridge.fhir.support.PatientUtils;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EhrStatusService {

    private final Logger log = LoggerFactory.getLogger(EhrStatusService.class);

    private final PatientEhrRepository patientEhrRepository;

    private final IFhirResourceDao<Patient> patientDao;

    private final EhrEndpoint ehrEndpoint;

    public EhrStatusService(PatientEhrRepository patientEhrRepository, IFhirResourceDao<Patient> patientDao,
                            OpenEhrClient openEhrClient) {
        this.patientEhrRepository = patientEhrRepository;
        this.patientDao = patientDao;
        this.ehrEndpoint = openEhrClient.ehrEndpoint();
    }

    @Async
    public void updateEhrStatus() throws InterruptedException {
        log.info("--- Start Update Process ---");
        Pageable pageable = PageRequest.of(0, 100);
        Page<PatientEhr> result = null;

        int count = 0;
        while (result == null || result.hasNext()) {
            result = patientEhrRepository.findAll(pageable);
            pageable = result.getPageable().next();

            for (var patientEhr : result) {
                handlePatient(patientEhr);
                log.info("Processing: {}/{}", ++count, result.getTotalElements());
            }
        }

        log.info("--- Update Process Completed ---");
    }

    private void handlePatient(PatientEhr patientEhr) {
        var patient = patientDao.read(new IdType("Patient", patientEhr.getPatientId()));
        UUID ehrId = patientEhr.getEhrId();
        Optional<EhrStatus> existing = ehrEndpoint.getEhrStatus(ehrId);
        if (existing.isEmpty()) {
            log.warn("Patient[id={}, ehr_id={}]", patientEhr.getPatientId(), patientEhr.getEhrId());
        } else {
            EhrStatus status = existing.get();
            Identifier pseudonym = PatientUtils.getPseudonym(patient);
            status.getSubject().getExternalRef().getId().setValue(pseudonym.getValue());
            ehrEndpoint.updateEhrStatus(ehrId, status);
        }
    }
}
