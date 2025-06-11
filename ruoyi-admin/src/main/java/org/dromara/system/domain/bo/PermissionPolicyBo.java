package org.dromara.system.domain.bo;

import org.dromara.system.domain.PermissionPolicy;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 设备权限策略业务对象 permission_policy
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PermissionPolicy.class, reverseConvertGenerate = false)
public class PermissionPolicyBo extends BaseEntity {

    /**
     * 策略ID
     */
    @NotBlank(message = "策略ID不能为空", groups = { EditGroup.class })
    private String policyId;

    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userId;

    /**
     * 设备ID
     */
    @NotBlank(message = "设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 允许的操作集合（逗号分隔）
     */
    @NotBlank(message = "允许的操作集合（逗号分隔）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String allowedOperations;

    /**
     * 策略生效开始时间
     */
    @NotNull(message = "策略生效开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date effectiveStartTime;

    /**
     * 策略生效结束时间
     */
    @NotNull(message = "策略生效结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date effectiveEndTime;

    /**
     * 策略优先级（数值越小优先级越高）
     */
    @NotNull(message = "策略优先级（数值越小优先级越高）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long priority;

    /**
     * 策略是否启用
     */
    @NotNull(message = "策略是否启用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isActive;


}
