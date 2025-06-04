package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.AbnormalOperationAlert;
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
 * 异常操作告警视图对象 abnormal_operation_alert
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AbnormalOperationAlert.class)
public class AbnormalOperationAlertVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 告警ID
     */
    @ExcelProperty(value = "告警ID")
    private String alertId;

    /**
     * 关联会话ID
     */
    @ExcelProperty(value = "关联会话ID")
    private String sessionId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private String userId;

    /**
     * 目标设备ID
     */
    @ExcelProperty(value = "目标设备ID")
    private String deviceId;

    /**
     * 告警类型：未授权访问/行为异常/协议违规
     */
    @ExcelProperty(value = "告警类型：未授权访问/行为异常/协议违规")
    private String alertType;

    /**
     * 告警时间
     */
    @ExcelProperty(value = "告警时间")
    private Date alertTime;

    /**
     * 处理状态：待处理/已阻断/已审核
     */
    @ExcelProperty(value = "处理状态：待处理/已阻断/已审核")
    private String handlingStatus;


}
