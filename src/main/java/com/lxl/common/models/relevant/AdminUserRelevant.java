package com.lxl.common.models.relevant;

import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminUser;

public class AdminUserRelevant extends AdminUser {
    private AdminRole adminRole;

    public AdminRole getAdminRole() {
        return adminRole;
    }
}
