package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 设备权限策略对象 permission_policy
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("permission_policy")
public class PermissionPolicy extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     */
    @TableId(value = "policy_id")
    private String policyId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 允许的操作集合（逗号分隔）
     */
    private String allowedOperations;

    /**
     * 策略生效开始时间
     */
    private Date effectiveStartTime;

    /**
     * 策略生效结束时间
     */
    private Date effectiveEndTime;

    /**
     * 策略优先级（数值越小优先级越高）
     */
    private Long priority;

    /**
     * 策略是否启用
     */
    private Long isActive;


}
