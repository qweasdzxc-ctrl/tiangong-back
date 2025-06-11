package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 设备分组对象 device_group
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("device_group")
public class DeviceGroup extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    @TableId(value = "group_id")
    private String groupId;

    /**
     * 所属用户ID
     */
    private String userId;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组描述
     */
    private String description;


}
