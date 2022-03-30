package com.wal.controller;

import com.wal.domain.ResponseResult;
import com.wal.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author fwt
 * @version 1.0
 * @date 2022/3/30 19:13
 */
@RestController
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
