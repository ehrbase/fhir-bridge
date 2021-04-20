package org.ehrbase.fhirbridge.config;

import org.ehrbase.fhirbridge.security.SmartOnFhirAuthorizationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * {@link Configuration Configuration} for security.
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration {

    /**
     * {@link Configuration} for Basic Authentication.
     */
    @Configuration(proxyBeanMethods = false)
    @EnableWebSecurity
    @ConditionalOnProperty(value = "fhir-bridge.security.authentication-type", havingValue = "basic")
    protected static class BasicAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

        private static final String NOOP_PASSWORD_PREFIX = "{noop}";

        private final SecurityProperties properties;

        public BasicAuthenticationConfiguration(SecurityProperties properties) {
            this.properties = properties;
        }

        /**
         * @see WebSecurityConfigurerAdapter#configure(HttpSecurity)
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic();
            // @formatter:on
        }

        /**
         * @see WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth
                .inMemoryAuthentication()
                    .withUser(properties.getBasic().getUsername())
                    .password(NOOP_PASSWORD_PREFIX + properties.getBasic().getPassword())
                    .roles("");
            // @formatter:on
        }
    }

    /**
     * {@link Configuration} for OAuth 2.0.
     */
    @Configuration(proxyBeanMethods = false)
    @EnableWebSecurity
    @ConditionalOnProperty(value = "fhir-bridge.security.authentication-type", havingValue = "oauth2")
    @Import({OAuth2ResourceServerAutoConfiguration.class})
    protected static class Oauth2AuthenticationConfiguration extends WebSecurityConfigurerAdapter {

        private final SecurityProperties properties;

        public Oauth2AuthenticationConfiguration(SecurityProperties properties) {
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
        public SmartOnFhirAuthorizationInterceptor authorizationInterceptor() {
            return new SmartOnFhirAuthorizationInterceptor();
        }
    }
}
