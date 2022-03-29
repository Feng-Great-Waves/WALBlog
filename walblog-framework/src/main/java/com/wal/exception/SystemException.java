package com.wal.exception;

import com.wal.enums.AppHttpCodeEnum;

/**
 * @author fwt
 * @version 1.0
 * @date 2022/3/22 22:17
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}

