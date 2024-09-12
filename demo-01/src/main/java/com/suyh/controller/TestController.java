package com.suyh.controller;

import cn.sticki.validator.spel.SpelValid;
import cn.sticki.validator.spel.constrain.SpelNotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author suyh
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/suyh")
@Validated
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody @Valid TgTemplate tgTemplate) {

        return "OK";
    }

    @Data
    @SpelValid
    public static class TgTemplate {
        private boolean enabled = false;

        @SpelNotNull(condition = "#this.enabled == true")
        private String template;

        public interface TemplateGroup {
        }
    }
}
