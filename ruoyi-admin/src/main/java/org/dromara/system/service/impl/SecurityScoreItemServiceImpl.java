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
import org.dromara.system.domain.bo.SecurityScoreItemBo;
import org.dromara.system.domain.vo.SecurityScoreItemVo;
import org.dromara.system.domain.SecurityScoreItem;
import org.dromara.system.mapper.SecurityScoreItemMapper;
import org.dromara.system.service.ISecurityScoreItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 安全评分项定义Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SecurityScoreItemServiceImpl implements ISecurityScoreItemService {

    private final SecurityScoreItemMapper baseMapper;

    /**
     * 查询安全评分项定义
     *
     * @param itemId 主键
     * @return 安全评分项定义
     */
    @Override
    public SecurityScoreItemVo queryById(String itemId){
        return baseMapper.selectVoById(itemId);
    }

    /**
     * 分页查询安全评分项定义列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 安全评分项定义分页列表
     */
    @Override
    public TableDataInfo<SecurityScoreItemVo> queryPageList(SecurityScoreItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SecurityScoreItem> lqw = buildQueryWrapper(bo);
        Page<SecurityScoreItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的安全评分项定义列表
     *
     * @param bo 查询条件
     * @return 安全评分项定义列表
     */
    @Override
    public List<SecurityScoreItemVo> queryList(SecurityScoreItemBo bo) {
        LambdaQueryWrapper<SecurityScoreItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SecurityScoreItem> buildQueryWrapper(SecurityScoreItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SecurityScoreItem> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SecurityScoreItem::getItemId);
        lqw.like(StringUtils.isNotBlank(bo.getItemName()), SecurityScoreItem::getItemName, bo.getItemName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SecurityScoreItem::getDescription, bo.getDescription());
        lqw.eq(bo.getWeight() != null, SecurityScoreItem::getWeight, bo.getWeight());
        lqw.eq(bo.getIsActive() != null, SecurityScoreItem::getIsActive, bo.getIsActive());
        return lqw;
    }

    /**
     * 新增安全评分项定义
     *
     * @param bo 安全评分项定义
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SecurityScoreItemBo bo) {
        SecurityScoreItem add = MapstructUtils.convert(bo, SecurityScoreItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setItemId(add.getItemId());
        }
        return flag;
    }

    /**
     * 修改安全评分项定义
     *
     * @param bo 安全评分项定义
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SecurityScoreItemBo bo) {
        SecurityScoreItem update = MapstructUtils.convert(bo, SecurityScoreItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SecurityScoreItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除安全评分项定义信息
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
