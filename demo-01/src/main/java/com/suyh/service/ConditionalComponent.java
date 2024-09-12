package com.suyh.service;

import org.springframework.stereotype.Service;

/**
 * @author suyh
 * @since 2024-09-12
 */
@Service("ss")
public class ConditionalComponent {
    public boolean conditional(boolean flag) {
        return flag;
    }
}
