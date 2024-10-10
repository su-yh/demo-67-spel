package com.suyh.util;

import cn.sticki.validator.spel.parse.SpelParser;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author suyh
 * @since 2024-10-10
 */
// suyh - 嘿嘿，我这里就是想用一下 注解： @Language("SpEL")  让idea 有提示功能和跳转功能
public class SpelParserUtils {
    @Nullable
    public static Object parse(@Language("SpEL") String expression, Object rootObject) {
        return SpelParser.parse(expression, rootObject);
    }

    @NotNull
    public static <T> T parse(@Language("SpEL") String expression, Object rootObject, Class<T> requiredType) {
        return SpelParser.parse(expression, rootObject, requiredType);
    }
}
