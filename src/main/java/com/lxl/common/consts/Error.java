package com.lxl.common.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/7.
 */
public class Error {

    public static int NO_EXCEPTION = 0;
    public static int SYSTEM_EXCEPTION = 1;

    //参数异常errorCode
    public static int PARAMS_EXCEPTION = 10000;

    //异常message
    public static Map<Integer, String> messageMap = new HashMap<>();
    static {
        messageMap.put(NO_EXCEPTION, "成功");
        messageMap.put(SYSTEM_EXCEPTION, "系统异常，请稍后重试！");
        messageMap.put(PARAMS_EXCEPTION, "你的请求参数异常！");
    }
}
