package com.suyh.aop;

import com.suyh.component.AuditOperation;
import com.suyh.util.SpelParserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * TODO: suyh - 我还是一直想要通过拦截器的方式来处理审计日志，但是拦截器的方式要如何取到方法参数名以及参数值呢，特别是这个参数值。
 *   我一直参考 SpringSecurity 的 @PreAuthorize  注解的实现，但是没看懂具体它是如何实现的。
 *
 * @author suyh
 * @since 2024-10-10
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditOperationAop {
    private final BeanFactory beanFactory;
    private BeanResolver beanResolver;

    @PostConstruct
    public void init() {
        beanResolver = new BeanFactoryResolver(beanFactory);
    }

    @Pointcut(value="@annotation(com.suyh.component.AuditOperation)")
    public void controller() {}

    @Before(value = "controller()")
    public void actionLog (JoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 参数
        Object[] args = joinPoint.getArgs();

        // 参数名称
        String[] parameterNames = signature.getParameterNames();

        // 目标方法
        Method targetMethod = signature.getMethod();

        // 方法上的日志注解
        AuditOperation auditOperation = targetMethod.getAnnotation(AuditOperation.class);
        if (auditOperation == null) {
            return;
        }

        @Language("SpEL") String spelExpression = auditOperation.value();
        if (!StringUtils.hasText(spelExpression)) {
            return;
        }

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(beanResolver);
        for (int i = 0; i < args.length; i ++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        SpelParserUtils.parse(context, spelExpression, null);
    }
}
