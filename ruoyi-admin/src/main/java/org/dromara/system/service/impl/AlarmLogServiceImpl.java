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
import org.dromara.system.domain.bo.AlarmLogBo;
import org.dromara.system.domain.vo.AlarmLogVo;
import org.dromara.system.domain.AlarmLog;
import org.dromara.system.mapper.AlarmLogMapper;
import org.dromara.system.service.IAlarmLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安全警报日志记录Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class AlarmLogServiceImpl implements IAlarmLogService {

    private final AlarmLogMapper baseMapper;

    /**
     * 查询安全警报日志记录
     *
     * @param logId 主键
     * @return 安全警报日志记录
     */
    @Override
    public AlarmLogVo queryById(String logId){
        return baseMapper.selectVoById(logId);
    }

    /**
     * 分页查询安全警报日志记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全警报日志记录分页列表
     */
    @Override
    public TableDataInfo<AlarmLogVo> queryPageList(AlarmLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlarmLog> lqw = buildQueryWrapper(bo);
        Page<AlarmLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的安全警报日志记录列表
     *
     * @param bo 查询条件
     * @return 安全警报日志记录列表
     */
    @Override
    public List<AlarmLogVo> queryList(AlarmLogBo bo) {
        LambdaQueryWrapper<AlarmLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlarmLog> buildQueryWrapper(AlarmLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlarmLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(AlarmLog::getLogId);
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), AlarmLog::getDeviceId, bo.getDeviceId());
        lqw.eq(StringUtils.isNotBlank(bo.getRuleId()), AlarmLog::getRuleId, bo.getRuleId());
        lqw.eq(StringUtils.isNotBlank(bo.getAlarmType()), AlarmLog::getAlarmType, bo.getAlarmType());
        lqw.eq(StringUtils.isNotBlank(bo.getMessage()), AlarmLog::getMessage, bo.getMessage());
        lqw.eq(StringUtils.isNotBlank(bo.getSeverity()), AlarmLog::getSeverity, bo.getSeverity());
        lqw.eq(bo.getHandled() != null, AlarmLog::getHandled, bo.getHandled());
        lqw.eq(StringUtils.isNotBlank(bo.getHandledBy()), AlarmLog::getHandledBy, bo.getHandledBy());
        lqw.eq(bo.getHandledTime() != null, AlarmLog::getHandledTime, bo.getHandledTime());
        lqw.eq(StringUtils.isNotBlank(bo.getHandlingNotes()), AlarmLog::getHandlingNotes, bo.getHandlingNotes());
        lqw.eq(bo.getLogTime() != null, AlarmLog::getLogTime, bo.getLogTime());
        return lqw;
    }

    /**
     * 新增安全警报日志记录
     *
     * @param bo 安全警报日志记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AlarmLogBo bo) {
        AlarmLog add = MapstructUtils.convert(bo, AlarmLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLogId(add.getLogId());
        }
        return flag;
    }

    /**
     * 修改安全警报日志记录
     *
     * @param bo 安全警报日志记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AlarmLogBo bo) {
        AlarmLog update = MapstructUtils.convert(bo, AlarmLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlarmLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除安全警报日志记录信息
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
