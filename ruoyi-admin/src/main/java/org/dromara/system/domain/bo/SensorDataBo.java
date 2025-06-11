package org.dromara.system.domain.bo;

import org.dromara.system.domain.SensorData;
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
 * 设备传感器数据记录业务对象 sensor_data
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SensorData.class, reverseConvertGenerate = false)
public class SensorDataBo extends BaseEntity {

    /**
     * 传感器数据记录唯一标识
     */
    @NotBlank(message = "传感器数据记录唯一标识不能为空", groups = { EditGroup.class })
    private String sensorId;

    /**
     * 数据来源设备ID
     */
    @NotBlank(message = "数据来源设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "数据采集时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date collectTime;


}
