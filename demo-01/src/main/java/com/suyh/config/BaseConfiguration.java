package com.suyh.config;

import com.suyh.config.properties.BaseConfigProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author suyh
 * @since 2024-02-07
 */
@EnableConfigurationProperties(BaseConfigProperties.class)
@SpringBootConfiguration
public class BaseConfiguration {
}
