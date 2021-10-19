package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.hl7.fhir.r4.model.Coding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("HttpUrlsUsage")
class CodingToDvCodedTextConverterTest {

    @Test
    void testConvert() {
        Coding coding = new Coding("http://snomed.info/sct", "408475000", "Diabetic medicine");
        CodingToDvCodedTextConverter converter = CodingToDvCodedTextConverter.getInstance();
        DvCodedText dvCodedText = converter.convert(coding);

        Assertions.assertEquals(coding.getSystem(), dvCodedText.getDefiningCode().getTerminologyId().getValue());
        Assertions.assertEquals(coding.getCode(), dvCodedText.getDefiningCode().getCodeString());
        Assertions.assertEquals(coding.getDisplay(), dvCodedText.getValue());
    }

    @Test
    void testConvertMissingDisplay() {
        Coding coding = new Coding("http://snomed.info/sct", "408475000", null);
        CodingToDvCodedTextConverter converter = CodingToDvCodedTextConverter.getInstance();
        Throwable ex = Assertions.assertThrows(ConversionException.class, () -> converter.convert(coding));

        Assertions.assertEquals("Coding must have a display or TerminologyService must not be null", ex.getMessage());
    }
}
