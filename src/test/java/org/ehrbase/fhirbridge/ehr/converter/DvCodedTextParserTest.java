package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.hl7.fhir.r4.model.Coding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@SuppressWarnings("HttpUrlsUsage")
class DvCodedTextParserTest {

    @Test
    void testConvert() {
        Coding coding = new Coding("http://snomed.info/sct", "408475000", "Diabetic medicine");
        Optional<DvCodedText> dvCodedText = DvCodedTextParser.getInstance().parseFHIRCoding(coding);

        Assertions.assertTrue(dvCodedText.isPresent());
        Assertions.assertEquals(coding.getSystem(), dvCodedText.get().getDefiningCode().getTerminologyId().getValue());
        Assertions.assertEquals(coding.getCode(), dvCodedText.get().getDefiningCode().getCodeString());
        Assertions.assertEquals(coding.getDisplay(), dvCodedText.get().getValue());
    }

    @Test
    void testConvertMissingDisplay() {
        Coding coding = new Coding("http://snomed.info/sct", "408475000", null);
        DvCodedTextParser converter = DvCodedTextParser.getInstance();
        Throwable ex = Assertions.assertThrows(ConversionException.class, () -> converter.parseFHIRCoding(coding));

        Assertions.assertEquals("Coding must have a display or TerminologyService must not be null", ex.getMessage());
    }
}
