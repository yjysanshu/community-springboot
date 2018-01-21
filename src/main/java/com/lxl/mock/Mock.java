package com.lxl.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mock {

    public static List<Map<String, Object>> getMenu() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("url", "");
        map.put("parent_id", 0);
        map.put("title", "系统管理");
        map.put("path", "/system");
        map.put("seq", 100);
        map.put("icon", "");
        map.put("type", "layout");
        map.put("checked", "checked");
        map.put("children", Mock.getSubMenu());
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 4);
        map1.put("url", "");
        map1.put("parent_id", 0);
        map1.put("title", "楼栋住户管理");
        map1.put("path", "/house");
        map1.put("seq", 99);
        map1.put("icon", "");
        map1.put("type", "layout");
        map1.put("checked", "checked");
        map1.put("children", Mock.getHouseSubMenu());
        list.add(map1);

        return list;
    }

    public static List<Map<String, Object>> getSubMenu() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("url", "");
        map.put("parent_id", 1);
        map.put("title", "菜单管理");
        map.put("path", "system/menu");
        map.put("seq", 9);
        map.put("icon", "");
        map.put("type", "default");
        map.put("checked", "checked");
        map.put("children", new ArrayList<Map<String, Object>>());
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 3);
        map1.put("url", "");
        map1.put("parent_id", 1);
        map1.put("title", "用户管理");
        map1.put("path", "system/user");
        map1.put("seq", 8);
        map1.put("icon", "");
        map1.put("type", "default");
        map1.put("checked", "checked");
        map1.put("children", new ArrayList<Map<String, Object>>());
        list.add(map1);
        return list;
    }

    public static List<Map<String, Object>> getHouseSubMenu() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 5);
        map.put("url", "");
        map.put("parent_id", 4);
        map.put("title", "楼东管理");
        map.put("path", "house/house");
        map.put("seq", 9);
        map.put("icon", "");
        map.put("type", "default");
        map.put("checked", "checked");
        map.put("children", new ArrayList<Map<String, Object>>());
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 6);
        map1.put("url", "");
        map1.put("parent_id", 4);
        map1.put("title", "房间管理");
        map1.put("path", "house/room");
        map1.put("seq", 8);
        map1.put("icon", "");
        map1.put("type", "default");
        map1.put("checked", "checked");
        map1.put("children", new ArrayList<Map<String, Object>>());
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 7);
        map2.put("url", "");
        map2.put("parent_id", 4);
        map2.put("title", "住户管理");
        map2.put("path", "house/hold");
        map2.put("seq", 7);
        map2.put("icon", "");
        map2.put("type", "default");
        map2.put("checked", "checked");
        map2.put("children", new ArrayList<Map<String, Object>>());
        list.add(map2);
        return list;
    }
}
