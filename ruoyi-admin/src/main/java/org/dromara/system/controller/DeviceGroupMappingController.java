package org.dromara.system.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.system.domain.vo.DeviceGroupMappingVo;
import org.dromara.system.domain.bo.DeviceGroupMappingBo;
import org.dromara.system.service.IDeviceGroupMappingService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备与分组关联
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/groupMapping")
public class DeviceGroupMappingController extends BaseController {

    private final IDeviceGroupMappingService deviceGroupMappingService;

    /**
     * 查询设备与分组关联列表
     */
    @SaCheckPermission("sensor:groupMapping:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceGroupMappingVo> list(DeviceGroupMappingBo bo, PageQuery pageQuery) {
        return deviceGroupMappingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备与分组关联列表
     */
    @SaCheckPermission("sensor:groupMapping:export")
    @Log(title = "设备与分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceGroupMappingBo bo, HttpServletResponse response) {
        List<DeviceGroupMappingVo> list = deviceGroupMappingService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备与分组关联", DeviceGroupMappingVo.class, response);
    }

    /**
     * 获取设备与分组关联详细信息
     *
     * @param mappingId 主键
     */
    @SaCheckPermission("sensor:groupMapping:query")
    @GetMapping("/{mappingId}")
    public R<DeviceGroupMappingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String mappingId) {
        return R.ok(deviceGroupMappingService.queryById(mappingId));
    }

    /**
     * 新增设备与分组关联
     */
    @SaCheckPermission("sensor:groupMapping:add")
    @Log(title = "设备与分组关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceGroupMappingBo bo) {
        return toAjax(deviceGroupMappingService.insertByBo(bo));
    }

    /**
     * 修改设备与分组关联
     */
    @SaCheckPermission("sensor:groupMapping:edit")
    @Log(title = "设备与分组关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceGroupMappingBo bo) {
        return toAjax(deviceGroupMappingService.updateByBo(bo));
    }

    /**
     * 删除设备与分组关联
     *
     * @param mappingIds 主键串
     */
    @SaCheckPermission("sensor:groupMapping:remove")
    @Log(title = "设备与分组关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{mappingIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] mappingIds) {
        return toAjax(deviceGroupMappingService.deleteWithValidByIds(List.of(mappingIds), true));
    }
}
