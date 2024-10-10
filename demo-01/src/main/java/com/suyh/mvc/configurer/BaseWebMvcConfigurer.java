package com.suyh.mvc.configurer;

import com.suyh.mvc.interceptor.AuditInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@RequiredArgsConstructor
public class BaseWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        AuditInterceptor auditInterceptor = new AuditInterceptor();
        registry.addInterceptor(auditInterceptor).addPathPatterns("/**");
    }
}
