package org.ehrbase.fhirbridge.audit;

import org.openehealth.ipf.commons.audit.AuditMetadataProvider;
import org.openehealth.ipf.commons.audit.TlsParameters;
import org.openehealth.ipf.commons.audit.protocol.AuditTransmissionProtocol;
import org.openehealth.ipf.commons.audit.protocol.UDPSyslogSenderImpl;

import java.nio.charset.StandardCharsets;

public class FhirJsonOverUdpSyslogProtocol extends UDPSyslogSenderImpl implements AuditTransmissionProtocol {

    public FhirJsonOverUdpSyslogProtocol() {
    }

    public FhirJsonOverUdpSyslogProtocol(TlsParameters tlsParameters) {
    }

    @Override
    protected byte[] getTransportPayload(AuditMetadataProvider auditMetadataProvider, String auditMessage) {
        String msg = String.format("<%s>1 %s %s %s %s %s - \uFEFF%s",
                85,
                auditMetadataProvider.getTimestamp(),
                auditMetadataProvider.getHostname(),
                auditMetadataProvider.getSendingApplication().replace(' ', '_'),
                auditMetadataProvider.getProcessID(),
                "FHIR+UDP",
                auditMessage);
        return msg.trim().getBytes(StandardCharsets.UTF_8);
    }
}
