package com.lxl.upload.services;

import com.lxl.common.consts.CharacterConst;
import com.lxl.common.consts.CommonConst;
import com.lxl.common.util.CodeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    /**
     * 文件上传
     * @param file 前端上传的文件
     * @return true-上传成功
     */
    public String upload(MultipartFile file) {
        String uploadDir = CommonConst.FILE_BASE_DIR;
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String originFilename = file.getOriginalFilename();
        String[] fileNameArr = originFilename.split(CharacterConst.CHARACTER_MATCH_SPOT);
        String extType = fileNameArr[fileNameArr.length - 1];
        String aimFileName = CodeUtil.createUUID() + CharacterConst.CHARACTER_SPOT + extType;
        File serverFile = new File(uploadDir + aimFileName);
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            return CharacterConst.CHARACTER_NULL;
        }
        return CommonConst.FILE_BASE_HOST + aimFileName;
    }
}
