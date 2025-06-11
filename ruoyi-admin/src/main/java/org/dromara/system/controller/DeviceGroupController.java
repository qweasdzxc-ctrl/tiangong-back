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
import org.dromara.system.domain.vo.DeviceGroupVo;
import org.dromara.system.domain.bo.DeviceGroupBo;
import org.dromara.system.service.IDeviceGroupService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备分组
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/group")
public class DeviceGroupController extends BaseController {

    private final IDeviceGroupService deviceGroupService;

    /**
     * 查询设备分组列表
     */
    @SaCheckPermission("sensor:group:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceGroupVo> list(DeviceGroupBo bo, PageQuery pageQuery) {
        return deviceGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备分组列表
     */
    @SaCheckPermission("sensor:group:export")
    @Log(title = "设备分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceGroupBo bo, HttpServletResponse response) {
        List<DeviceGroupVo> list = deviceGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备分组", DeviceGroupVo.class, response);
    }

    /**
     * 获取设备分组详细信息
     *
     * @param groupId 主键
     */
    @SaCheckPermission("sensor:group:query")
    @GetMapping("/{groupId}")
    public R<DeviceGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String groupId) {
        return R.ok(deviceGroupService.queryById(groupId));
    }

    /**
     * 新增设备分组
     */
    @SaCheckPermission("sensor:group:add")
    @Log(title = "设备分组", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceGroupBo bo) {
        return toAjax(deviceGroupService.insertByBo(bo));
    }

    /**
     * 修改设备分组
     */
    @SaCheckPermission("sensor:group:edit")
    @Log(title = "设备分组", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceGroupBo bo) {
        return toAjax(deviceGroupService.updateByBo(bo));
    }

    /**
     * 删除设备分组
     *
     * @param groupIds 主键串
     */
    @SaCheckPermission("sensor:group:remove")
    @Log(title = "设备分组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] groupIds) {
        return toAjax(deviceGroupService.deleteWithValidByIds(List.of(groupIds), true));
    }
}
