/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springboot/CfgProperties.java to edit this template
 */
package caspvale.caspsuporte.common;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Hilton
 */
@Configuration
public class JacksonLocalDateConfigurations {

    private static final String datePattern = "dd/MM/yyyy";
    private static final String dateTimePattern = "dd/MM/yyyy HH:mm";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimePattern);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)));
        };
    }

}
