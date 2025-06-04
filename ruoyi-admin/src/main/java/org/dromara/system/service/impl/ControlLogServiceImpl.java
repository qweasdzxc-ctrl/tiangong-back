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
import org.dromara.system.domain.bo.ControlLogBo;
import org.dromara.system.domain.vo.ControlLogVo;
import org.dromara.system.domain.ControlLog;
import org.dromara.system.mapper.ControlLogMapper;
import org.dromara.system.service.IControlLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备控制操作日志Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class ControlLogServiceImpl implements IControlLogService {

    private final ControlLogMapper baseMapper;

    /**
     * 查询设备控制操作日志
     *
     * @param controlId 主键
     * @return 设备控制操作日志
     */
    @Override
    public ControlLogVo queryById(String controlId){
        return baseMapper.selectVoById(controlId);
    }

    /**
     * 分页查询设备控制操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备控制操作日志分页列表
     */
    @Override
    public TableDataInfo<ControlLogVo> queryPageList(ControlLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ControlLog> lqw = buildQueryWrapper(bo);
        Page<ControlLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备控制操作日志列表
     *
     * @param bo 查询条件
     * @return 设备控制操作日志列表
     */
    @Override
    public List<ControlLogVo> queryList(ControlLogBo bo) {
        LambdaQueryWrapper<ControlLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ControlLog> buildQueryWrapper(ControlLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ControlLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(ControlLog::getControlId);
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), ControlLog::getDeviceId, bo.getDeviceId());
        lqw.eq(StringUtils.isNotBlank(bo.getUserId()), ControlLog::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getAction()), ControlLog::getAction, bo.getAction());
        lqw.eq(StringUtils.isNotBlank(bo.getResult()), ControlLog::getResult, bo.getResult());
        lqw.eq(bo.getActionTime() != null, ControlLog::getActionTime, bo.getActionTime());
        lqw.eq(StringUtils.isNotBlank(bo.getActionParams()), ControlLog::getActionParams, bo.getActionParams());
        lqw.eq(StringUtils.isNotBlank(bo.getSessionId()), ControlLog::getSessionId, bo.getSessionId());
        lqw.eq(StringUtils.isNotBlank(bo.getGeolocation()), ControlLog::getGeolocation, bo.getGeolocation());
        lqw.eq(StringUtils.isNotBlank(bo.getRiskLevel()), ControlLog::getRiskLevel, bo.getRiskLevel());
        return lqw;
    }

    /**
     * 新增设备控制操作日志
     *
     * @param bo 设备控制操作日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ControlLogBo bo) {
        ControlLog add = MapstructUtils.convert(bo, ControlLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setControlId(add.getControlId());
        }
        return flag;
    }

    /**
     * 修改设备控制操作日志
     *
     * @param bo 设备控制操作日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ControlLogBo bo) {
        ControlLog update = MapstructUtils.convert(bo, ControlLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ControlLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备控制操作日志信息
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
