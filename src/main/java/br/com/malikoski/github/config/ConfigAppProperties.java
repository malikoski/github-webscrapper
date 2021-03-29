package br.com.malikoski.github.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "app.github")
public class ConfigAppProperties {

    private String urlBase;
    private String nameClassHrefSearch;

}
