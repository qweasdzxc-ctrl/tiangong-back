package org.dromara.system.service;

import org.dromara.system.domain.vo.ControlLogVo;
import org.dromara.system.domain.bo.ControlLogBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备控制操作日志Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface IControlLogService {

    /**
     * 查询设备控制操作日志
     *
     * @param controlId 主键
     * @return 设备控制操作日志
     */
    ControlLogVo queryById(String controlId);

    /**
     * 分页查询设备控制操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备控制操作日志分页列表
     */
    TableDataInfo<ControlLogVo> queryPageList(ControlLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备控制操作日志列表
     *
     * @param bo 查询条件
     * @return 设备控制操作日志列表
     */
    List<ControlLogVo> queryList(ControlLogBo bo);

    /**
     * 新增设备控制操作日志
     *
     * @param bo 设备控制操作日志
     * @return 是否新增成功
     */
    Boolean insertByBo(ControlLogBo bo);

    /**
     * 修改设备控制操作日志
     *
     * @param bo 设备控制操作日志
     * @return 是否修改成功
     */
    Boolean updateByBo(ControlLogBo bo);

    /**
     * 校验并批量删除设备控制操作日志信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
