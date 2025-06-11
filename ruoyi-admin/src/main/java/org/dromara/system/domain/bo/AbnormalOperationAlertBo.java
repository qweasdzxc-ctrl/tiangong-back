package org.dromara.system.domain.bo;

import org.dromara.system.domain.AbnormalOperationAlert;
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
 * 异常操作告警业务对象 abnormal_operation_alert
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AbnormalOperationAlert.class, reverseConvertGenerate = false)
public class AbnormalOperationAlertBo extends BaseEntity {

    /**
     * 告警ID
     */
    @NotBlank(message = "告警ID不能为空", groups = { EditGroup.class })
    private String alertId;

    /**
     * 关联会话ID
     */
    @NotBlank(message = "关联会话ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sessionId;

    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userId;

    /**
     * 目标设备ID
     */
    @NotBlank(message = "目标设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 告警类型：未授权访问/行为异常/协议违规
     */
    @NotBlank(message = "告警类型：未授权访问/行为异常/协议违规不能为空", groups = { AddGroup.class, EditGroup.class })
    private String alertType;

    /**
     * 告警时间
     */
    @NotNull(message = "告警时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date alertTime;

    /**
     * 处理状态：待处理/已阻断/已审核
     */
    private String handlingStatus;


}
