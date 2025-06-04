package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 设备控制操作日志对象 control_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("control_log")
public class ControlLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 控制操作唯一标识
     */
    @TableId(value = "control_id")
    private String controlId;

    /**
     * 被操作的设备ID
     */
    private String deviceId;

    /**
     * 执行操作的用户ID
     */
    private String userId;

    /**
     * 操作类型
     */
    private String action;

    /**
     * 操作结果
     */
    private String result;

    /**
     * 操作执行时间
     */
    private Date actionTime;

    /**
     * 操作参数JSON
     */
    private String actionParams;

    /**
     * 关联会话ID
     */
    private String sessionId;

    /**
     * 操作地理位置（经纬度）
     */
    private String geolocation;

    /**
     * 操作风险等级
     */
    private String riskLevel;


}
