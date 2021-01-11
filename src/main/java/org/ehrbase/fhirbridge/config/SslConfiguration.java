package org.ehrbase.fhirbridge.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

/**
 * {@link Configuration Configuration} for FHIR validation.
 */
@Configuration
@EnableConfigurationProperties(SslProperties.class)
public class SslConfiguration {

    // TODO: Use SSL properties
    @Bean
    public HttpClient httpClient(SslProperties properties) throws IOException, GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new ClassPathResource("keystore/test.p12").getInputStream(), "test".toCharArray());

        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(new ClassPathResource("keystore/test.p12").getInputStream(), "test".toCharArray());

        SSLContext sslContext = new SSLContextBuilder()
                .loadKeyMaterial(keyStore, "test".toCharArray())
                .loadTrustMaterial(trustStore, new TrustAllStrategy())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
    }
}
