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
import org.dromara.system.domain.vo.PermissionPolicyVo;
import org.dromara.system.domain.bo.PermissionPolicyBo;
import org.dromara.system.service.IPermissionPolicyService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备权限策略
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor/policy")
public class PermissionPolicyController extends BaseController {

    private final IPermissionPolicyService permissionPolicyService;

    /**
     * 查询设备权限策略列表
     */
    @SaCheckPermission("sensor:policy:list")
    @GetMapping("/list")
    public TableDataInfo<PermissionPolicyVo> list(PermissionPolicyBo bo, PageQuery pageQuery) {
        return permissionPolicyService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备权限策略列表
     */
    @SaCheckPermission("sensor:policy:export")
    @Log(title = "设备权限策略", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PermissionPolicyBo bo, HttpServletResponse response) {
        List<PermissionPolicyVo> list = permissionPolicyService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备权限策略", PermissionPolicyVo.class, response);
    }

    /**
     * 获取设备权限策略详细信息
     *
     * @param policyId 主键
     */
    @SaCheckPermission("sensor:policy:query")
    @GetMapping("/{policyId}")
    public R<PermissionPolicyVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String policyId) {
        return R.ok(permissionPolicyService.queryById(policyId));
    }

    /**
     * 新增设备权限策略
     */
    @SaCheckPermission("sensor:policy:add")
    @Log(title = "设备权限策略", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PermissionPolicyBo bo) {
        return toAjax(permissionPolicyService.insertByBo(bo));
    }

    /**
     * 修改设备权限策略
     */
    @SaCheckPermission("sensor:policy:edit")
    @Log(title = "设备权限策略", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PermissionPolicyBo bo) {
        return toAjax(permissionPolicyService.updateByBo(bo));
    }

    /**
     * 删除设备权限策略
     *
     * @param policyIds 主键串
     */
    @SaCheckPermission("sensor:policy:remove")
    @Log(title = "设备权限策略", businessType = BusinessType.DELETE)
    @DeleteMapping("/{policyIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] policyIds) {
        return toAjax(permissionPolicyService.deleteWithValidByIds(List.of(policyIds), true));
    }
}
