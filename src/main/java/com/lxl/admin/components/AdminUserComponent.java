package com.lxl.admin.components;

import com.lxl.common.models.AdminUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUserComponent {

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
