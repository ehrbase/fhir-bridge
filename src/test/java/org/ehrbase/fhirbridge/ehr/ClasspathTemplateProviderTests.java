package org.ehrbase.fhirbridge.ehr;

import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

import java.util.Optional;

class ClasspathTemplateProviderTests {

    private final TemplateProvider templateProvider = new ClasspathTemplateProvider();

    @Test
    void find() {
        Optional<OPERATIONALTEMPLATE> result = templateProvider.find("Kennzeichnung Erregernachweis SARS-CoV-2");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Kennzeichnung Erregernachweis SARS-CoV-2", result.get().getConcept());
    }

    @Test
    void findWithInvalidTemplateId() {
        Exception e = Assertions.assertThrows(FhirBridgeException.class, () -> templateProvider.find("Invalid Template ID"));

        Assertions.assertEquals("An error occurred while parsing the template [Invalid Template ID]", e.getMessage());
    }
}
