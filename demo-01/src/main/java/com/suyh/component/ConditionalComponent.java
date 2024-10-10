package com.suyh.component;

import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2024-09-12
 */
@Component("ss")
public class ConditionalComponent {
    public boolean conditional(boolean flag) {
        return flag;
    }
}
