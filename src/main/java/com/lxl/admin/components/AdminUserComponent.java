package com.lxl.admin.components;

import com.lxl.common.consts.HeaderConst;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

public class AdminUserComponent {

    @Autowired
    private AdminUserMapper mapper;

    public static AdminUser getCurrentFilterUser() {
        /*Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof String) {
            ConsoleUtil.formatPrint("String");
        } else if (user instanceof UserDetails) {
            ConsoleUtil.formatPrint("UserDetails");
        } else {
            ConsoleUtil.formatPrint(user.getClass());
        }*/
        return (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
