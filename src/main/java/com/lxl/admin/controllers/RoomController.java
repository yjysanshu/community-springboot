package com.lxl.admin.controllers;

import com.lxl.admin.models.request.RoomRequest;
import com.lxl.common.services.RoomService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/list")
    public Map list(@RequestBody RoomRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", roomService.getList(request));
        map.put("total", roomService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/all")
    public Map all() {
        return FormatUtil.success(roomService.getAll());
    }

    @RequestMapping("/save")
    public Map save(@RequestBody RoomRequest request) {
        return FormatUtil.success(roomService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody RoomRequest request) {
        if (roomService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}