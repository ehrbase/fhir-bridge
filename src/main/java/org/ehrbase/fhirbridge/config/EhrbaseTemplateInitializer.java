package org.ehrbase.fhirbridge.config;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.xmlbeans.XmlOptions;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.config.ehrbase.AuthorizationType;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class EhrbaseTemplateInitializer implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(EhrbaseTemplateInitializer.class);

    private final EhrbaseProperties properties;

    private final ResourceTemplateProvider templateProvider;

    private final OpenEhrClient openEhrClient;

    private final HttpClient httpClient;

    public EhrbaseTemplateInitializer(EhrbaseProperties properties, ResourceTemplateProvider templateProvider, OpenEhrClient openEhrClient) {
        this.properties = properties;
        this.templateProvider = templateProvider;
        this.openEhrClient = openEhrClient;
        this.httpClient = adminHttpClient();
    }

    @Override
    public void afterPropertiesSet() {
        for (String templateId : templateProvider.getTemplateIds()) {
            LOG.info("Initializing template '{}'", templateId);

            Optional<OPERATIONALTEMPLATE> ehrbaseTemplate = openEhrClient.templateEndpoint()
                    .findTemplate(templateId);

            if (ehrbaseTemplate.isEmpty()) {
                createTemplate(templateId);
            } else {
                updateTemplate(templateId);
            }
        }
    }

    private void createTemplate(String templateId) {
        openEhrClient.templateEndpoint().ensureExistence(templateId);
    }

    private void updateTemplate(String templateId) {
        OPERATIONALTEMPLATE fhirBridgeTemplate = templateProvider.find(templateId)
                .orElseThrow(() -> new IllegalStateException("Failed to load template with id " + templateId));

        HttpResponse response;
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl())
                    .path("/admin/template/{templateId}")
                    .build(templateId);

            XmlOptions options = new XmlOptions();
            options.setSaveSyntheticDocumentElement(new QName("http://schemas.openehr.org/v1", "template"));

            HttpPut request = new HttpPut(uri);
            request.setEntity(new StringEntity(fhirBridgeTemplate.xmlText(options), ContentType.APPLICATION_XML));
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new IllegalStateException("An error occurred while sending the template with id " + templateId);
        }

        HttpStatus status = HttpStatus.valueOf(response.getStatusLine().getStatusCode());
        if (status.isError()) {
            throw new IllegalStateException("An error occurred in EHRbase while updating the template with id " + templateId);
        }
    }

    private HttpClient adminHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        EhrbaseProperties.Security security = properties.getSecurity();
        if (security.getType() == AuthorizationType.BASIC_AUTH) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(security.getAdminUser(), security.getAdminPassword()));
            builder.setDefaultCredentialsProvider(credentialsProvider);
        }
        return builder.build();
    }
}
