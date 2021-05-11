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
import java.util.Objects;
import java.util.UUID;

/**
 * ResourceMap JPA Entity.
 *
 * @since 1.2.0
 */
@Entity
@Table(name = "FB_RESOURCE_MAP")
public class ResourceMap {

    @Id
    @Column(name = "RES_ID")
    private String id;

    @Column(name = "VERSION_UID")
    private UUID versionUid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getVersionUid() {
        return versionUid;
    }

    public void setVersionUid(UUID versionUid) {
        this.versionUid = versionUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResourceMap that = (ResourceMap) o;
        return Objects.equals(id, that.id) && Objects.equals(versionUid, that.versionUid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, versionUid);
    }

    @Override
    public String toString() {
        return "ResourceMap{" +
                "id='" + id + '\'' +
                ", versionUid=" + versionUid +
                '}';
    }
}
