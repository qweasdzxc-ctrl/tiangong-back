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
import org.dromara.system.domain.bo.DeviceBo;
import org.dromara.system.domain.vo.DeviceVo;
import org.dromara.system.domain.Device;
import org.dromara.system.mapper.DeviceMapper;
import org.dromara.system.service.IDeviceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 智能家居设备信息Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceMapper baseMapper;

    /**
     * 查询智能家居设备信息
     *
     * @param deviceId 主键
     * @return 智能家居设备信息
     */
    @Override
    public DeviceVo queryById(String deviceId){
        return baseMapper.selectVoById(deviceId);
    }

    /**
     * 分页查询智能家居设备信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 智能家居设备信息分页列表
     */
    @Override
    public TableDataInfo<DeviceVo> queryPageList(DeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        Page<DeviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的智能家居设备信息列表
     *
     * @param bo 查询条件
     * @return 智能家居设备信息列表
     */
    @Override
    public List<DeviceVo> queryList(DeviceBo bo) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Device> buildQueryWrapper(DeviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Device> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Device::getDeviceId);
        lqw.like(StringUtils.isNotBlank(bo.getDeviceName()), Device::getDeviceName, bo.getDeviceName());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceType()), Device::getDeviceType, bo.getDeviceType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Device::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getFirmwareVersion()), Device::getFirmwareVersion, bo.getFirmwareVersion());
        lqw.eq(StringUtils.isNotBlank(bo.getMacAddress()), Device::getMacAddress, bo.getMacAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getBindUserId()), Device::getBindUserId, bo.getBindUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getLocationId()), Device::getLocationId, bo.getLocationId());
        lqw.eq(bo.getLastActiveTime() != null, Device::getLastActiveTime, bo.getLastActiveTime());
        return lqw;
    }

    /**
     * 新增智能家居设备信息
     *
     * @param bo 智能家居设备信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DeviceBo bo) {
        Device add = MapstructUtils.convert(bo, Device.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDeviceId(add.getDeviceId());
        }
        return flag;
    }

    /**
     * 修改智能家居设备信息
     *
     * @param bo 智能家居设备信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DeviceBo bo) {
        Device update = MapstructUtils.convert(bo, Device.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Device entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除智能家居设备信息信息
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
