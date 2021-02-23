package org.ehrbase.fhirbridge.fhir.support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientIdRepository extends JpaRepository<PatientId, UUID> {
}
