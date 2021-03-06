package com.lxl.api.services;

import com.lxl.common.mapper.UserMapper;
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
        List<User> list = new ArrayList<>();
        list.add(mapper.findOneById(0));
        return list;
    }

    public List<User> getAllUser(){
        return mapper.findByParams(new User());
    }

    public int addUser(User user) {
        return mapper.insert(user);
    }

    public User getUserById(Integer id) {
        return mapper.findOneById(id);
    }

    public int delUser(Integer id) {
        return mapper.deleteOneById(id);
    }
}
