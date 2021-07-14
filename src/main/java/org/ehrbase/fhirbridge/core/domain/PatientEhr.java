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

package org.ehrbase.fhirbridge.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "FB_PATIENT_EHR")
public class PatientEhr {

    @Id
    @Column(name = "PATIENT_ID")
    private String patientId;

    @NotNull
    @Column(name = "EHR_ID")
    private UUID ehrId;

    public PatientEhr() {
    }

    public PatientEhr(String patientId, UUID ehrId) {
        this.patientId = patientId;
        this.ehrId = ehrId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public UUID getEhrId() {
        return ehrId;
    }

    public void setEhrId(UUID ehrId) {
        this.ehrId = ehrId;
    }

    @Override
    public String toString() {
        return "PatientEhr{" +
                "patientId='" + patientId + '\'' +
                ", ehrId=" + ehrId +
                '}';
    }
}
