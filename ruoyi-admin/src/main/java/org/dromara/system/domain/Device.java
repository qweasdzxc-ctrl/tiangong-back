package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 智能家居设备信息对象 device
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("device")
public class Device extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识符
     */
    @TableId(value = "device_id")
    private String deviceId;

    /**
     * 设备自定义名称
     */
    private String deviceName;

    /**
     * 设备类型：门锁、摄像头、传感器等
     */
    private String deviceType;

    /**
     * 设备当前状态
     */
    private String status;

    /**
     * 设备固件版本
     */
    private String firmwareVersion;

    /**
     * 设备MAC地址，用于唯一标识
     */
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
