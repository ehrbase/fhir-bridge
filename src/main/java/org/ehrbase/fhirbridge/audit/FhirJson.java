package org.ehrbase.fhirbridge.audit;

import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Base64BinaryType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.audit.codes.NetworkAccessPointTypeCode;
import org.openehealth.ipf.commons.audit.model.ParticipantObjectIdentificationType;
import org.openehealth.ipf.commons.ihe.fhir.support.audit.marshal.FhirAuditJsonEvent;

public class FhirJson extends FhirAuditJsonEvent {

    @Override
    protected AuditEvent.AuditEventAgentNetworkType auditEventNetworkType(NetworkAccessPointTypeCode naptc) {
        if (naptc == null) {
            return null;
        }
        return super.auditEventNetworkType(naptc);
    }

    @Override
    protected AuditEvent.AuditEventEntityComponent participantObjectIdentificationToEntity(ParticipantObjectIdentificationType poit) {
        AuditEvent.AuditEventEntityComponent entity = new AuditEvent.AuditEventEntityComponent()
                .setWhat(new Reference().setIdentifier(new Identifier()
                        .setValue(poit.getParticipantObjectID())))
                // poit.getParticipantObjectIDTypeCode())) not used here
                .setType(new Coding()
                        .setCode(String.valueOf(poit.getParticipantObjectTypeCode().getValue()))
                        .setSystem("http://hl7.org/fhir/audit-entity-type"))
                .setRole(new Coding()
                        .setCode(String.valueOf(poit.getParticipantObjectTypeCodeRole().getValue()))
                        .setSystem("http://hl7.org/fhir/object-role"))
                .addSecurityLabel(new Coding()
                        .setCode(poit.getParticipantObjectSensitivity()))
                .setName(poit.getParticipantObjectName())
                // poit.getParticipantObjectDescription) not mappable here
                .setQuery(poit.getParticipantObjectQuery());

        if (poit.getParticipantObjectDataLifeCycle() != null) {
            entity.setLifecycle(new Coding()
                    .setCode(String.valueOf(poit.getParticipantObjectDataLifeCycle().getValue()))
                    .setSystem("http://hl7.org/fhir/dicom-audit-lifecycle"));
        }

        poit.getParticipantObjectDetails().forEach(tvp ->
                entity.addDetail(new AuditEvent.AuditEventEntityDetailComponent()
                        .setType(tvp.getType())
                        .setValue(new Base64BinaryType(tvp.getValue()))));

        return entity;

    }
}
