package com.lxl.api.services;

import com.lxl.api.mapper.UserMapper;
import com.lxl.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public List<User> getUser() {
        List<User> list = new ArrayList<User>();
        list.add(mapper.selectByPrimaryKey("xtt"));
        return list;
    }

    public List<User> getAllUser(){
        List<User> list = new ArrayList<User>();
        list = mapper.selectAll();
        return list;
    }

    public int addUser(User user) {
        return mapper.insert(user);
    }

    public List<User> getUserById(String id) {
        return mapper.getMessById(id);
    }

    public int delUser(String id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
