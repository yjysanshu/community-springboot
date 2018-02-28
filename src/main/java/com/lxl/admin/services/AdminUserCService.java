package com.lxl.admin.services;

import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserCService implements UserDetailsService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminUser user = adminUserMapper.findOneByEmail(email);
        if (user == null) {
            user = adminUserMapper.findOneByPhone(email);
            if (user == null)
                throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
