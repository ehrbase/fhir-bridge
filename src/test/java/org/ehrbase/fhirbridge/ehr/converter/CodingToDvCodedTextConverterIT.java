package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.service.TerminologyService;
import org.hl7.fhir.r4.model.Coding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Required an external terminology server")
@SuppressWarnings("HttpUrlsUsage")
class CodingToDvCodedTextConverterIT {

    private static CodingToDvCodedTextConverter converter;

    @BeforeAll
    static void beforeAll() {
        FhirContext context = FhirContext.forR4();
        IGenericClient client = context.newRestfulGenericClient("https://r4.ontoserver.csiro.au/fhir");
        converter = CodingToDvCodedTextConverter.getInstance(new TerminologyService(client));
    }

    @Test
    void testConvert() {
        Coding coding = new Coding("http://hl7.org/fhir/address-type", "both", null);
        DvCodedText dvCodedText = converter.convert(coding);

        Assertions.assertEquals(coding.getSystem(), dvCodedText.getDefiningCode().getTerminologyId().getValue());
        Assertions.assertEquals(coding.getCode(), dvCodedText.getDefiningCode().getCodeString());
        Assertions.assertEquals("Postal & Physical", dvCodedText.getValue());
    }

    @Test
    void testConvertInvalidCoding() {
        Coding coding = new Coding("http://hl7.org/fhir/address-type", "aaaaa", null);

        Throwable ex = Assertions.assertThrows(ConversionException.class, () -> converter.convert(coding));

        Assertions.assertEquals("Cannot convert coding. Reason: Cannot find display for coding: " +
                "system=http://hl7.org/fhir/address-type, code=aaaaa", ex.getMessage());
    }
}
