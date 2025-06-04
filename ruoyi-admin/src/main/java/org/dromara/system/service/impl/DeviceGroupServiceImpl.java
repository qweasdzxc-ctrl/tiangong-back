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
import org.dromara.system.domain.bo.DeviceGroupBo;
import org.dromara.system.domain.vo.DeviceGroupVo;
import org.dromara.system.domain.DeviceGroup;
import org.dromara.system.mapper.DeviceGroupMapper;
import org.dromara.system.service.IDeviceGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备分组Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class DeviceGroupServiceImpl implements IDeviceGroupService {

    private final DeviceGroupMapper baseMapper;

    /**
     * 查询设备分组
     *
     * @param groupId 主键
     * @return 设备分组
     */
    @Override
    public DeviceGroupVo queryById(String groupId){
        return baseMapper.selectVoById(groupId);
    }

    /**
     * 分页查询设备分组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备分组分页列表
     */
    @Override
    public TableDataInfo<DeviceGroupVo> queryPageList(DeviceGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DeviceGroup> lqw = buildQueryWrapper(bo);
        Page<DeviceGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备分组列表
     *
     * @param bo 查询条件
     * @return 设备分组列表
     */
    @Override
    public List<DeviceGroupVo> queryList(DeviceGroupBo bo) {
        LambdaQueryWrapper<DeviceGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DeviceGroup> buildQueryWrapper(DeviceGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DeviceGroup> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DeviceGroup::getGroupId);
        lqw.eq(StringUtils.isNotBlank(bo.getUserId()), DeviceGroup::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getGroupName()), DeviceGroup::getGroupName, bo.getGroupName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), DeviceGroup::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增设备分组
     *
     * @param bo 设备分组
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DeviceGroupBo bo) {
        DeviceGroup add = MapstructUtils.convert(bo, DeviceGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setGroupId(add.getGroupId());
        }
        return flag;
    }

    /**
     * 修改设备分组
     *
     * @param bo 设备分组
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DeviceGroupBo bo) {
        DeviceGroup update = MapstructUtils.convert(bo, DeviceGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DeviceGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备分组信息
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
