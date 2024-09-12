package com.suyh;

import cn.sticki.validator.spel.parse.EnableSpelValidatorBeanRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suyh
 * @since 2024-09-12
 */
@EnableSpelValidatorBeanRegistrar
@SpringBootApplication
public class SuyhMain01 {
    public static void main(String[] args) {
        SpringApplication.run(SuyhMain01.class);
    }
}
