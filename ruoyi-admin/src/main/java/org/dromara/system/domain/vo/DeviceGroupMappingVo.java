package org.dromara.system.domain.vo;

import org.dromara.system.domain.DeviceGroupMapping;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 设备与分组关联视图对象 device_group_mapping
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DeviceGroupMapping.class)
public class DeviceGroupMappingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 映射ID
     */
    @ExcelProperty(value = "映射ID")
    private String mappingId;

    /**
     * 设备ID
     */
    @ExcelProperty(value = "设备ID")
    private String deviceId;

    /**
     * 分组ID
     */
    @ExcelProperty(value = "分组ID")
    private String groupId;


}
