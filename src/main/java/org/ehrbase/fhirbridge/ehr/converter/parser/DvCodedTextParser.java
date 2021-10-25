package org.ehrbase.fhirbridge.ehr.converter.parser;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.IntegerType;

import java.util.Optional;

public class DvCodedTextParser {

    private DvCodedTextParser() {
    }

    public static DvCodedText parseDefiningCode(EnumValueSet enumValueSet) {
        return new DvCodedText(enumValueSet.getValue(), new CodePhrase(new TerminologyId(enumValueSet.getTerminologyId(), ""), enumValueSet.getCode()));
    }

    public static DvCodedText parseBirthInteger(IntegerType multipleBirthIntegerType) {
        return new DvCodedText("amount of multiple births", new CodePhrase(new TerminologyId("", ""), multipleBirthIntegerType.toString()));
    }

    public static DvCodedText parseBirthBoolean(BooleanType multipleBirthBoolean) {
        return new DvCodedText("multiple births present", new CodePhrase(new TerminologyId("", ""), multipleBirthBoolean.toString()));
    }
}
