package org.dromara.system.domain.bo;

import org.dromara.system.domain.SecurityRule;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 安全检查规则业务对象 security_rule
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SecurityRule.class, reverseConvertGenerate = false)
public class SecurityRuleBo extends BaseEntity {

    /**
     * 规则ID
     */
    @NotBlank(message = "规则ID不能为空", groups = { EditGroup.class })
    private String ruleId;

    /**
     * 规则名称
     */
    @NotBlank(message = "规则名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ruleName;

    /**
     * 规则表达式（如：temperature > 30）
     */
    @NotBlank(message = "规则表达式（如：temperature > 30）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ruleExpression;

    /**
     * 严重级别
     */
    @NotBlank(message = "严重级别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String severity;

    /**
     * 警报消息
     */
    @NotBlank(message = "警报消息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String alertMessage;

    /**
     * 是否启用
     */
    @NotNull(message = "是否启用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isActive;


}
