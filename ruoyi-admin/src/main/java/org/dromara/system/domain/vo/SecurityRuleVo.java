package org.dromara.system.domain.vo;

import org.dromara.system.domain.SecurityRule;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 安全检查规则视图对象 security_rule
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SecurityRule.class)
public class SecurityRuleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    @ExcelProperty(value = "规则ID")
    private String ruleId;

    /**
     * 规则名称
     */
    @ExcelProperty(value = "规则名称")
    private String ruleName;

    /**
     * 规则表达式（如：temperature > 30）
     */
    @ExcelProperty(value = "规则表达式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=：temperature,>=,3=0")
    private String ruleExpression;

    /**
     * 严重级别
     */
    @ExcelProperty(value = "严重级别")
    private String severity;

    /**
     * 警报消息
     */
    @ExcelProperty(value = "警报消息")
    private String alertMessage;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用")
    private Long isActive;


}
