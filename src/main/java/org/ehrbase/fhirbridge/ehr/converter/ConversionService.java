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

import org.ehrbase.fhirbridge.ehr.converter.generic.RMEntityConverter;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Resource;

import java.util.EnumMap;
import java.util.Map;

// TODO: Review
@SuppressWarnings({"rawtypes", "unchecked"})
public class ConversionService {

    private final Map<Profile, RMEntityConverter> converters = new EnumMap<>(Profile.class);

    public boolean canConvert(Profile profile) {
        return converters.containsKey(profile);
    }

    public Object convert(Profile profile, Resource resource) {
        if (!canConvert(profile)) {
            throw new ConversionException("No converter available for " + resource.getResourceType() + " with profile " + profile);
        }
        return converters.get(profile)
                .convert(resource);
    }

    public Object convertDefaultEncounter(Resource resource) {

        if(!converters.containsKey(Profile.STATIONAERER_VERSORGUNGSFALL)) {
            throw new ConversionException("No converter available for encounter with profile stationär Versorgungsfall" );
        }

        return converters.get(Profile.STATIONAERER_VERSORGUNGSFALL)
                .convert(resource);
    }

    public void registerConverter(Profile profile, RMEntityConverter<?, ?> converter) {
        converters.put(profile, converter);
    }
}
