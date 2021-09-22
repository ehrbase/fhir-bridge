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

package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @param <R> FHIR resource type
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class CompositionConverter<R extends Resource, C extends CompositionEntity> extends AbstractConverter<R, C> {

    @Override
    public C convert(@NonNull R resource) {
        C composition = convertInternal(resource);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setFeederAudit(buildFeederAudit(resource));
        composition.setLanguage(resolveLanguageOrDefault(resource));
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setComposer(convertComposer(resource));
        convertHealthCareFacility(resource).ifPresent(composition::setHealthCareFacility);
        return composition;
    }

    protected abstract C convertInternal(R resource);

    protected abstract PartyProxy convertComposer(R resource);

    protected abstract Optional<PartyIdentified> convertHealthCareFacility(R resource);
}
