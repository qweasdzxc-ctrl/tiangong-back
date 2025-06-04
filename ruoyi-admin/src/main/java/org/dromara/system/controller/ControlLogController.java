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
import org.dromara.system.domain.vo.ControlLogVo;
import org.dromara.system.domain.bo.ControlLogBo;
import org.dromara.system.service.IControlLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备控制操作日志
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/log")
public class ControlLogController extends BaseController {

    private final IControlLogService controlLogService;

    /**
     * 查询设备控制操作日志列表
     */
    @SaCheckPermission("sensor:log:list")
    @GetMapping("/list")
    public TableDataInfo<ControlLogVo> list(ControlLogBo bo, PageQuery pageQuery) {
        return controlLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备控制操作日志列表
     */
    @SaCheckPermission("sensor:log:export")
    @Log(title = "设备控制操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ControlLogBo bo, HttpServletResponse response) {
        List<ControlLogVo> list = controlLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备控制操作日志", ControlLogVo.class, response);
    }

    /**
     * 获取设备控制操作日志详细信息
     *
     * @param controlId 主键
     */
    @SaCheckPermission("sensor:log:query")
    @GetMapping("/{controlId}")
    public R<ControlLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String controlId) {
        return R.ok(controlLogService.queryById(controlId));
    }

    /**
     * 新增设备控制操作日志
     */
    @SaCheckPermission("sensor:log:add")
    @Log(title = "设备控制操作日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ControlLogBo bo) {
        return toAjax(controlLogService.insertByBo(bo));
    }

    /**
     * 修改设备控制操作日志
     */
    @SaCheckPermission("sensor:log:edit")
    @Log(title = "设备控制操作日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ControlLogBo bo) {
        return toAjax(controlLogService.updateByBo(bo));
    }

    /**
     * 删除设备控制操作日志
     *
     * @param controlIds 主键串
     */
    @SaCheckPermission("sensor:log:remove")
    @Log(title = "设备控制操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{controlIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] controlIds) {
        return toAjax(controlLogService.deleteWithValidByIds(List.of(controlIds), true));
    }
}
