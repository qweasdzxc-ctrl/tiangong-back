package org.dromara.coze.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CozeService {
    private static final String API_BASE_URL = "https://api.coze.cn";
    private static final String WORKFLOW_URL = API_BASE_URL + "/v1/workflow/run";
    private static final String UPLOAD_FILE_URL = API_BASE_URL + "/v1/files/upload";
    private static final String TOKEN = "pat_XZL5bsTwZFsev9jzqMQAkHiCdFwekukurSGsUe4HgkCkDmEQ0zIKAIpIXvNmxjua";

    public String runWorkflowWithFile(MultipartFile file) throws IOException {
        String fileId = uploadFile(file);
        return executeWorkflow(fileId);
    }

    private String uploadFile(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
            UPLOAD_FILE_URL,
            HttpMethod.POST,
            requestEntity,
            Map.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("文件上传失败: " + response.getBody());
        }

        // 解析响应获取文件ID
        Map<String, Object> responseBody = response.getBody();
        try {
            Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
            return (String) data.get("id");
        } catch (Exception e) {
            throw new RuntimeException("解析文件ID失败: " + responseBody, e);
        }
    }

    private String executeWorkflow(String fileId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 构建请求体
        Map<String, Object> input = new HashMap<>();
        input.put("file_id", fileId);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("input", input);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("workflow_id", "7511713150833180711");
        requestBody.put("parameters", parameters);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
            WORKFLOW_URL,
            HttpMethod.POST,
            requestEntity,
            Map.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("工作流执行失败: " + response.getBody());
        }

        return response.getBody().toString();
    }
}
