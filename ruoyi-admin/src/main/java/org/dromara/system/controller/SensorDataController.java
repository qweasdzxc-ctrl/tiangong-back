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
import org.dromara.system.domain.vo.SensorDataVo;
import org.dromara.system.domain.bo.SensorDataBo;
import org.dromara.system.service.ISensorDataService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备传感器数据记录
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/data")
public class SensorDataController extends BaseController {

    private final ISensorDataService sensorDataService;

    /**
     * 查询设备传感器数据记录列表
     */
    @SaCheckPermission("sensor:data:list")
    @GetMapping("/list")
    public TableDataInfo<SensorDataVo> list(SensorDataBo bo, PageQuery pageQuery) {
        return sensorDataService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备传感器数据记录列表
     */
    @SaCheckPermission("sensor:data:export")
    @Log(title = "设备传感器数据记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SensorDataBo bo, HttpServletResponse response) {
        List<SensorDataVo> list = sensorDataService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备传感器数据记录", SensorDataVo.class, response);
    }

    /**
     * 获取设备传感器数据记录详细信息
     *
     * @param sensorId 主键
     */
    @SaCheckPermission("sensor:data:query")
    @GetMapping("/{sensorId}")
    public R<SensorDataVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String sensorId) {
        return R.ok(sensorDataService.queryById(sensorId));
    }

    /**
     * 新增设备传感器数据记录
     */
    @SaCheckPermission("sensor:data:add")
    @Log(title = "设备传感器数据记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SensorDataBo bo) {
        return toAjax(sensorDataService.insertByBo(bo));
    }

    /**
     * 修改设备传感器数据记录
     */
    @SaCheckPermission("sensor:data:edit")
    @Log(title = "设备传感器数据记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SensorDataBo bo) {
        return toAjax(sensorDataService.updateByBo(bo));
    }

    /**
     * 删除设备传感器数据记录
     *
     * @param sensorIds 主键串
     */
    @SaCheckPermission("sensor:data:remove")
    @Log(title = "设备传感器数据记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sensorIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] sensorIds) {
        return toAjax(sensorDataService.deleteWithValidByIds(List.of(sensorIds), true));
    }
}
