package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SensorData;
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
 * 设备传感器数据记录视图对象 sensor_data
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SensorData.class)
public class SensorDataVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 传感器数据记录唯一标识
     */
    @ExcelProperty(value = "传感器数据记录唯一标识")
    private String sensorId;

    /**
     * 数据来源设备ID
     */
    @ExcelProperty(value = "数据来源设备ID")
    private String deviceId;

    /**
     * 温度值（摄氏度）
     */
    @ExcelProperty(value = "温度值", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "摄=氏度")
    private Long temperature;

    /**
     * 湿度值（百分比）
     */
    @ExcelProperty(value = "湿度值", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "百=分比")
    private Long humidity;

    /**
     * 二氧化碳浓度（ppm）
     */
    @ExcelProperty(value = "二氧化碳浓度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "p=pm")
    private Long co2Level;

    /**
     * 功耗（瓦特）
     */
    @ExcelProperty(value = "功耗", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "瓦=特")
    private Long powerConsumption;

    /**
     * 门状态：开启/关闭
     */
    @ExcelProperty(value = "门状态：开启/关闭")
    private String doorStatus;

    /**
     * 电池电量百分比
     */
    @ExcelProperty(value = "电池电量百分比")
    private Long batteryLevel;

    /**
     * 数据采集时间
     */
    @ExcelProperty(value = "数据采集时间")
    private Date collectTime;


}
