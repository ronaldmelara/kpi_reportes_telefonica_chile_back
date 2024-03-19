package tech.telefonicachile.kpibackendapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir solicitudes desde localhost:3000
        config.addAllowedOrigin("http://localhost:3000");

        // Permitir solicitudes con los métodos HTTP: GET, POST, etc.
        config.addAllowedMethod("*");

        // Permitir el envío de ciertos encabezados
        config.addAllowedHeader("*");

        // Permitir el envío de cookies y credenciales
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }


}
