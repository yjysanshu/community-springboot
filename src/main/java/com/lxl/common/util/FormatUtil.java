package com.lxl.common.util;

import com.lxl.common.consts.Error;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/7.
 */
public class FormatUtil {

    public static Map success(Map data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", data);
        return map;
    }

    public static Map fail(Map data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("message", "系统异常，请稍后重试！");
        map.put("data", data);
        return map;
    }

    public static Map fail(int errorCode, Map data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", errorCode);
        map.put("message", Error.messageMap.get(errorCode));
        map.put("data", data);
        return map;
    }

    public static Map fail(int errorCode, String message, Map data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", errorCode);
        map.put("message", message);
        map.put("data", data);
        return map;
    }
}
