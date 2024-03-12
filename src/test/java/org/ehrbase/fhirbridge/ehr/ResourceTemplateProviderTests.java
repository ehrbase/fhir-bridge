package org.ehrbase.fhirbridge.ehr;

import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.ehrbase.fhirbridge.exception.IODetailedException;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

class ResourceTemplateProviderTests {

    @Test
    void find() {
        ResourceTemplateProvider templateProvider = new ResourceTemplateProvider("classpath:/opt/");
        templateProvider.afterPropertiesSet();

        Optional<OPERATIONALTEMPLATE> result = templateProvider.find("Kennzeichnung Erregernachweis SARS-CoV-2");

        assertTrue(result.isPresent());
        assertEquals("Kennzeichnung Erregernachweis SARS-CoV-2", result.get().getConcept());
    }

    @Test
    void findWithInvalidTemplateId() {
        ResourceTemplateProvider templateProvider = new ResourceTemplateProvider("classpath:/opt/");
        templateProvider.afterPropertiesSet();

        Optional<OPERATIONALTEMPLATE> template = templateProvider.find("Invalid Template ID");

        assertTrue(template.isEmpty());
    }

    @Test
    void afterPropertiesSetThrowIODetailedException() {
        ResourceTemplateProvider templateProvider = new ResourceTemplateProvider("classpath:/opt1/");
        assertThrows(IODetailedException.class, templateProvider::afterPropertiesSet,
                AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION);
    }

}
