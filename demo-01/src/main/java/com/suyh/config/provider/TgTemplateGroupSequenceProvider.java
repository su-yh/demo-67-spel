package com.suyh.config.provider;

import com.suyh.config.properties.BaseConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyh
 * @since 2024-08-19
 */
@Slf4j
public class TgTemplateGroupSequenceProvider
        implements DefaultGroupSequenceProvider<BaseConfigProperties.TgTemplate> {

    @Override
    public List<Class<?>> getValidationGroups(BaseConfigProperties.TgTemplate tgTemplate) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(BaseConfigProperties.TgTemplate.class); // 这一步不能省,否则Default分组都不会执行了，会抛错的

        if (tgTemplate != null) {
            if (tgTemplate.isEnabled()) {
                defaultGroupSequence.add(BaseConfigProperties.TgTemplate.TemplateGroup.class);
            }
        }

        return defaultGroupSequence;
    }
}
