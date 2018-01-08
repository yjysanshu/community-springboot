package com.lxl.api.services;

import com.lxl.api.mapper.AppMessageMapper;
import com.lxl.api.models.AppMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/16.
 */
@Service
public class AppMessageService {

    @Autowired
    private AppMessageMapper mapper;

    public List<AppMessage> getMessage() {
        List<AppMessage> list = new ArrayList<>();
        list.add(mapper.selectByPrimaryKey("xtt"));
        return list;
    }

    public List<AppMessage> getAllMessage(){
        List<AppMessage> list = mapper.selectAll();
        return list;
    }

    public int addMessage(AppMessage appMessage) {
        return mapper.insert(appMessage);
    }

    public List<AppMessage> getMessageById(String id) {
        return mapper.getMessById(id);
    }

    public int delMessage(String id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
