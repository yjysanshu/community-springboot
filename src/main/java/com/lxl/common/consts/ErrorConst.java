package com.lxl.common.consts;

import java.util.HashMap;
import java.util.Map;

public class ErrorConst {

    public static int SYSTEM_EXCEPTION = 1;
    public static int PARAMS_EXCEPTION = 10000;

    public static Map<Integer, String> message = new HashMap<>();

    static {
        message.put(SYSTEM_EXCEPTION, "系统异常，请稍后重试！");
        message.put(PARAMS_EXCEPTION, "您的请求有误！");
    }
}
