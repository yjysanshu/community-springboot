package com.lxl.common.util;

import java.util.HashMap;
import java.util.Map;

public class FormatUtil {

    public static Map<String, Object> success(Map data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "OK");
        map.put("data", data);
        return map;
    }
}
