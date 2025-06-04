package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 安全检查规则对象 security_rule
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_rule")
public class SecurityRule extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    @TableId(value = "rule_id")
    private String ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则表达式（如：temperature > 30）
     */
    private String ruleExpression;

    /**
     * 严重级别
     */
    private String severity;

    /**
     * 警报消息
     */
    private String alertMessage;

    /**
     * 是否启用
     */
    private Long isActive;


}
