package org.dromara.system.domain.bo;

import org.dromara.system.domain.DeviceGroupMapping;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 设备与分组关联业务对象 device_group_mapping
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = DeviceGroupMapping.class, reverseConvertGenerate = false)
public class DeviceGroupMappingBo extends BaseEntity {

    /**
     * 映射ID
     */
    @NotBlank(message = "映射ID不能为空", groups = { EditGroup.class })
    private String mappingId;

    /**
     * 设备ID
     */
    @NotBlank(message = "设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 分组ID
     */
    @NotBlank(message = "分组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupId;


}
