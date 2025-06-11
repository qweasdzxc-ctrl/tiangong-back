package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SecurityScore;
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
 * 设备安全评分视图对象 security_score
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SecurityScore.class)
public class SecurityScoreVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分记录唯一标识
     */
    @ExcelProperty(value = "评分记录唯一标识")
    private String ratingId;

    /**
     * 被评分的设备ID
     */
    @ExcelProperty(value = "被评分的设备ID")
    private String deviceId;

    /**
     * 安全总评分（0-100分）
     */
    @ExcelProperty(value = "安全总评分", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=-100分")
    private Long totalScore;

    /**
     * 评分计算时间
     */
    @ExcelProperty(value = "评分计算时间")
    private Date ratingTime;


}
