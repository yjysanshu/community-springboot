package com.lxl.upload.controllers;

import com.lxl.common.consts.CharacterConst;
import com.lxl.common.util.FormatUtil;
import com.lxl.upload.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload")
    public Map upload(MultipartFile fileContent) {
        String accessUrl = uploadService.upload(fileContent);
        if (!accessUrl.equals(CharacterConst.CHARACTER_NULL)) {
            Map<String, String> map = new HashMap<>();
            map.put("access_url", accessUrl);
            map.put("resource_path", accessUrl);
            map.put("source_url", accessUrl);
            map.put("url", accessUrl);
            map.put("vid", accessUrl);
            return FormatUtil.success(map);
        }
        return FormatUtil.fail();
    }
}
