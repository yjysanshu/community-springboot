package com.lxl.admin.controllers;

import com.lxl.common.consts.HeaderConst;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class CommonController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AdminUserMapper mapper;

    public AdminUser getCurrentUser() {
        String token = request.getHeader(HeaderConst.X_TOKEN);
        return mapper.findOneByToken(token);
    }

    public String getCurrentUserToken() {
        return request.getHeader(HeaderConst.X_TOKEN);
    }
}
