package org.dromara.system.service;

import org.dromara.system.domain.vo.SecurityRuleVo;
import org.dromara.system.domain.bo.SecurityRuleBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 安全检查规则Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface ISecurityRuleService {

    /**
     * 查询安全检查规则
     *
     * @param ruleId 主键
     * @return 安全检查规则
     */
    SecurityRuleVo queryById(String ruleId);

    /**
     * 分页查询安全检查规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全检查规则分页列表
     */
    TableDataInfo<SecurityRuleVo> queryPageList(SecurityRuleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的安全检查规则列表
     *
     * @param bo 查询条件
     * @return 安全检查规则列表
     */
    List<SecurityRuleVo> queryList(SecurityRuleBo bo);

    /**
     * 新增安全检查规则
     *
     * @param bo 安全检查规则
     * @return 是否新增成功
     */
    Boolean insertByBo(SecurityRuleBo bo);

    /**
     * 修改安全检查规则
     *
     * @param bo 安全检查规则
     * @return 是否修改成功
     */
    Boolean updateByBo(SecurityRuleBo bo);

    /**
     * 校验并批量删除安全检查规则信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
