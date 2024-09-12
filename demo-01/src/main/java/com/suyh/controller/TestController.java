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
    public String test(@RequestBody @Valid TempVo tempVo) {

        return "OK";
    }

    @Data
    @SpelValid
    public static class TempVo {
        private boolean enabled = false;

        /**
         * 校验：当 {@link TempVo#enabled} 为true 时需要校验
         */
        @SpelNotNull(condition = "#this.enabled == true")
        private String template;

        /**
         * 调用 ss bean 对方的方法 conditional 若返回结果为true，则校验
         */
        @SpelNotNull(condition = "@ss.conditional(#this.enabled)")
        private String otherString;
    }
}
