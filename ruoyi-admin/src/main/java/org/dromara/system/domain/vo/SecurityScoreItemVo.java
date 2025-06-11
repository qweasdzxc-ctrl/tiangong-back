package org.dromara.system.domain.vo;

import org.dromara.system.domain.SecurityScoreItem;
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
 * 安全评分项定义视图对象 security_score_item
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SecurityScoreItem.class)
public class SecurityScoreItemVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分项ID
     */
    @ExcelProperty(value = "评分项ID")
    private String itemId;

    /**
     * 评分项名称
     */
    @ExcelProperty(value = "评分项名称")
    private String itemName;

    /**
     * 评分项描述
     */
    @ExcelProperty(value = "评分项描述")
    private String description;

    /**
     * 权重（0-1之间）
     */
    @ExcelProperty(value = "权重", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=-1之间")
    private Long weight;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "是否启用")
    private Long isActive;


}
