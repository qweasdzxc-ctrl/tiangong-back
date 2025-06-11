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
import org.dromara.system.domain.bo.SensorDataBo;
import org.dromara.system.domain.vo.SensorDataVo;
import org.dromara.system.domain.SensorData;
import org.dromara.system.mapper.SensorDataMapper;
import org.dromara.system.service.ISensorDataService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备传感器数据记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SensorDataServiceImpl implements ISensorDataService {

    private final SensorDataMapper baseMapper;

    /**
     * 查询设备传感器数据记录
     *
     * @param sensorId 主键
     * @return 设备传感器数据记录
     */
    @Override
    public SensorDataVo queryById(String sensorId){
        return baseMapper.selectVoById(sensorId);
    }

    /**
     * 分页查询设备传感器数据记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备传感器数据记录分页列表
     */
    @Override
    public TableDataInfo<SensorDataVo> queryPageList(SensorDataBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SensorData> lqw = buildQueryWrapper(bo);
        Page<SensorDataVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备传感器数据记录列表
     *
     * @param bo 查询条件
     * @return 设备传感器数据记录列表
     */
    @Override
    public List<SensorDataVo> queryList(SensorDataBo bo) {
        LambdaQueryWrapper<SensorData> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SensorData> buildQueryWrapper(SensorDataBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SensorData> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SensorData::getSensorId);
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), SensorData::getDeviceId, bo.getDeviceId());
        lqw.eq(bo.getTemperature() != null, SensorData::getTemperature, bo.getTemperature());
        lqw.eq(bo.getHumidity() != null, SensorData::getHumidity, bo.getHumidity());
        lqw.eq(bo.getCo2Level() != null, SensorData::getCo2Level, bo.getCo2Level());
        lqw.eq(bo.getPowerConsumption() != null, SensorData::getPowerConsumption, bo.getPowerConsumption());
        lqw.eq(StringUtils.isNotBlank(bo.getDoorStatus()), SensorData::getDoorStatus, bo.getDoorStatus());
        lqw.eq(bo.getBatteryLevel() != null, SensorData::getBatteryLevel, bo.getBatteryLevel());
        lqw.eq(bo.getCollectTime() != null, SensorData::getCollectTime, bo.getCollectTime());
        return lqw;
    }

    /**
     * 新增设备传感器数据记录
     *
     * @param bo 设备传感器数据记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SensorDataBo bo) {
        SensorData add = MapstructUtils.convert(bo, SensorData.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSensorId(add.getSensorId());
        }
        return flag;
    }

    /**
     * 修改设备传感器数据记录
     *
     * @param bo 设备传感器数据记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SensorDataBo bo) {
        SensorData update = MapstructUtils.convert(bo, SensorData.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SensorData entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备传感器数据记录信息
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
