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
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Resource;

public abstract class AbstractCompositionConverter<R extends Resource, C extends CompositionEntity> implements Converter<R, C> {

    protected void mapDefaultAttributes(R resource, C composition) {
        composition.setFeederAudit(buildFeederAudit(resource));
        composition.setLanguage(resolveLanguage(resource.getLanguage()));
        composition.setTerritory(Territory.DE);
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf()); // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        composition.setLocation("test");
    }
}
