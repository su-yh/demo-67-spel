package com.suyh.service;

import com.suyh.config.properties.BaseConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author suyh
 * @since 2024-02-07
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleSpelService {
    // suyh - 下面的这些key 固定下来就不能修改，这些交由用户使用了。
    // 常量
    private static final String ARTIFACT_ID_KEY = "artifactId";
    private static final String VERSION_KEY = "version";
    private static final String FLINK_WEB_HOST_KEY = "flinkWebHost";
    private static final String FLINK_WEB_PORT_KEY = "flinkWebPort";
    private static final String FLINK_JOB_MODE_KEY = "flinkJobMode";
    private static final String FLINK_JOB_NAME_KEY = "flinkJobName";
    private static final String FLINK_JOB_ID_KEY = "flinkJobId";
    private static final String FLINK_JOB_ARGUMENT_KEY = "flinkJobArgument";
    private static final String FLINK_JOB_ARGUMENT_DATES_KEY = "flinkJobArgumentDates";
    private static final String FLINK_JOB_ARGUMENT_CHANNEL_LIST_KEY = "flinkJobArgumentChannelList";
    private static final String FLINK_JOB_SUBMIT_STATUS_KEY = "flinkJobSubmitStatus";

    // 变量
    private static final String FLINK_JOB_PREV_STATUS_KEY = "flinkJobPrevStatus";
    private static final String FLINK_JOB_CURR_STATUS_KEY = "flinkJobCurrStatus";
    private static final String CURR_EXCEPTION_MESSAGE_KEY = "currExceptionMessage";
    private static final String JOB_START_TIME_KEY = "jobStartTime";
    private static final String JOB_END_TIME_KEY = "jobEndTime";
    private static final String JOB_DURATION_MINUTE_KEY = "jobDurationMinute";

    private final BaseConfigProperties baseConfigProperties;
    private final ExpressionParser expressionParser = new SpelExpressionParser();
    private final StandardEvaluationContext evaluationContext = new StandardEvaluationContext();


    @PostConstruct
    public void init() {
        initEvaluationContextVariable();

        BaseConfigProperties.TgTemplate start = baseConfigProperties.getTg().getTemplates().getStart();
        Expression expression = expressionParser.parseExpression(start.getTemplate(), new TemplateParserContext());
        String message = expression.getValue(evaluationContext, String.class);
        System.out.println("message: " + message);
    }

    private void initEvaluationContextVariable() {
        evaluationContext.setVariable(FLINK_WEB_HOST_KEY, baseConfigProperties.getFlinkWebHost());
        evaluationContext.setVariable(FLINK_WEB_PORT_KEY, baseConfigProperties.getFlinkWebPort());
        evaluationContext.setVariable(FLINK_JOB_MODE_KEY, baseConfigProperties.getFlinkJobMode());
        evaluationContext.setVariable(FLINK_JOB_NAME_KEY, baseConfigProperties.getFlinkJobName());
        evaluationContext.setVariable(FLINK_JOB_ID_KEY, baseConfigProperties.getFlinkJobId());
        evaluationContext.setVariable(FLINK_JOB_ARGUMENT_KEY, baseConfigProperties.getFlinkJobArgument());
        evaluationContext.setVariable(FLINK_JOB_ARGUMENT_DATES_KEY, baseConfigProperties.getFlinkJobArgumentDates());
        evaluationContext.setVariable(FLINK_JOB_ARGUMENT_CHANNEL_LIST_KEY, baseConfigProperties.getFlinkJobArgumentChannelList());

    }
}
