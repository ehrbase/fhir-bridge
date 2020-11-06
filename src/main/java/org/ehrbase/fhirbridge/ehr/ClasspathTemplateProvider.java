package org.ehrbase.fhirbridge.ehr;

import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Optional;

// TODO: Check whether the templates should be cached or loaded during application startup as possible improvement.
public class ClasspathTemplateProvider implements TemplateProvider {

    @Override
    public Optional<OPERATIONALTEMPLATE> find(String templateId) {
        Resource templateResource = new ClassPathResource("/opt/" + templateId + ".opt");

        TemplateDocument document;
        try {
            document = TemplateDocument.Factory.parse(templateResource.getInputStream());
        } catch (XmlException | IOException e) {
            throw new FhirBridgeException("An error occurred while parsing the template [" + templateId + "]", e);
        }

        return Optional.of(document.getTemplate());
    }
}
