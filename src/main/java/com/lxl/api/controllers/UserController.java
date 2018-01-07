package com.lxl.api.controllers;

import com.lxl.api.models.User;
import com.lxl.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getThree")
    public List<User> getThreeForUser() {
        List<User> list = userService.getUser();
        return list;
    }

    @RequestMapping("/getAll")
    public List<User> getAllUser(){

        List<User> list = userService.getAllUser();
        int num = list.size();
        if(null!=list && num>3){
            for (int i = 0; i < num-3; i++) {
                list.remove(0);
            }
        }
        return list;
    }

    @RequestMapping("/getByID")
    public List<User> getUserById(@RequestParam("id") String id){
        List<User> list = userService.getUserById(id);
        int num = list.size();
        if(null!=list && num>5){
            for (int i = 0; i < num-5; i++) {
                list.remove(0);
            }
        }
        return list;
    }

    @RequestMapping(value = "/add")
    public Map<String, Object> addUser(@RequestBody User user){
        System.out.println(user.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("message", "系统异常！");
        if (userService.addUser(user) > 0) {
            map.put("code", 1);
            return map;
        } else {
            return map;
        }
    }

    @RequestMapping(value="/delUserById",method= RequestMethod.POST)
    public int delUserById(@RequestParam("id") String id){
        return userService.delUser(id);
    }
}
