package org.ehrbase.fhirbridge.ehr;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DomainResource;

import java.util.ArrayList;
import java.util.List;

public class FeederAuditBuilder {

    private final IBaseResource resource;

    public FeederAuditBuilder(IBaseResource resource) {
        this.resource = resource;
    }

    public FeederAudit build() {
        FeederAudit fa = new FeederAudit();

        FeederAuditDetails fad = new FeederAuditDetails();
        fad.setSystemId("FHIR-bridge");

        fa.setOriginatingSystemAudit(fad);


        // set item id
        List<DvIdentifier> ids = new ArrayList<>();

        DvIdentifier id = new DvIdentifier();
        id.setId(resource.getIdElement().getValue());
        id.setType("fhir_logical_id");

        ids.add(id);
        fa.setOriginatingSystemItemIds(ids);


        // original content
        /* now the content is stored in the bridge
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser();
        String originalContent = parser.encodeResourceToString(fhirResource);

        DvParsable originalContentEnc = new DvParsable();
        originalContentEnc.setValue(originalContent);
        originalContentEnc.setFormalism("application/json");

        fa.setOriginalContent(originalContentEnc);
        */

        return fa;
    }
}
