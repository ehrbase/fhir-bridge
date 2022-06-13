package org.ehrbase.fhirbridge.config.security;

import org.ehrbase.fhirbridge.security.SmartOnFhirAuthorizationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * {@link Configuration} for Spring Security using OAuth2.
 *
 * @author Renaud Subiger
 * @since 1.6
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@ConditionalOnProperty(value = "fhir-bridge.security.type", havingValue = "oauth2")
public class OAuth2SecurityConfiguration {

    private final SecurityProperties properties;

    public OAuth2SecurityConfiguration(SecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri(properties.getOauth2().getJwkSetUri())
                .jwsAlgorithm(SignatureAlgorithm.from(properties.getOauth2().getJwsAlgorithm()))
                .build();
    }

    @Bean
    public SecurityFilterChain jwtSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests.anyRequest().authenticated());
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean
    public SmartOnFhirAuthorizationInterceptor authorizationInterceptor() {
        return new SmartOnFhirAuthorizationInterceptor();
    }
}
