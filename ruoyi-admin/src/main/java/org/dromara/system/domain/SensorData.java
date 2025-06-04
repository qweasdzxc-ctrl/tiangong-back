package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 设备传感器数据记录对象 sensor_data
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sensor_data")
public class SensorData extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 传感器数据记录唯一标识
     */
    @TableId(value = "sensor_id")
    private String sensorId;

    /**
     * 数据来源设备ID
     */
    private String deviceId;

    /**
     * 温度值（摄氏度）
     */
    private Long temperature;

    /**
     * 湿度值（百分比）
     */
    private Long humidity;

    /**
     * 二氧化碳浓度（ppm）
     */
    private Long co2Level;

    /**
     * 功耗（瓦特）
     */
    private Long powerConsumption;

    /**
     * 门状态：开启/关闭
     */
    private String doorStatus;

    /**
     * 电池电量百分比
     */
    private Long batteryLevel;

    /**
     * 数据采集时间
     */
    private Date collectTime;


}
