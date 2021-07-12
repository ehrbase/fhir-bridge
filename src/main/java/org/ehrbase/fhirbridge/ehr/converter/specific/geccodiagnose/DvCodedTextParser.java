package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.hl7.fhir.r4.model.Coding;

import javax.swing.text.html.Option;
import java.util.Optional;

public class DvCodedTextParser {

    public static DvCodedText parseDefiningCode(EnumValueSet enumValueSet){
        return new DvCodedText(enumValueSet.getValue(), new CodePhrase(new TerminologyId(enumValueSet.getTerminologyId(), ""), enumValueSet.getCode()));
    }

    public static Optional<DvCodedText> parseFHIRCoding(Coding coding){
        if(coding.hasDisplay() && coding.hasSystem() && coding.hasCode()){
            return  Optional.of(new DvCodedText(coding.getDisplay(), new CodePhrase(new TerminologyId(coding.getSystem(), ""), coding.getCode())));
        }else if (coding.hasCode()){
            return Optional.of(new DvCodedText("", new CodePhrase(new TerminologyId(coding.getSystem(), ""), coding.getCode())));
        }else{
            return Optional.empty();
        }
    }
}
