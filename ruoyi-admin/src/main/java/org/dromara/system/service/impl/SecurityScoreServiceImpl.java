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
import org.dromara.system.domain.bo.SecurityScoreBo;
import org.dromara.system.domain.vo.SecurityScoreVo;
import org.dromara.system.domain.SecurityScore;
import org.dromara.system.mapper.SecurityScoreMapper;
import org.dromara.system.service.ISecurityScoreService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备安全评分Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SecurityScoreServiceImpl implements ISecurityScoreService {

    private final SecurityScoreMapper baseMapper;

    /**
     * 查询设备安全评分
     *
     * @param ratingId 主键
     * @return 设备安全评分
     */
    @Override
    public SecurityScoreVo queryById(String ratingId){
        return baseMapper.selectVoById(ratingId);
    }

    /**
     * 分页查询设备安全评分列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备安全评分分页列表
     */
    @Override
    public TableDataInfo<SecurityScoreVo> queryPageList(SecurityScoreBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SecurityScore> lqw = buildQueryWrapper(bo);
        Page<SecurityScoreVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备安全评分列表
     *
     * @param bo 查询条件
     * @return 设备安全评分列表
     */
    @Override
    public List<SecurityScoreVo> queryList(SecurityScoreBo bo) {
        LambdaQueryWrapper<SecurityScore> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SecurityScore> buildQueryWrapper(SecurityScoreBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SecurityScore> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SecurityScore::getRatingId);
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceId()), SecurityScore::getDeviceId, bo.getDeviceId());
        lqw.eq(bo.getTotalScore() != null, SecurityScore::getTotalScore, bo.getTotalScore());
        lqw.eq(bo.getRatingTime() != null, SecurityScore::getRatingTime, bo.getRatingTime());
        return lqw;
    }

    /**
     * 新增设备安全评分
     *
     * @param bo 设备安全评分
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SecurityScoreBo bo) {
        SecurityScore add = MapstructUtils.convert(bo, SecurityScore.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRatingId(add.getRatingId());
        }
        return flag;
    }

    /**
     * 修改设备安全评分
     *
     * @param bo 设备安全评分
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SecurityScoreBo bo) {
        SecurityScore update = MapstructUtils.convert(bo, SecurityScore.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SecurityScore entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备安全评分信息
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
