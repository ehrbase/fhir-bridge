package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * {@link Configuration} for CORS.
 *
 * @since 1.1.0
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfiguration {

    @Bean
    public FilterRegistrationBean<CorsFilter> cors(CorsProperties properties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowCredentials(properties.isAllowCredentials());
        config.setAllowedHeaders(properties.getAllowedHeaders());
        config.setAllowedMethods(properties.getAllowedMethods());
        config.setAllowedOrigins(properties.getAllowedOrigins());
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); //this is what ensures CORS setting overrides all else
        return bean;
    }
}
