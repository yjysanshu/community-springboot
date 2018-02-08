package com.lxl.admin.components;

import com.lxl.common.models.AdminUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminUserComponent {
    public static AdminUser getCurrentUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o instanceof String) {
            ConsoleUtil.formatPrint("String");
        } else if (o instanceof UserDetails) {
            ConsoleUtil.formatPrint("UserDetails");
        } else {
            ConsoleUtil.formatPrint(o.getClass());
        }

        return (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
