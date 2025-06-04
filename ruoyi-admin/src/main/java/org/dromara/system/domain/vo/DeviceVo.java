package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.Device;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 智能家居设备信息视图对象 device
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Device.class)
public class DeviceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识符
     */
    @ExcelProperty(value = "设备唯一标识符")
    private String deviceId;

    /**
     * 设备自定义名称
     */
    @ExcelProperty(value = "设备自定义名称")
    private String deviceName;

    /**
     * 设备类型：门锁、摄像头、传感器等
     */
    @ExcelProperty(value = "设备类型：门锁、摄像头、传感器等")
    private String deviceType;

    /**
     * 设备当前状态
     */
    @ExcelProperty(value = "设备当前状态")
    private String status;

    /**
     * 设备固件版本
     */
    @ExcelProperty(value = "设备固件版本")
    private String firmwareVersion;

    /**
     * 设备MAC地址，用于唯一标识
     */
    @ExcelProperty(value = "设备MAC地址，用于唯一标识")
    private String macAddress;

    /**
     * 绑定的用户ID
     */
    @ExcelProperty(value = "绑定的用户ID")
    private String bindUserId;

    /**
     * 设备所在位置ID
     */
    @ExcelProperty(value = "设备所在位置ID")
    private String locationId;

    /**
     * 最后活跃时间
     */
    @ExcelProperty(value = "最后活跃时间")
    private Date lastActiveTime;


}
