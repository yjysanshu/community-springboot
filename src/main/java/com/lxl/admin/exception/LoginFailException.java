package com.lxl.admin.exception;

import com.lxl.common.consts.ErrorConst;

public class LoginFailException extends Exception {
    private Integer code;

    public LoginFailException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginFailException(String msg) {
        super(msg);
    }

    public LoginFailException(Integer code) {
        super(ErrorConst.messageMap.get(code));
    }
}
