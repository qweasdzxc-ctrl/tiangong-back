package org.dromara.system.domain.bo;

import org.dromara.system.domain.DeviceGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 设备分组业务对象 device_group
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DeviceGroup.class, reverseConvertGenerate = false)
public class DeviceGroupBo extends BaseEntity {

    /**
     * 分组ID
     */
    @NotBlank(message = "分组ID不能为空", groups = { EditGroup.class })
    private String groupId;

    /**
     * 所属用户ID
     */
    @NotBlank(message = "所属用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userId;

    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupName;

    /**
     * 分组描述
     */
    private String description;


}
