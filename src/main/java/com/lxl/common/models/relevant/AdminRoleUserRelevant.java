package com.lxl.common.models.relevant;

import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.models.AdminUser;

public class AdminRoleUserRelevant extends AdminRoleUser {
    private AdminUser adminUser;
    private AdminRole adminRole;

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(AdminRole adminRole) {
        this.adminRole = adminRole;
    }
}
