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

package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Resource;

public abstract class AbstractEntryEntityConverter<R extends Resource, E extends EntryEntity> implements Converter<R, E> {

    protected void convert(R resource, E entry) {
        entry.setLanguage(resolveLanguage(resource.getLanguage()));
        entry.setSubject(new PartySelf());
        entry.setFeederAudit(buildFeederAudit(resource));
    }
}
