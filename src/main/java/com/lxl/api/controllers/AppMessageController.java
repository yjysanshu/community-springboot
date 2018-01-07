package com.lxl.api.controllers;

import com.lxl.api.models.AppMessage;
import com.lxl.api.services.AppMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/16.
 */
@RestController
@RequestMapping("/appmessage")
public class AppMessageController {

    @Autowired
    private AppMessageService messageService;

    @RequestMapping("/getThree")
    public List<AppMessage> getThreeForMessage() {
        List<AppMessage> list = messageService.getMessage();
        return list;
    }

    @RequestMapping("/getAll")
    public List<AppMessage> getAllMessage(){

        List<AppMessage> list = messageService.getAllMessage();
        int num = list.size();
        if(null!=list && num>3){
            for (int i = 0; i < num-3; i++) {
                list.remove(0);
            }
        }
        return list;
    }

    @RequestMapping("/getByID")
    public List<AppMessage> getMessageById(@RequestParam("id") String id){
        List<AppMessage> list = messageService.getMessageById(id);
        int num = list.size();
        if(null!=list && num>5){
            for (int i = 0; i < num-5; i++) {
                list.remove(0);
            }
        }
        return list;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int addMessage(@RequestBody AppMessage appMessage){
        return messageService.addMessage(appMessage);
    }

    @RequestMapping(value="/delMessageById",method= RequestMethod.POST)
    public int delMessageById(@RequestParam("id") String id){
        return messageService.delMessage(id);
    }
}
