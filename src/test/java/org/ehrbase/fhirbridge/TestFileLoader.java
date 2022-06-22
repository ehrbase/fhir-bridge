package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestFileLoader {
    private final String directory;
    private final Class clazz;
    private final FhirContext context;


    public TestFileLoader(String directory, Class clazz, FhirContext context) {
        this.directory = directory;
        this.clazz = clazz;
        this.context = context;
    }

    public IBaseResource loadResource(String path) throws IOException {
        String resource = IOUtils.toString(new ClassPathResource(directory + path).getInputStream(), StandardCharsets.UTF_8);
        String fileEnding = path.substring(path.length() - 4);
        IParser parser;
        if (fileEnding.equals(".xml")) {
            parser = context.newXmlParser();
        } else if (fileEnding.equals("json")) {
            parser = context.newJsonParser();
        } else {
            throw new DataFormatException("File ending has to be either .json or .xml");
        }
        return parser.parseResource(clazz, resource);
    }


    public String loadResourceToString(String path) throws IOException {
        return IOUtils.toString(new ClassPathResource(directory + path).getInputStream(), StandardCharsets.UTF_8);
    }
}
