package org.ehrbase.fhirbridge.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IpfAtnaConfiguration {

//      json {
//          target => "AuditEvent"
//          source => "syslog_message"
//      }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public AuditContext auditContext(IpfAtnaConfigurationProperties config,
//                                     AuditTransmissionProtocol auditTransmissionProtocol,
//                                     AuditMessageQueue auditMessageQueue,
//                                     TlsParameters tlsParameters,
//                                     AuditMetadataProvider auditMetadataProvider,
//                                     AuditExceptionHandler auditExceptionHandler,
//                                     AuditMessagePostProcessor auditMessagePostProcessor,
//                                     @Value("${spring.application.name}") String appName) {
//
//        DefaultAuditContext auditContext = new DefaultAuditContext();
//        auditContext.setAuditEnabled(config.isAuditEnabled());
//        auditContext.setAuditSourceId(config.getAuditSourceId() != null ? config.getAuditSourceId() : appName);
//        auditContext.setAuditEnterpriseSiteId(config.getAuditEnterpriseSiteId());
//        auditContext.setAuditRepositoryHost(config.getAuditRepositoryHost());
//        auditContext.setAuditRepositoryPort(config.getAuditRepositoryPort());
//        auditContext.setAuditSource(config.getAuditSourceType());
//        auditContext.setIncludeParticipantsFromResponse(config.isIncludeParticipantsFromResponse());
//        auditContext.setAuditValueIfMissing(config.getAuditValueIfMissing());
//        auditContext.setTlsParameters(tlsParameters);
//        auditContext.setAuditMetadataProvider(auditMetadataProvider);
//        auditContext.setAuditTransmissionProtocol(auditTransmissionProtocol);
//        auditContext.setAuditMessageQueue(auditMessageQueue);
//        auditContext.setAuditExceptionHandler(auditExceptionHandler);
//        auditContext.setAuditMessagePostProcessor(auditMessagePostProcessor);
//        auditContext.setSerializationStrategy(new FhirJson());
//        return auditContext;
//}
}
