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
import org.springframework.core.annotation.Order;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author suyh
 * @since 2024-10-10
 */
@Aspect
@Component
@Order(-1)
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

        SpelParserUtils.parse(spelExpression, null, context);
    }
}
