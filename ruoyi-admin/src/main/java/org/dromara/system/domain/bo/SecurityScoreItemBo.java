package org.dromara.system.domain.bo;

import org.dromara.system.domain.SecurityScoreItem;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 安全评分项定义业务对象 security_score_item
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SecurityScoreItem.class, reverseConvertGenerate = false)
public class SecurityScoreItemBo extends BaseEntity {

    /**
     * 评分项ID
     */
    @NotBlank(message = "评分项ID不能为空", groups = { EditGroup.class })
    private String itemId;

    /**
     * 评分项名称
     */
    @NotBlank(message = "评分项名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemName;

    /**
     * 评分项描述
     */
    private String description;

    /**
     * 权重（0-1之间）
     */
    @NotNull(message = "权重（0-1之间）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long weight;

    /**
     * 是否启用
     */
    @NotNull(message = "是否启用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isActive;


}
