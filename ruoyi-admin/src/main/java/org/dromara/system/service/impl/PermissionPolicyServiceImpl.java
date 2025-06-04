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
import org.dromara.system.domain.bo.PermissionPolicyBo;
import org.dromara.system.domain.vo.PermissionPolicyVo;
import org.dromara.system.domain.PermissionPolicy;
import org.dromara.system.mapper.PermissionPolicyMapper;
import org.dromara.system.service.IPermissionPolicyService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备权限策略Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class PermissionPolicyServiceImpl implements IPermissionPolicyService {

    private final PermissionPolicyMapper baseMapper;

    /**
     * 查询设备权限策略
     *
     * @param policyId 主键
     * @return 设备权限策略
     */
    @Override
    public PermissionPolicyVo queryById(String policyId){
        return baseMapper.selectVoById(policyId);
    }

    /**
     * 分页查询设备权限策略列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备权限策略分页列表
     */
    @Override
    public TableDataInfo<PermissionPolicyVo> queryPageList(PermissionPolicyBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PermissionPolicy> lqw = buildQueryWrapper(bo);
        Page<PermissionPolicyVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备权限策略列表
     *
     * @param bo 查询条件
     * @return 设备权限策略列表
     */
    @Override
    public List<PermissionPolicyVo> queryList(PermissionPolicyBo bo) {
        LambdaQueryWrapper<PermissionPolicy> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PermissionPolicy> buildQueryWrapper(PermissionPolicyBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PermissionPolicy> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(PermissionPolicy::getPolicyId);
        lqw.eq(StringUtils.isNotBlank(bo.getUserId()), PermissionPolicy::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), PermissionPolicy::getDeviceId, bo.getDeviceId());
        lqw.eq(StringUtils.isNotBlank(bo.getAllowedOperations()), PermissionPolicy::getAllowedOperations, bo.getAllowedOperations());
        lqw.eq(bo.getEffectiveStartTime() != null, PermissionPolicy::getEffectiveStartTime, bo.getEffectiveStartTime());
        lqw.eq(bo.getEffectiveEndTime() != null, PermissionPolicy::getEffectiveEndTime, bo.getEffectiveEndTime());
        lqw.eq(bo.getPriority() != null, PermissionPolicy::getPriority, bo.getPriority());
        lqw.eq(bo.getIsActive() != null, PermissionPolicy::getIsActive, bo.getIsActive());
        return lqw;
    }

    /**
     * 新增设备权限策略
     *
     * @param bo 设备权限策略
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PermissionPolicyBo bo) {
        PermissionPolicy add = MapstructUtils.convert(bo, PermissionPolicy.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setPolicyId(add.getPolicyId());
        }
        return flag;
    }

    /**
     * 修改设备权限策略
     *
     * @param bo 设备权限策略
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PermissionPolicyBo bo) {
        PermissionPolicy update = MapstructUtils.convert(bo, PermissionPolicy.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PermissionPolicy entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备权限策略信息
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
