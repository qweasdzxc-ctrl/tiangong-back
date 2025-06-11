package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 设备与分组关联对象 device_group_mapping
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("device_group_mapping")
public class DeviceGroupMapping extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 映射ID
     */
    @TableId(value = "mapping_id")
    private String mappingId;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 分组ID
     */
    private String groupId;


}
