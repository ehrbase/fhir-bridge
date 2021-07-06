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

@Entity
@Table(name = "FB_RESOURCE_COMPOSITION")
public class ResourceComposition {

    @Id
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "COMPOSITION_ID")
    private String compositionId;

    public ResourceComposition() {
    }

    public ResourceComposition(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String id) {
        this.resourceId = id;
    }

    public String getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(String versionUid) {
        this.compositionId = versionUid;
    }

    @Override
    public String toString() {
        return "ResourceComposition{" +
                "resourceId='" + resourceId + '\'' +
                ", compositionId='" + compositionId + '\'' +
                '}';
    }
}
