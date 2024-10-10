package com.suyh.component;

import com.suyh.vo.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-10-10
 */
@Component("audit")
@Slf4j
@RequiredArgsConstructor
public class AuditComponent {
    public void recordTmp(LoginUser loginUser, String opt) {
        log.info("recordTmp, loginUser: {}, opt: {}", loginUser, opt);
    }
}
