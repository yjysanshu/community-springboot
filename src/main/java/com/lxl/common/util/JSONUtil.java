package com.lxl.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxl.admin.models.Menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {

    /**
     * json转成map
     * @param jsonString String
     * @return Map
     * @throws IOException exception
     */
    public static Map jsonToMap(String jsonString) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.readValue(jsonString, Map.class);
    }

    /**
     * json转成map
     * @param map Map
     * @return Map
     * @throws IOException exception
     */
    public static String mapToJson(Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 把menu中的json转换为字符串
     * @param menu 菜单对象
     * @return 字符串
     * @throws JsonProcessingException -
     */
    public static String menuToJson(Menu menu) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("title", menu.getTitle());
        map.put("path", menu.getPath());
        map.put("seq", menu.getSeq());
        map.put("icon", menu.getIcon());
        map.put("type", menu.getType());
        return objectMapper.writeValueAsString(map);
    }
}
