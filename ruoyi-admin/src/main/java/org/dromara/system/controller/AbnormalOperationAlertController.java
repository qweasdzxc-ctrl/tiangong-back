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
import org.dromara.system.domain.vo.AbnormalOperationAlertVo;
import org.dromara.system.domain.bo.AbnormalOperationAlertBo;
import org.dromara.system.service.IAbnormalOperationAlertService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 异常操作告警
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/operationAlert")
public class AbnormalOperationAlertController extends BaseController {

    private final IAbnormalOperationAlertService abnormalOperationAlertService;

    /**
     * 查询异常操作告警列表
     */
    @SaCheckPermission("security:operationAlert:list")
    @GetMapping("/list")
    public TableDataInfo<AbnormalOperationAlertVo> list(AbnormalOperationAlertBo bo, PageQuery pageQuery) {
        return abnormalOperationAlertService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出异常操作告警列表
     */
    @SaCheckPermission("security:operationAlert:export")
    @Log(title = "异常操作告警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AbnormalOperationAlertBo bo, HttpServletResponse response) {
        List<AbnormalOperationAlertVo> list = abnormalOperationAlertService.queryList(bo);
        ExcelUtil.exportExcel(list, "异常操作告警", AbnormalOperationAlertVo.class, response);
    }

    /**
     * 获取异常操作告警详细信息
     *
     * @param alertId 主键
     */
    @SaCheckPermission("security:operationAlert:query")
    @GetMapping("/{alertId}")
    public R<AbnormalOperationAlertVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String alertId) {
        return R.ok(abnormalOperationAlertService.queryById(alertId));
    }

    /**
     * 新增异常操作告警
     */
    @SaCheckPermission("security:operationAlert:add")
    @Log(title = "异常操作告警", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AbnormalOperationAlertBo bo) {
        return toAjax(abnormalOperationAlertService.insertByBo(bo));
    }

    /**
     * 修改异常操作告警
     */
    @SaCheckPermission("security:operationAlert:edit")
    @Log(title = "异常操作告警", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AbnormalOperationAlertBo bo) {
        return toAjax(abnormalOperationAlertService.updateByBo(bo));
    }

    /**
     * 删除异常操作告警
     *
     * @param alertIds 主键串
     */
    @SaCheckPermission("security:operationAlert:remove")
    @Log(title = "异常操作告警", businessType = BusinessType.DELETE)
    @DeleteMapping("/{alertIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] alertIds) {
        return toAjax(abnormalOperationAlertService.deleteWithValidByIds(List.of(alertIds), true));
    }
}
