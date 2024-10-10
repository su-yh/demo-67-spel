package com.suyh.mvc.interceptor;

import com.suyh.component.AuditOperation;
import com.suyh.util.SpelParserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suyh
 * @since 2024-10-10
 */
@RequiredArgsConstructor
@Slf4j
public class AuditInterceptor implements HandlerInterceptor {

    // 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        HandlerMethod handlerMethod = (handler instanceof HandlerMethod) ? (HandlerMethod) handler : null;
        if (handlerMethod == null) {
            return true;
        }

        AuditOperation auditOperation = handlerMethod.getMethodAnnotation(AuditOperation.class);
        if (auditOperation == null) {
            return true;
        }

        String value = auditOperation.value();
        if (!StringUtils.hasText(value)) {
            return true;
        }

        SpelParserUtils.parse(value, null);

        return true;
    }
}
