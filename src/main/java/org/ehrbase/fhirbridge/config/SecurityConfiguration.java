package org.ehrbase.fhirbridge.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
    protected static class Oauth2AuthenticationConfiguration extends WebSecurityConfigurerAdapter {

        // TODO: Add OAuth 2.0 configuration
        public Oauth2AuthenticationConfiguration() {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
