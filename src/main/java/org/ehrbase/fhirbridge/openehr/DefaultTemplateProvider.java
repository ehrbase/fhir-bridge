/*
 * Copyright 2022 the original author or authors.
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

package org.ehrbase.fhirbridge.openehr;

import org.apache.xmlbeans.XmlException;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Renaud Subiger
 * @since 1.6
 */
public class DefaultTemplateProvider implements TemplateProvider, ApplicationContextAware {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Cache templateCache;

    private ApplicationContext applicationContext;

    public DefaultTemplateProvider(Cache cache) {
        this.templateCache = cache;
    }

    @PostConstruct
    public void initialize() throws IOException, XmlException {
        Resource[] resources = applicationContext.getResources("classpath:/opt/*.opt");

        log.info("Initializing openEHR templates...");

        for (var resource : resources) {
            var templateDocument = TemplateDocument.Factory.parse(resource.getInputStream());
            var template = templateDocument.getTemplate();
            templateCache.put(template.getTemplateId().getValue(), template);
        }
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {
        var template = templateCache.get(templateId, OPERATIONALTEMPLATE.class);
        return Optional.ofNullable(template);
    }

    @SuppressWarnings("unchecked")
    public Set<String> getTemplateIds() {
        var templateIds = new HashSet<String>();
        var iterator = ((javax.cache.Cache<String, ?>) templateCache.getNativeCache()).iterator();
        iterator.forEachRemaining(entry -> templateIds.add(entry.getKey()));
        return templateIds;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
