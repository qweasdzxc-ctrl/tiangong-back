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
import org.dromara.system.domain.vo.SecurityScoreVo;
import org.dromara.system.domain.bo.SecurityScoreBo;
import org.dromara.system.service.ISecurityScoreService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 设备安全评分
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/score")
public class SecurityScoreController extends BaseController {

    private final ISecurityScoreService securityScoreService;

    /**
     * 查询设备安全评分列表
     */
    @SaCheckPermission("security:score:list")
    @GetMapping("/list")
    public TableDataInfo<SecurityScoreVo> list(SecurityScoreBo bo, PageQuery pageQuery) {
        return securityScoreService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备安全评分列表
     */
    @SaCheckPermission("security:score:export")
    @Log(title = "设备安全评分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SecurityScoreBo bo, HttpServletResponse response) {
        List<SecurityScoreVo> list = securityScoreService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备安全评分", SecurityScoreVo.class, response);
    }

    /**
     * 获取设备安全评分详细信息
     *
     * @param ratingId 主键
     */
    @SaCheckPermission("security:score:query")
    @GetMapping("/{ratingId}")
    public R<SecurityScoreVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String ratingId) {
        return R.ok(securityScoreService.queryById(ratingId));
    }

    /**
     * 新增设备安全评分
     */
    @SaCheckPermission("security:score:add")
    @Log(title = "设备安全评分", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SecurityScoreBo bo) {
        return toAjax(securityScoreService.insertByBo(bo));
    }

    /**
     * 修改设备安全评分
     */
    @SaCheckPermission("security:score:edit")
    @Log(title = "设备安全评分", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SecurityScoreBo bo) {
        return toAjax(securityScoreService.updateByBo(bo));
    }

    /**
     * 删除设备安全评分
     *
     * @param ratingIds 主键串
     */
    @SaCheckPermission("security:score:remove")
    @Log(title = "设备安全评分", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ratingIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ratingIds) {
        return toAjax(securityScoreService.deleteWithValidByIds(List.of(ratingIds), true));
    }
}
