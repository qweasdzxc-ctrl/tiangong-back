package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.PermissionPolicy;
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
 * 设备权限策略视图对象 permission_policy
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PermissionPolicy.class)
public class PermissionPolicyVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     */
    @ExcelProperty(value = "策略ID")
    private String policyId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private String userId;

    /**
     * 设备ID
     */
    @ExcelProperty(value = "设备ID")
    private String deviceId;

    /**
     * 允许的操作集合（逗号分隔）
     */
    @ExcelProperty(value = "允许的操作集合", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "逗=号分隔")
    private String allowedOperations;

    /**
     * 策略生效开始时间
     */
    @ExcelProperty(value = "策略生效开始时间")
    private Date effectiveStartTime;

    /**
     * 策略生效结束时间
     */
    @ExcelProperty(value = "策略生效结束时间")
    private Date effectiveEndTime;

    /**
     * 策略优先级（数值越小优先级越高）
     */
    @ExcelProperty(value = "策略优先级", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "数=值越小优先级越高")
    private Long priority;

    /**
     * 策略是否启用
     */
    @ExcelProperty(value = "策略是否启用")
    private Long isActive;


}
