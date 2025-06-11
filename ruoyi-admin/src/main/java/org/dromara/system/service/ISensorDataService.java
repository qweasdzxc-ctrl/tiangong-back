package org.dromara.system.service;

import org.dromara.system.domain.vo.SensorDataVo;
import org.dromara.system.domain.bo.SensorDataBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备传感器数据记录Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface ISensorDataService {

    /**
     * 查询设备传感器数据记录
     *
     * @param sensorId 主键
     * @return 设备传感器数据记录
     */
    SensorDataVo queryById(String sensorId);

    /**
     * 分页查询设备传感器数据记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备传感器数据记录分页列表
     */
    TableDataInfo<SensorDataVo> queryPageList(SensorDataBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备传感器数据记录列表
     *
     * @param bo 查询条件
     * @return 设备传感器数据记录列表
     */
    List<SensorDataVo> queryList(SensorDataBo bo);

    /**
     * 新增设备传感器数据记录
     *
     * @param bo 设备传感器数据记录
     * @return 是否新增成功
     */
    Boolean insertByBo(SensorDataBo bo);

    /**
     * 修改设备传感器数据记录
     *
     * @param bo 设备传感器数据记录
     * @return 是否修改成功
     */
    Boolean updateByBo(SensorDataBo bo);

    /**
     * 校验并批量删除设备传感器数据记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
