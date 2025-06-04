package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 安全评分明细对象 security_score_detail
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_score_detail")
public class SecurityScoreDetail extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分明细ID
     */
    @TableId(value = "detail_id")
    private String detailId;

    /**
     * 关联的评分记录ID
     */
    private String ratingId;

    /**
     * 评分项ID
     */
    private String itemId;

    /**
     * 该项得分
     */
    private Long score;

    /**
     * 评分原因
     */
    private String reason;


}
