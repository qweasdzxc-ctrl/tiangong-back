package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 异常操作告警对象 abnormal_operation_alert
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("abnormal_operation_alert")
public class AbnormalOperationAlert extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 告警ID
     */
    @TableId(value = "alert_id")
    private String alertId;

    /**
     * 关联会话ID
     */
    private String sessionId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 目标设备ID
     */
    private String deviceId;

    /**
     * 告警类型：未授权访问/行为异常/协议违规
     */
    private String alertType;

    /**
     * 告警时间
     */
    private Date alertTime;

    /**
     * 处理状态：待处理/已阻断/已审核
     */
    private String handlingStatus;


}
