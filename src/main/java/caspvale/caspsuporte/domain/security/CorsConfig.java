/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caspvale.caspsuporte.domain.security;

/**
 *
 * @author Hilton
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Permitir todas as origens (incluindo o uso com allowCredentials)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir os métodos HTTP que deseja suportar
                .allowedHeaders("*") // Permitir todos os cabeçalhos
                .allowCredentials(true); // Permitir o envio de credenciais, como cookies, caso necessário
    }
}
