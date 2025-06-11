package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.AlarmLog;
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
 * 安全警报日志记录视图对象 alarm_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlarmLog.class)
public class AlarmLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 警报日志唯一标识
     */
    @ExcelProperty(value = "警报日志唯一标识")
    private String logId;

    /**
     * 产生警报的设备ID
     */
    @ExcelProperty(value = "产生警报的设备ID")
    private String deviceId;

    /**
     * 触发的规则ID
     */
    @ExcelProperty(value = "触发的规则ID")
    private String ruleId;

    /**
     * 警报类型
     */
    @ExcelProperty(value = "警报类型")
    private String alarmType;

    /**
     * 警报详细描述
     */
    @ExcelProperty(value = "警报详细描述")
    private String message;

    /**
     * 严重级别
     */
    @ExcelProperty(value = "严重级别")
    private String severity;

    /**
     * 处理状态：0=未处理，1=已处理
     */
    @ExcelProperty(value = "处理状态：0=未处理，1=已处理")
    private Long handled;

    /**
     * 处理人ID
     */
    @ExcelProperty(value = "处理人ID")
    private String handledBy;

    /**
     * 处理时间
     */
    @ExcelProperty(value = "处理时间")
    private Date handledTime;

    /**
     * 处理备注
     */
    @ExcelProperty(value = "处理备注")
    private String handlingNotes;

    /**
     * 警报产生时间
     */
    @ExcelProperty(value = "警报产生时间")
    private Date logTime;


}
