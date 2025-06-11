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
import org.dromara.system.domain.bo.AbnormalOperationAlertBo;
import org.dromara.system.domain.vo.AbnormalOperationAlertVo;
import org.dromara.system.domain.AbnormalOperationAlert;
import org.dromara.system.mapper.AbnormalOperationAlertMapper;
import org.dromara.system.service.IAbnormalOperationAlertService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 异常操作告警Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class AbnormalOperationAlertServiceImpl implements IAbnormalOperationAlertService {

    private final AbnormalOperationAlertMapper baseMapper;

    /**
     * 查询异常操作告警
     *
     * @param alertId 主键
     * @return 异常操作告警
     */
    @Override
    public AbnormalOperationAlertVo queryById(String alertId){
        return baseMapper.selectVoById(alertId);
    }

    /**
     * 分页查询异常操作告警列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 异常操作告警分页列表
     */
    @Override
    public TableDataInfo<AbnormalOperationAlertVo> queryPageList(AbnormalOperationAlertBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AbnormalOperationAlert> lqw = buildQueryWrapper(bo);
        Page<AbnormalOperationAlertVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的异常操作告警列表
     *
     * @param bo 查询条件
     * @return 异常操作告警列表
     */
    @Override
    public List<AbnormalOperationAlertVo> queryList(AbnormalOperationAlertBo bo) {
        LambdaQueryWrapper<AbnormalOperationAlert> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AbnormalOperationAlert> buildQueryWrapper(AbnormalOperationAlertBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AbnormalOperationAlert> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(AbnormalOperationAlert::getAlertId);
        lqw.eq(StringUtils.isNotBlank(bo.getSessionId()), AbnormalOperationAlert::getSessionId, bo.getSessionId());
        lqw.eq(StringUtils.isNotBlank(bo.getUserId()), AbnormalOperationAlert::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), AbnormalOperationAlert::getDeviceId, bo.getDeviceId());
        lqw.eq(StringUtils.isNotBlank(bo.getAlertType()), AbnormalOperationAlert::getAlertType, bo.getAlertType());
        lqw.eq(bo.getAlertTime() != null, AbnormalOperationAlert::getAlertTime, bo.getAlertTime());
        lqw.eq(StringUtils.isNotBlank(bo.getHandlingStatus()), AbnormalOperationAlert::getHandlingStatus, bo.getHandlingStatus());
        return lqw;
    }

    /**
     * 新增异常操作告警
     *
     * @param bo 异常操作告警
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AbnormalOperationAlertBo bo) {
        AbnormalOperationAlert add = MapstructUtils.convert(bo, AbnormalOperationAlert.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAlertId(add.getAlertId());
        }
        return flag;
    }

    /**
     * 修改异常操作告警
     *
     * @param bo 异常操作告警
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AbnormalOperationAlertBo bo) {
        AbnormalOperationAlert update = MapstructUtils.convert(bo, AbnormalOperationAlert.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AbnormalOperationAlert entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除异常操作告警信息
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
