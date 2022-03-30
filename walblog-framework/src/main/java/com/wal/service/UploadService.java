package com.wal.service;

import com.wal.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fwt
 * @version 1.0
 * @date 2022/3/30 19:14
 */

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
