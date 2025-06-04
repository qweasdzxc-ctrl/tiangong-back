package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 安全警报日志记录对象 alarm_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("alarm_log")
public class AlarmLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 警报日志唯一标识
     */
    @TableId(value = "log_id")
    private String logId;

    /**
     * 产生警报的设备ID
     */
    private String deviceId;

    /**
     * 触发的规则ID
     */
    private String ruleId;

    /**
     * 警报类型
     */
    private String alarmType;

    /**
     * 警报详细描述
     */
    private String message;

    /**
     * 严重级别
     */
    private String severity;

    /**
     * 处理状态：0=未处理，1=已处理
     */
    private Long handled;

    /**
     * 处理人ID
     */
    private String handledBy;

    /**
     * 处理时间
     */
    private Date handledTime;

    /**
     * 处理备注
     */
    private String handlingNotes;

    /**
     * 警报产生时间
     */
    private Date logTime;


}
