package org.dromara.system.domain.bo;

import org.dromara.system.domain.SecurityScoreDetail;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 安全评分明细业务对象 security_score_detail
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SecurityScoreDetail.class, reverseConvertGenerate = false)
public class SecurityScoreDetailBo extends BaseEntity {

    /**
     * 评分明细ID
     */
    @NotBlank(message = "评分明细ID不能为空", groups = { EditGroup.class })
    private String detailId;

    /**
     * 关联的评分记录ID
     */
    @NotBlank(message = "关联的评分记录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ratingId;

    /**
     * 评分项ID
     */
    @NotBlank(message = "评分项ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemId;

    /**
     * 该项得分
     */
    @NotNull(message = "该项得分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long score;

    /**
     * 评分原因
     */
    private String reason;


}
