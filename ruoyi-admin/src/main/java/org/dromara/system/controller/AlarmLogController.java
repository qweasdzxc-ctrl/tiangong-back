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
import org.dromara.system.domain.vo.AlarmLogVo;
import org.dromara.system.domain.bo.AlarmLogBo;
import org.dromara.system.service.IAlarmLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 安全警报日志记录
 *
 * @author Lion Li
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/security/log")
public class AlarmLogController extends BaseController {

    private final IAlarmLogService alarmLogService;

    /**@TableField(exist = false)
private String tenantId;

     * 查询安全警报日志记录列表
     */
    @SaCheckPermission("security:log:list")
    @GetMapping("/list")
    public TableDataInfo<AlarmLogVo> list(AlarmLogBo bo, PageQuery pageQuery) {
        return alarmLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安全警报日志记录列表
     */
    @SaCheckPermission("security:log:export")
    @Log(title = "安全警报日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlarmLogBo bo, HttpServletResponse response) {
        List<AlarmLogVo> list = alarmLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "安全警报日志记录", AlarmLogVo.class, response);
    }

    /**
     * 获取安全警报日志记录详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("security:log:query")
    @GetMapping("/{logId}")
    public R<AlarmLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String logId) {
        return R.ok(alarmLogService.queryById(logId));
    }

    /**
     * 新增安全警报日志记录
     */
    @SaCheckPermission("security:log:add")
    @Log(title = "安全警报日志记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlarmLogBo bo) {
        return toAjax(alarmLogService.insertByBo(bo));
    }

    /**
     * 修改安全警报日志记录
     */
    @SaCheckPermission("security:log:edit")
    @Log(title = "安全警报日志记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlarmLogBo bo) {
        return toAjax(alarmLogService.updateByBo(bo));
    }

    /**
     * 删除安全警报日志记录
     *
     * @param logIds 主键串
     */
    @SaCheckPermission("security:log:remove")
    @Log(title = "安全警报日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] logIds) {
        return toAjax(alarmLogService.deleteWithValidByIds(List.of(logIds), true));
    }
}
