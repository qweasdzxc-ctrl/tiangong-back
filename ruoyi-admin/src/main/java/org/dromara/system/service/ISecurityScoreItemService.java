package org.dromara.system.service;

import org.dromara.system.domain.vo.SecurityScoreItemVo;
import org.dromara.system.domain.bo.SecurityScoreItemBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 安全评分项定义Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface ISecurityScoreItemService {

    /**
     * 查询安全评分项定义
     *
     * @param itemId 主键
     * @return 安全评分项定义
     */
    SecurityScoreItemVo queryById(String itemId);

    /**
     * 分页查询安全评分项定义列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全评分项定义分页列表
     */
    TableDataInfo<SecurityScoreItemVo> queryPageList(SecurityScoreItemBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的安全评分项定义列表
     *
     * @param bo 查询条件
     * @return 安全评分项定义列表
     */
    List<SecurityScoreItemVo> queryList(SecurityScoreItemBo bo);

    /**
     * 新增安全评分项定义
     *
     * @param bo 安全评分项定义
     * @return 是否新增成功
     */
    Boolean insertByBo(SecurityScoreItemBo bo);

    /**
     * 修改安全评分项定义
     *
     * @param bo 安全评分项定义
     * @return 是否修改成功
     */
    Boolean updateByBo(SecurityScoreItemBo bo);

    /**
     * 校验并批量删除安全评分项定义信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
