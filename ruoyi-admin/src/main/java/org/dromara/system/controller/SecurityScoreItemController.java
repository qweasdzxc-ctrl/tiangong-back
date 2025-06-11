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
import org.dromara.system.domain.vo.SecurityScoreItemVo;
import org.dromara.system.domain.bo.SecurityScoreItemBo;
import org.dromara.system.service.ISecurityScoreItemService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 安全评分项定义
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/scoreItem")
public class SecurityScoreItemController extends BaseController {

    private final ISecurityScoreItemService securityScoreItemService;

    /**
     * 查询安全评分项定义列表
     */
    @SaCheckPermission("security:scoreItem:list")
    @GetMapping("/list")
    public TableDataInfo<SecurityScoreItemVo> list(SecurityScoreItemBo bo, PageQuery pageQuery) {
        return securityScoreItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安全评分项定义列表
     */
    @SaCheckPermission("security:scoreItem:export")
    @Log(title = "安全评分项定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SecurityScoreItemBo bo, HttpServletResponse response) {
        List<SecurityScoreItemVo> list = securityScoreItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "安全评分项定义", SecurityScoreItemVo.class, response);
    }

    /**
     * 获取安全评分项定义详细信息
     *
     * @param itemId 主键
     */
    @SaCheckPermission("security:scoreItem:query")
    @GetMapping("/{itemId}")
    public R<SecurityScoreItemVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String itemId) {
        return R.ok(securityScoreItemService.queryById(itemId));
    }

    /**
     * 新增安全评分项定义
     */
    @SaCheckPermission("security:scoreItem:add")
    @Log(title = "安全评分项定义", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SecurityScoreItemBo bo) {
        return toAjax(securityScoreItemService.insertByBo(bo));
    }

    /**
     * 修改安全评分项定义
     */
    @SaCheckPermission("security:scoreItem:edit")
    @Log(title = "安全评分项定义", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SecurityScoreItemBo bo) {
        return toAjax(securityScoreItemService.updateByBo(bo));
    }

    /**
     * 删除安全评分项定义
     *
     * @param itemIds 主键串
     */
    @SaCheckPermission("security:scoreItem:remove")
    @Log(title = "安全评分项定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] itemIds) {
        return toAjax(securityScoreItemService.deleteWithValidByIds(List.of(itemIds), true));
    }
}
