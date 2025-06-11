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
import org.dromara.system.domain.vo.DeviceVo;
import org.dromara.system.domain.bo.DeviceBo;
import org.dromara.system.service.IDeviceService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 智能家居设备信息
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/device")
public class DeviceController extends BaseController {

    private final IDeviceService deviceService;

    /**
     * 查询智能家居设备信息列表
     */
    @SaCheckPermission("sensor:device:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceVo> list(DeviceBo bo, PageQuery pageQuery) {
        return deviceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出智能家居设备信息列表
     */
    @SaCheckPermission("sensor:device:export")
    @Log(title = "智能家居设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceBo bo, HttpServletResponse response) {
        List<DeviceVo> list = deviceService.queryList(bo);
        ExcelUtil.exportExcel(list, "智能家居设备信息", DeviceVo.class, response);
    }

    /**
     * 获取智能家居设备信息详细信息
     *
     * @param deviceId 主键
     */
    @SaCheckPermission("sensor:device:query")
    @GetMapping("/{deviceId}")
    public R<DeviceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String deviceId) {
        return R.ok(deviceService.queryById(deviceId));
    }

    /**
     * 新增智能家居设备信息
     */
    @SaCheckPermission("sensor:device:add")
    @Log(title = "智能家居设备信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceBo bo) {
        return toAjax(deviceService.insertByBo(bo));
    }

    /**
     * 修改智能家居设备信息
     */
    @SaCheckPermission("sensor:device:edit")
    @Log(title = "智能家居设备信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceBo bo) {
        return toAjax(deviceService.updateByBo(bo));
    }

    /**
     * 删除智能家居设备信息
     *
     * @param deviceIds 主键串
     */
    @SaCheckPermission("sensor:device:remove")
    @Log(title = "智能家居设备信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deviceIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] deviceIds) {
        return toAjax(deviceService.deleteWithValidByIds(List.of(deviceIds), true));
    }
}
