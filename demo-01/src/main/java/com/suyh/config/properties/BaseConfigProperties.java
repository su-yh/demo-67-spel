package com.suyh.config.properties;

import com.suyh.config.provider.TgTemplateGroupSequenceProvider;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suyh
 * @since 2024-02-07
 */
@ConfigurationProperties(BaseConfigProperties.PREFIX)
@Data
@Validated
public class BaseConfigProperties {
    public static final String PREFIX = "suyh.spel";

    /**
     * flink 作业对应集群的web 监听ip
     */
    private String flinkWebHost = "localhost";
    /**
     * flink 作业对应集群的web 端口
     */
    private Integer flinkWebPort = 8081;

    private String flinkJobMode;
    private String flinkJobName;
    private String flinkJobId;
    private String flinkJobArgument;
    private Integer flinkJobArgumentDates;
    private List<String> flinkJobArgumentChannelList;

    @Valid
    private TgProperties tg = new TgProperties();

    @Data
    public static class TgProperties {
        @Valid
        private final TgTemplates templates = new TgTemplates();
    }

    @Data
    public static class TgTemplates {
        private final Map<String, String> customProperties = new HashMap<>();

        // 简化 ==============================================================
        /**
         * 服务启动时初始化成功
         */
        @Valid
        private final TgTemplate start = new TgTemplate();

        /**
         * 由于flink job 的状态变更为结束或者错误等状态时，结束当前监控服务进程。
         */
        @Valid
        private final TgTemplate stopByJob = new TgTemplate();
    }

    @GroupSequenceProvider(TgTemplateGroupSequenceProvider.class)
    @Data
    public static class TgTemplate {
        private boolean enabled = false;

        @NotNull(groups = TemplateGroup.class)
        private String template;

        public interface TemplateGroup {
        }
    }
}
