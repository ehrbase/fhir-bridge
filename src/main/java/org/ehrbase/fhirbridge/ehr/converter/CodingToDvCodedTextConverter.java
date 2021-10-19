package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.service.TerminologyService;
import org.ehrbase.fhirbridge.service.TerminologyServiceException;
import org.ehrbase.fhirbridge.support.SpringContext;
import org.hl7.fhir.r4.model.Coding;
import org.springframework.beans.factory.ObjectProvider;

import java.util.Optional;

public final class CodingToDvCodedTextConverter implements Converter<Coding, DvCodedText> {

    private static CodingToDvCodedTextConverter instance;

    private final TerminologyService terminologyService;

    private CodingToDvCodedTextConverter(TerminologyService terminologyService) {
        this.terminologyService = terminologyService;
    }

    public static CodingToDvCodedTextConverter getInstance() {
        if (instance == null) {
            TerminologyService terminologyService = null;
            if (SpringContext.exists()) {
                ObjectProvider<TerminologyService> provider = SpringContext.getBeanProvider(TerminologyService.class);
                terminologyService = provider.getIfAvailable();
            }
            instance = new CodingToDvCodedTextConverter(terminologyService);
        }
        return instance;
    }

    public static CodingToDvCodedTextConverter getInstance(TerminologyService terminologyService) {
        if (instance == null) {
            instance = new CodingToDvCodedTextConverter(terminologyService);
        }
        return instance;
    }

    @Override
    public DvCodedText convert(Coding coding) {
        if (coding == null) {
            return null;
        }

        if (!coding.hasSystem() || !coding.hasCode()) {
            throw new ConversionException("Coding must have a system and a code");
        }

        String display;
        if (coding.hasDisplay()) {
            display = coding.getDisplay();
        } else {
            try {
                display = getDisplay(coding.getSystem(), coding.getCode())
                        .orElseThrow(() -> new ConversionException("Coding must have a display or TerminologyService must not be null"));
            } catch (TerminologyServiceException e) {
                throw new ConversionException("Cannot convert coding. Reason: " + e.getMessage(), e);
            }
        }

        CodePhrase codePhrase = new CodePhrase(new TerminologyId(coding.getSystem()), coding.getCode());
        return new DvCodedText(display, codePhrase);
    }

    private Optional<String> getDisplay(String system, String code) {
        if (terminologyService == null) {
            return Optional.empty();
        }
        return Optional.of(terminologyService.getDisplay(system, code));
    }
}
