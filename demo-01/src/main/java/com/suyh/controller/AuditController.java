package com.suyh.controller;

import com.suyh.component.AuditOperation;
import com.suyh.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyh
 * @since 2024-10-10
 */
@RestController
@RequestMapping("/audit")
@Validated
@Slf4j
public class AuditController {
    /**
     * 这里我想做一下用一个注释来处理审计记录
     * 使用注解，拿到当前参数的值，并传递给到注解，并且在注解中调用一个 bean 的方法。
     */
    @AuditOperation("@audit.auditOperationLogRecord(#loginUser, #opt)")
    @RequestMapping(value = "/opt", method = RequestMethod.GET)
    public Boolean auditOpt(LoginUser loginUser, String opt) {
        log.info("controller audit opt. loginUser: {}, opt: {}", loginUser, opt);
        return true;
    }
}
