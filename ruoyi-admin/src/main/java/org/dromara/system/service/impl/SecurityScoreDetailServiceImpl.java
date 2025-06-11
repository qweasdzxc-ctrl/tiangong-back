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
import org.dromara.system.domain.bo.SecurityScoreDetailBo;
import org.dromara.system.domain.vo.SecurityScoreDetailVo;
import org.dromara.system.domain.SecurityScoreDetail;
import org.dromara.system.mapper.SecurityScoreDetailMapper;
import org.dromara.system.service.ISecurityScoreDetailService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安全评分明细Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SecurityScoreDetailServiceImpl implements ISecurityScoreDetailService {

    private final SecurityScoreDetailMapper baseMapper;

    /**
     * 查询安全评分明细
     *
     * @param detailId 主键
     * @return 安全评分明细
     */
    @Override
    public SecurityScoreDetailVo queryById(String detailId){
        return baseMapper.selectVoById(detailId);
    }

    /**
     * 分页查询安全评分明细列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全评分明细分页列表
     */
    @Override
    public TableDataInfo<SecurityScoreDetailVo> queryPageList(SecurityScoreDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SecurityScoreDetail> lqw = buildQueryWrapper(bo);
        Page<SecurityScoreDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的安全评分明细列表
     *
     * @param bo 查询条件
     * @return 安全评分明细列表
     */
    @Override
    public List<SecurityScoreDetailVo> queryList(SecurityScoreDetailBo bo) {
        LambdaQueryWrapper<SecurityScoreDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SecurityScoreDetail> buildQueryWrapper(SecurityScoreDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SecurityScoreDetail> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SecurityScoreDetail::getDetailId);
        lqw.eq(StringUtils.isNotBlank(bo.getRatingId()), SecurityScoreDetail::getRatingId, bo.getRatingId());
        lqw.eq(StringUtils.isNotBlank(bo.getItemId()), SecurityScoreDetail::getItemId, bo.getItemId());
        lqw.eq(bo.getScore() != null, SecurityScoreDetail::getScore, bo.getScore());
        lqw.eq(StringUtils.isNotBlank(bo.getReason()), SecurityScoreDetail::getReason, bo.getReason());
        return lqw;
    }

    /**
     * 新增安全评分明细
     *
     * @param bo 安全评分明细
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SecurityScoreDetailBo bo) {
        SecurityScoreDetail add = MapstructUtils.convert(bo, SecurityScoreDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDetailId(add.getDetailId());
        }
        return flag;
    }

    /**
     * 修改安全评分明细
     *
     * @param bo 安全评分明细
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SecurityScoreDetailBo bo) {
        SecurityScoreDetail update = MapstructUtils.convert(bo, SecurityScoreDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SecurityScoreDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除安全评分明细信息
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
