package org.dromara.system.domain.bo;

import org.dromara.system.domain.SecurityScore;
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
 * 设备安全评分业务对象 security_score
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SecurityScore.class, reverseConvertGenerate = false)
public class SecurityScoreBo extends BaseEntity {

    /**
     * 评分记录唯一标识
     */
    @NotBlank(message = "评分记录唯一标识不能为空", groups = { EditGroup.class })
    private String ratingId;

    /**
     * 被评分的设备ID
     */
    @NotBlank(message = "被评分的设备ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 安全总评分（0-100分）
     */
    private Long totalScore;

    /**
     * 评分计算时间
     */
    @NotNull(message = "评分计算时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date ratingTime;


}
