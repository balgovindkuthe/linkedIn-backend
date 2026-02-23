package com.linkedin.social.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

@Configuration
public class CorsConfig {

@Bean
public CorsConfigurationSource corsConfigurationSource() {
CorsConfiguration config = new CorsConfiguration();



config.setAllowedOriginPatterns(List.of("http://localhost:61478"));


config.setAllowCredentials(true);



config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
source.registerCorsConfiguration("/**", config);

return source;
}
}
