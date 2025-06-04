package org.dromara.system.domain.bo;

import org.dromara.system.domain.Device;
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
 * 智能家居设备信息业务对象 device
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Device.class, reverseConvertGenerate = false)
public class DeviceBo extends BaseEntity {

    /**
     * 设备唯一标识符
     */
    @NotBlank(message = "设备唯一标识符不能为空", groups = { EditGroup.class })
    private String deviceId;

    /**
     * 设备自定义名称
     */
    @NotBlank(message = "设备自定义名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceName;

    /**
     * 设备类型：门锁、摄像头、传感器等
     */
    @NotBlank(message = "设备类型：门锁、摄像头、传感器等不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceType;

    /**
     * 设备当前状态
     */
    @NotBlank(message = "设备当前状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 设备固件版本
     */
    private String firmwareVersion;

    /**
     * 设备MAC地址，用于唯一标识
     */
    @NotBlank(message = "设备MAC地址，用于唯一标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String macAddress;

    /**
     * 绑定的用户ID
     */
    private String bindUserId;

    /**
     * 设备所在位置ID
     */
    private String locationId;

    /**
     * 最后活跃时间
     */
    private Date lastActiveTime;


}
