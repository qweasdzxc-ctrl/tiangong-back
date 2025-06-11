package org.dromara.system.domain.bo;

import org.dromara.system.domain.ControlLog;
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
 * 设备控制操作日志业务对象 control_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ControlLog.class, reverseConvertGenerate = false)
public class ControlLogBo extends BaseEntity {

    /**
     * 控制操作唯一标识
     */
    @NotBlank(message = "控制操作唯一标识不能为空", groups = { EditGroup.class })
    private String controlId;

    /**
     * 被操作的设备ID
     */
    @NotBlank(message = "被操作的设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 执行操作的用户ID
     */
    @NotBlank(message = "执行操作的用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userId;

    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String action;

    /**
     * 操作结果
     */
    @NotBlank(message = "操作结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String result;

    /**
     * 操作执行时间
     */
    @NotNull(message = "操作执行时间不能为空", groups = { AddGroup.class, EditGroup.class })
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
