package org.dromara.system.domain.bo;

import org.dromara.system.domain.AlarmLog;
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
 * 安全警报日志记录业务对象 alarm_log
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlarmLog.class, reverseConvertGenerate = false)
public class AlarmLogBo extends BaseEntity {

    /**
     * 警报日志唯一标识
     */
    @NotBlank(message = "警报日志唯一标识不能为空", groups = { EditGroup.class })
    private String logId;

    /**
     * 产生警报的设备ID
     */
    @NotBlank(message = "产生警报的设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "严重级别不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "警报产生时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date logTime;


}
