package org.ehrbase.fhirbridge.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;

/**
 * {@link Configuration Configuration} for Apache HTTP client.
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientConfiguration {


    @Bean
    public HttpClient httpClient(HttpClientProperties properties) throws Exception {
        HttpClientBuilder builder = HttpClients.custom();

        if (properties.getSsl().isEnabled()) {
            builder.setSSLContext(buildSSLContext(properties.getSsl()));
        }

        return builder.build();
    }

    private SSLContext buildSSLContext(HttpClientProperties.Ssl properties) throws Exception {
        SSLContextBuilder builder = SSLContextBuilder.create();

        if (properties.getKeyStoreType() != null) {
            builder.setKeyStoreType(properties.getKeyStoreType());
        }
        builder.loadKeyMaterial(ResourceUtils.getFile(properties.getKeyStore()),
                properties.getKeyStorePassword().toCharArray(),
                properties.getKeyPassword().toCharArray());

        if (properties.getTrustStoreType() != null) {
            builder.setKeyStoreType(properties.getTrustStoreType());
        }
        builder.loadTrustMaterial(ResourceUtils.getFile(properties.getTrustStore()),
                properties.getTrustStorePassword().toCharArray(), new TrustAllStrategy());

        return builder.build();
    }
}
