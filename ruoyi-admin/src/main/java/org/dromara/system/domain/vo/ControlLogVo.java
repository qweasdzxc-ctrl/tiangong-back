package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.ControlLog;
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
 * 设备控制操作日志视图对象 control_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ControlLog.class)
public class ControlLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 控制操作唯一标识
     */
    @ExcelProperty(value = "控制操作唯一标识")
    private String controlId;

    /**
     * 被操作的设备ID
     */
    @ExcelProperty(value = "被操作的设备ID")
    private String deviceId;

    /**
     * 执行操作的用户ID
     */
    @ExcelProperty(value = "执行操作的用户ID")
    private String userId;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型")
    private String action;

    /**
     * 操作结果
     */
    @ExcelProperty(value = "操作结果")
    private String result;

    /**
     * 操作执行时间
     */
    @ExcelProperty(value = "操作执行时间")
    private Date actionTime;

    /**
     * 操作参数JSON
     */
    @ExcelProperty(value = "操作参数JSON")
    private String actionParams;

    /**
     * 关联会话ID
     */
    @ExcelProperty(value = "关联会话ID")
    private String sessionId;

    /**
     * 操作地理位置（经纬度）
     */
    @ExcelProperty(value = "操作地理位置", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "经=纬度")
    private String geolocation;

    /**
     * 操作风险等级
     */
    @ExcelProperty(value = "操作风险等级")
    private String riskLevel;


}
