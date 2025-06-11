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
import org.dromara.system.domain.vo.SecurityRuleVo;
import org.dromara.system.domain.bo.SecurityRuleBo;
import org.dromara.system.service.ISecurityRuleService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 安全检查规则
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/rule")
public class SecurityRuleController extends BaseController {

    private final ISecurityRuleService securityRuleService;

    /**
     * 查询安全检查规则列表
     */
    @SaCheckPermission("security:rule:list")
    @GetMapping("/list")
    public TableDataInfo<SecurityRuleVo> list(SecurityRuleBo bo, PageQuery pageQuery) {
        return securityRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安全检查规则列表
     */
    @SaCheckPermission("security:rule:export")
    @Log(title = "安全检查规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SecurityRuleBo bo, HttpServletResponse response) {
        List<SecurityRuleVo> list = securityRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "安全检查规则", SecurityRuleVo.class, response);
    }

    /**
     * 获取安全检查规则详细信息
     *
     * @param ruleId 主键
     */
    @SaCheckPermission("security:rule:query")
    @GetMapping("/{ruleId}")
    public R<SecurityRuleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String ruleId) {
        return R.ok(securityRuleService.queryById(ruleId));
    }

    /**
     * 新增安全检查规则
     */
    @SaCheckPermission("security:rule:add")
    @Log(title = "安全检查规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SecurityRuleBo bo) {
        return toAjax(securityRuleService.insertByBo(bo));
    }

    /**
     * 修改安全检查规则
     */
    @SaCheckPermission("security:rule:edit")
    @Log(title = "安全检查规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SecurityRuleBo bo) {
        return toAjax(securityRuleService.updateByBo(bo));
    }

    /**
     * 删除安全检查规则
     *
     * @param ruleIds 主键串
     */
    @SaCheckPermission("security:rule:remove")
    @Log(title = "安全检查规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ruleIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ruleIds) {
        return toAjax(securityRuleService.deleteWithValidByIds(List.of(ruleIds), true));
    }
}
