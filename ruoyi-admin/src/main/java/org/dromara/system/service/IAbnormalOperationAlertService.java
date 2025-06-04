package org.dromara.system.service;

import org.dromara.system.domain.vo.AbnormalOperationAlertVo;
import org.dromara.system.domain.bo.AbnormalOperationAlertBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 异常操作告警Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface IAbnormalOperationAlertService {

    /**
     * 查询异常操作告警
     *
     * @param alertId 主键
     * @return 异常操作告警
     */
    AbnormalOperationAlertVo queryById(String alertId);

    /**
     * 分页查询异常操作告警列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 异常操作告警分页列表
     */
    TableDataInfo<AbnormalOperationAlertVo> queryPageList(AbnormalOperationAlertBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的异常操作告警列表
     *
     * @param bo 查询条件
     * @return 异常操作告警列表
     */
    List<AbnormalOperationAlertVo> queryList(AbnormalOperationAlertBo bo);

    /**
     * 新增异常操作告警
     *
     * @param bo 异常操作告警
     * @return 是否新增成功
     */
    Boolean insertByBo(AbnormalOperationAlertBo bo);

    /**
     * 修改异常操作告警
     *
     * @param bo 异常操作告警
     * @return 是否修改成功
     */
    Boolean updateByBo(AbnormalOperationAlertBo bo);

    /**
     * 校验并批量删除异常操作告警信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
