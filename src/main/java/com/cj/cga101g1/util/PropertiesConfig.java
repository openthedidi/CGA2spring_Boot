package com.cj.cga101g1.util;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource")
@Component
@Getter
@Setter
public class PropertiesConfig {
    private String url;
    private String username;
    private String password;
}
