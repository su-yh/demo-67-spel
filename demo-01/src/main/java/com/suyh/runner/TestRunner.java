package com.suyh.runner;

import cn.sticki.validator.spel.parse.SpelParser;
import com.suyh.controller.TestController;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-09-12
 */
@Component
public class TestRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        TestController.TempVo vo = new TestController.TempVo();
        Object parse = SpelParser.parse("@ss.conditional(#this.enabled)", vo);
        Boolean res = SpelParser.parse("@ss.conditional(#this.enabled)", vo, Boolean.class);
        System.out.println(parse);
        System.out.println(res);
    }
}
