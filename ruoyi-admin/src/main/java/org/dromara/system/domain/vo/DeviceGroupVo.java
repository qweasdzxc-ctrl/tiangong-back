package org.dromara.system.domain.vo;

import org.dromara.system.domain.DeviceGroup;
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
 * 设备分组视图对象 device_group
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = DeviceGroup.class)
public class DeviceGroupVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    @ExcelProperty(value = "分组ID")
    private String groupId;

    /**
     * 所属用户ID
     */
    @ExcelProperty(value = "所属用户ID")
    private String userId;

    /**
     * 分组名称
     */
    @ExcelProperty(value = "分组名称")
    private String groupName;

    /**
     * 分组描述
     */
    @ExcelProperty(value = "分组描述")
    private String description;


}
