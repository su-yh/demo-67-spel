package com.suyh.config;

import com.suyh.component.AuditComponent;
import com.suyh.config.properties.BaseConfigProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author suyh
 * @since 2024-02-07
 */
@EnableConfigurationProperties(BaseConfigProperties.class)
@SpringBootConfiguration
public class BaseConfiguration {

    @Bean
    public AuditComponent audit() {
        return new AuditComponent();
    }
}
