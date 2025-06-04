package org.dromara.system.domain.vo;

import org.dromara.system.domain.SecurityScoreDetail;
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
 * 安全评分明细视图对象 security_score_detail
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SecurityScoreDetail.class)
public class SecurityScoreDetailVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分明细ID
     */
    @ExcelProperty(value = "评分明细ID")
    private String detailId;

    /**
     * 关联的评分记录ID
     */
    @ExcelProperty(value = "关联的评分记录ID")
    private String ratingId;

    /**
     * 评分项ID
     */
    @ExcelProperty(value = "评分项ID")
    private String itemId;

    /**
     * 该项得分
     */
    @ExcelProperty(value = "该项得分")
    private Long score;

    /**
     * 评分原因
     */
    @ExcelProperty(value = "评分原因")
    private String reason;


}
