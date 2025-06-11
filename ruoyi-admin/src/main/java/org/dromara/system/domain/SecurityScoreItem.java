package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 安全评分项定义对象 security_score_item
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_score_item")
public class SecurityScoreItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分项ID
     */
    @TableId(value = "item_id")
    private String itemId;

    /**
     * 评分项名称
     */
    private String itemName;

    /**
     * 评分项描述
     */
    private String description;

    /**
     * 权重（0-1之间）
     */
    private Long weight;

    /**
     * 是否启用
     */
    private Long isActive;


}
