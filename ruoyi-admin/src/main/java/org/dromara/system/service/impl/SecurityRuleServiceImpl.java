package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SecurityRuleBo;
import org.dromara.system.domain.vo.SecurityRuleVo;
import org.dromara.system.domain.SecurityRule;
import org.dromara.system.mapper.SecurityRuleMapper;
import org.dromara.system.service.ISecurityRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安全检查规则Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SecurityRuleServiceImpl implements ISecurityRuleService {

    private final SecurityRuleMapper baseMapper;

    /**
     * 查询安全检查规则
     *
     * @param ruleId 主键
     * @return 安全检查规则
     */
    @Override
    public SecurityRuleVo queryById(String ruleId){
        return baseMapper.selectVoById(ruleId);
    }

    /**
     * 分页查询安全检查规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全检查规则分页列表
     */
    @Override
    public TableDataInfo<SecurityRuleVo> queryPageList(SecurityRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SecurityRule> lqw = buildQueryWrapper(bo);
        Page<SecurityRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的安全检查规则列表
     *
     * @param bo 查询条件
     * @return 安全检查规则列表
     */
    @Override
    public List<SecurityRuleVo> queryList(SecurityRuleBo bo) {
        LambdaQueryWrapper<SecurityRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SecurityRule> buildQueryWrapper(SecurityRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SecurityRule> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SecurityRule::getRuleId);
        lqw.like(StringUtils.isNotBlank(bo.getRuleName()), SecurityRule::getRuleName, bo.getRuleName());
        lqw.eq(StringUtils.isNotBlank(bo.getRuleExpression()), SecurityRule::getRuleExpression, bo.getRuleExpression());
        lqw.eq(StringUtils.isNotBlank(bo.getSeverity()), SecurityRule::getSeverity, bo.getSeverity());
        lqw.eq(StringUtils.isNotBlank(bo.getAlertMessage()), SecurityRule::getAlertMessage, bo.getAlertMessage());
        lqw.eq(bo.getIsActive() != null, SecurityRule::getIsActive, bo.getIsActive());
        return lqw;
    }

    /**
     * 新增安全检查规则
     *
     * @param bo 安全检查规则
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SecurityRuleBo bo) {
        SecurityRule add = MapstructUtils.convert(bo, SecurityRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRuleId(add.getRuleId());
        }
        return flag;
    }

    /**
     * 修改安全检查规则
     *
     * @param bo 安全检查规则
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SecurityRuleBo bo) {
        SecurityRule update = MapstructUtils.convert(bo, SecurityRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SecurityRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除安全检查规则信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
