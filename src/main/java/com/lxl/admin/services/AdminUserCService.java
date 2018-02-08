package com.lxl.admin.services;

import com.lxl.admin.models.request.AdminUserRequest;
import com.lxl.common.consts.AdminUserConst;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminUser;
import com.lxl.common.util.encrypt.BASE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserCService implements UserDetailsService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminUser user = adminUserMapper.findOneByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    /**
     * 用户登录
     * @param request
     * @return true-登陆成功
     */
    public Boolean login(AdminUserRequest request) {
        AdminUser adminUser = this.getUser(request.getPhone(), request.getEmail(), request.getPasswordHash());
        return adminUser != null;
    }

    /**
     * 检查登录信息是否正确
     * @param phone 手机号
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    public AdminUser getUser(String phone, String email, String password)
    {
        AdminUser adminUser;
        //密码是否为空
        if (password.isEmpty()) {
            return null;
        }
        if (!phone.isEmpty()) {
            adminUser = adminUserMapper.findOneByPhone(phone);
        } else {
            adminUser = adminUserMapper.findOneByEmail(email);
        }
        if (adminUser == null) {
            return null;
        }
        if (adminUser.getAdminUserStatus() != AdminUserConst.USER_STATUS_ACTIVE) {
            return null;
        }
        //密码是否正确
        if (!adminUser.getAdminUserPasswordHash().equals(BASE.encrypt(password))) {
            return null;
        }
        return adminUser;
    }
}
