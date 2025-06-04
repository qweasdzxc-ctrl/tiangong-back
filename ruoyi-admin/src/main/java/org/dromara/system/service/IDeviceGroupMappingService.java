package org.dromara.system.service;

import org.dromara.system.domain.vo.DeviceGroupMappingVo;
import org.dromara.system.domain.bo.DeviceGroupMappingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备与分组关联Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface IDeviceGroupMappingService {

    /**
     * 查询设备与分组关联
     *
     * @param mappingId 主键
     * @return 设备与分组关联
     */
    DeviceGroupMappingVo queryById(String mappingId);

    /**
     * 分页查询设备与分组关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备与分组关联分页列表
     */
    TableDataInfo<DeviceGroupMappingVo> queryPageList(DeviceGroupMappingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备与分组关联列表
     *
     * @param bo 查询条件
     * @return 设备与分组关联列表
     */
    List<DeviceGroupMappingVo> queryList(DeviceGroupMappingBo bo);

    /**
     * 新增设备与分组关联
     *
     * @param bo 设备与分组关联
     * @return 是否新增成功
     */
    Boolean insertByBo(DeviceGroupMappingBo bo);

    /**
     * 修改设备与分组关联
     *
     * @param bo 设备与分组关联
     * @return 是否修改成功
     */
    Boolean updateByBo(DeviceGroupMappingBo bo);

    /**
     * 校验并批量删除设备与分组关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
