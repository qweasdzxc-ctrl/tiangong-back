package org.dromara.coze.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.coze.service.CozeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@SaIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coze")
public class CozeController {
    private final CozeService cozeService;

    @PostMapping("/run")
    public ResponseEntity<String> runWorkflow(@RequestParam("file") MultipartFile file) {
        try {
            // 确保文件内容被完整读取并转换为字节数组
            byte[] fileBytes = file.getBytes();
            String result = cozeService.runWorkflowWithFile(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            log.error("文件读取失败", e);
            return ResponseEntity.badRequest().body("文件读取失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("执行工作流出错", e);
            return ResponseEntity.internalServerError().body("错误: " + e.getMessage());
        }
    }
}
