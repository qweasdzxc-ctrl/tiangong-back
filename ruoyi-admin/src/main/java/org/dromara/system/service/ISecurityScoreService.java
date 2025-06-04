package org.dromara.system.service;

import org.dromara.system.domain.vo.SecurityScoreVo;
import org.dromara.system.domain.bo.SecurityScoreBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备安全评分Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface ISecurityScoreService {

    /**
     * 查询设备安全评分
     *
     * @param ratingId 主键
     * @return 设备安全评分
     */
    SecurityScoreVo queryById(String ratingId);

    /**
     * 分页查询设备安全评分列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备安全评分分页列表
     */
    TableDataInfo<SecurityScoreVo> queryPageList(SecurityScoreBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备安全评分列表
     *
     * @param bo 查询条件
     * @return 设备安全评分列表
     */
    List<SecurityScoreVo> queryList(SecurityScoreBo bo);

    /**
     * 新增设备安全评分
     *
     * @param bo 设备安全评分
     * @return 是否新增成功
     */
    Boolean insertByBo(SecurityScoreBo bo);

    /**
     * 修改设备安全评分
     *
     * @param bo 设备安全评分
     * @return 是否修改成功
     */
    Boolean updateByBo(SecurityScoreBo bo);

    /**
     * 校验并批量删除设备安全评分信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
