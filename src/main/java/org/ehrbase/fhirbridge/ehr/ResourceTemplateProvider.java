package org.ehrbase.fhirbridge.ehr;

import org.apache.xmlbeans.XmlException;
import org.ehrbase.fhirbridge.exception.IODetailedException;
import org.ehrbase.fhirbridge.exception.ParsingTemplateException;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.AN_ERROR_OCCURRED_WHILE_PARSING_TEMPLATE;
import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION;

public class ResourceTemplateProvider implements TemplateProvider, InitializingBean {

    private final ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();

    private final Map<String, String> templates = new HashMap<>();

    private final String prefix;

    public ResourceTemplateProvider(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {
        String templateFilename = templates.get(templateId);
        if (templateFilename == null) {
            return Optional.empty();
        }

        Resource resource = resourceLoader.getResource(prefix + templateFilename);
        return Optional.of(parse(resource));
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Arrays.stream(resourceLoader.getResources(prefix + "*.opt"))
                    .forEach(resource -> {
                        OPERATIONALTEMPLATE template = parse(resource);
                        templates.put(template.getTemplateId().getValue(), resource.getFilename());
                    });
        } catch (IOException e) {
            throw new IODetailedException(ResourceTemplateProvider.class, AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION, e.getMessage());
        }
    }

    public Iterable<String> getTemplateIds() {
        return templates.keySet();
    }

    private OPERATIONALTEMPLATE parse(Resource resource) {
        try {
            TemplateDocument document = TemplateDocument.Factory.parse(resource.getInputStream());
            return document.getTemplate();
        } catch (XmlException | IOException e) {
            throw new ParsingTemplateException(ResourceTemplateProvider.class, AN_ERROR_OCCURRED_WHILE_PARSING_TEMPLATE,
                    String.format(AN_ERROR_OCCURRED_WHILE_PARSING_TEMPLATE, resource.getFilename()));
        }
    }
}
