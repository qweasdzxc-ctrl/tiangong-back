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
import org.dromara.system.domain.vo.SecurityScoreDetailVo;
import org.dromara.system.domain.bo.SecurityScoreDetailBo;
import org.dromara.system.service.ISecurityScoreDetailService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 安全评分明细
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/scoreDetail")
public class SecurityScoreDetailController extends BaseController {

    private final ISecurityScoreDetailService securityScoreDetailService;

    /**
     * 查询安全评分明细列表
     */
    @SaCheckPermission("security:scoreDetail:list")
    @GetMapping("/list")
    public TableDataInfo<SecurityScoreDetailVo> list(SecurityScoreDetailBo bo, PageQuery pageQuery) {
        return securityScoreDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安全评分明细列表
     */
    @SaCheckPermission("security:scoreDetail:export")
    @Log(title = "安全评分明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SecurityScoreDetailBo bo, HttpServletResponse response) {
        List<SecurityScoreDetailVo> list = securityScoreDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "安全评分明细", SecurityScoreDetailVo.class, response);
    }

    /**
     * 获取安全评分明细详细信息
     *
     * @param detailId 主键
     */
    @SaCheckPermission("security:scoreDetail:query")
    @GetMapping("/{detailId}")
    public R<SecurityScoreDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String detailId) {
        return R.ok(securityScoreDetailService.queryById(detailId));
    }

    /**
     * 新增安全评分明细
     */
    @SaCheckPermission("security:scoreDetail:add")
    @Log(title = "安全评分明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SecurityScoreDetailBo bo) {
        return toAjax(securityScoreDetailService.insertByBo(bo));
    }

    /**
     * 修改安全评分明细
     */
    @SaCheckPermission("security:scoreDetail:edit")
    @Log(title = "安全评分明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SecurityScoreDetailBo bo) {
        return toAjax(securityScoreDetailService.updateByBo(bo));
    }

    /**
     * 删除安全评分明细
     *
     * @param detailIds 主键串
     */
    @SaCheckPermission("security:scoreDetail:remove")
    @Log(title = "安全评分明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] detailIds) {
        return toAjax(securityScoreDetailService.deleteWithValidByIds(List.of(detailIds), true));
    }
}
