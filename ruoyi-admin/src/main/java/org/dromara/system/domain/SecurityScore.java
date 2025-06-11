package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 设备安全评分对象 security_score
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("security_score")
public class SecurityScore extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分记录唯一标识
     */
    @TableId(value = "rating_id")
    private String ratingId;

    /**
     * 被评分的设备ID
     */
    private String deviceId;

    /**
     * 安全总评分（0-100分）
     */
    private Long totalScore;

    /**
     * 评分计算时间
     */
    private Date ratingTime;


}
