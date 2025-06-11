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
import org.dromara.system.domain.bo.DeviceGroupMappingBo;
import org.dromara.system.domain.vo.DeviceGroupMappingVo;
import org.dromara.system.domain.DeviceGroupMapping;
import org.dromara.system.mapper.DeviceGroupMappingMapper;
import org.dromara.system.service.IDeviceGroupMappingService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备与分组关联Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class DeviceGroupMappingServiceImpl implements IDeviceGroupMappingService {

    private final DeviceGroupMappingMapper baseMapper;

    /**
     * 查询设备与分组关联
     *
     * @param mappingId 主键
     * @return 设备与分组关联
     */
    @Override
    public DeviceGroupMappingVo queryById(String mappingId){
        return baseMapper.selectVoById(mappingId);
    }

    /**
     * 分页查询设备与分组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备与分组关联分页列表
     */
    @Override
    public TableDataInfo<DeviceGroupMappingVo> queryPageList(DeviceGroupMappingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DeviceGroupMapping> lqw = buildQueryWrapper(bo);
        Page<DeviceGroupMappingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备与分组关联列表
     *
     * @param bo 查询条件
     * @return 设备与分组关联列表
     */
    @Override
    public List<DeviceGroupMappingVo> queryList(DeviceGroupMappingBo bo) {
        LambdaQueryWrapper<DeviceGroupMapping> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DeviceGroupMapping> buildQueryWrapper(DeviceGroupMappingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DeviceGroupMapping> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(DeviceGroupMapping::getMappingId);
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), DeviceGroupMapping::getDeviceId, bo.getDeviceId());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupId()), DeviceGroupMapping::getGroupId, bo.getGroupId());
        return lqw;
    }

    /**
     * 新增设备与分组关联
     *
     * @param bo 设备与分组关联
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DeviceGroupMappingBo bo) {
        DeviceGroupMapping add = MapstructUtils.convert(bo, DeviceGroupMapping.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMappingId(add.getMappingId());
        }
        return flag;
    }

    /**
     * 修改设备与分组关联
     *
     * @param bo 设备与分组关联
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DeviceGroupMappingBo bo) {
        DeviceGroupMapping update = MapstructUtils.convert(bo, DeviceGroupMapping.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DeviceGroupMapping entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备与分组关联信息
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
