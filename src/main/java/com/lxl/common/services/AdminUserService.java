package com.lxl.common.services;

import com.lxl.admin.models.request.AdminUserRequest;
import com.lxl.admin.models.request.ChangeRequest;
import com.lxl.admin.models.response.AdminUserResponse;
import com.lxl.common.consts.AdminUserConst;
import com.lxl.common.consts.CommonConst;
import com.lxl.common.mapper.AdminRoleUserMapper;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.models.AdminUser;
import com.lxl.common.util.ConsoleUtil;
import com.lxl.common.util.encrypt.BASE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminUserService extends BaseService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleUserMapper adminRoleUserMapper;

    /**
     * 根据角色获取用户
     * @param roleId Integer
     * @return 角色对应的用户
     */
    public List<AdminUser> getUserByRoleId(Integer roleId) {
        return adminUserMapper.findUserByRoleId(roleId);
    }
    /**
     * 获取所有的用户信息
     * @return 所有用户信息
     */
    public List<AdminUser> getAll() {
        return adminUserMapper.findByParams(new AdminUser());
    }

    /**
     * 获取所有的用户信息-前端显示
     * @param request AdminUserRequest
     * @return 所有的用户信息-前端显示
     */
    public List<AdminUserResponse> getList(AdminUserRequest request) {
        AdminUser adminUserSearch = formatModelDetail(request);
        List<AdminUser> listAdminUser = adminUserMapper.findByParams(adminUserSearch);
        List<AdminUserResponse> list = new ArrayList<>();
        for (AdminUser adminUser : listAdminUser) {
            AdminUserResponse adminUserResponse = formatResponseDetail(adminUser);
            list.add(adminUserResponse);
        }
        return list;
    }

    public Integer getTotal(AdminUserRequest request) {
        AdminUser adminUserSearch = formatModelDetail(request);
        return adminUserMapper.findTotalByParams(adminUserSearch);
    }

    @Transactional
    public Integer save(AdminUserRequest request) {
        AdminUser adminUser;
        if (request.getId() != null) {
            adminUser = adminUserMapper.findOneById(request.getId());
        } else {
            adminUser = new AdminUser();
            adminUser.setAdminUserCreateAt(new Date());
        }
        adminUser.setAdminUserPhone(request.getPhone());
        adminUser.setAdminUserName(request.getName());
        adminUser.setAdminUserEmail(request.getEmail());
        adminUser.setAdminUserFullName(request.getFullName());
        adminUser.setAdminUserAvatar(request.getAvatar());
        adminUser.setAdminUserPosition(request.getPosition());

        String passwd = new BCryptPasswordEncoder().encode(CommonConst.INITIAL_PASSWORD);
        adminUser.setAdminUserPasswordHash(passwd);
        adminUser.setAdminUserPasswordResetToken(passwd.substring(20));
        adminUser.setAdminUserStatus(request.getStatus());
        adminUser.setAdminUserCreateBy(request.getCreateBy());
        adminUser.setAdminUserUpdateBy(request.getUpdateBy());
        if (request.getId() != null) {
            adminUserMapper.updateByIdAndParams(adminUser);
        } else {
            adminUserMapper.insertByParams(adminUser);
        }

        //插入或修改用户的角色
        AdminRoleUser adminRoleUser;
        for (Integer roleId : request.getRoles()) {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId", adminUser.getAdminUserId());
            map.put("roleId", roleId);
            adminRoleUser = adminRoleUserMapper.findByUserIdAndRoleId(map);
            if (adminRoleUser != null) {
                ConsoleUtil.formatPrint("已经存在" + adminUser.getAdminUserId());
                continue;
            }
            adminRoleUser = new AdminRoleUser();
            adminRoleUser.setAdminRoleUserAdminRoleId(roleId);
            adminRoleUser.setAdminRoleUserAdminUserId(adminUser.getAdminUserId());
            adminRoleUser.setAdminRoleUserCreateAt(new Date());
            adminRoleUserMapper.insertByParams(adminRoleUser);
        }
        return CommonConst.SUCCESS;
    }

    /**
     * 重置密码
     * @return -
     */
    public Integer resetPwd() {
        AdminUser adminUser = new AdminUser();
        String passwd = new BCryptPasswordEncoder().encode(CommonConst.INITIAL_PASSWORD);
        adminUser.setAdminUserPasswordHash(passwd);
        adminUser.setAdminUserPasswordResetToken(passwd.substring(20));
        return adminUserMapper.updateByIdAndParams(adminUser);
    }

    /**
     * 修改信息
     * @return -
     */
    public Integer changePwd(ChangeRequest request) {
        ConsoleUtil.formatPrint(request);
        AdminUser adminUser = this.getCurrentUser();
        int count = 0;
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String passwd = encrypt.encode(request.getPassword());
        if (encrypt.matches(request.getPassword(), adminUser.getAdminUserPasswordHash())) {
            adminUser.setAdminUserAvatar(request.getPic());
            adminUser.setAdminUserPasswordHash(new BCryptPasswordEncoder().encode(request.getNewPassword()));
            adminUser.setAdminUserPasswordResetToken(passwd.substring(20));
            count = adminUserMapper.updateByIdAndParams(adminUser);
        }
        return count;
    }

    public Integer delete(Integer id) {
        return adminUserMapper.deleteOneById(id);
    }

    private AdminUserResponse formatResponseDetail(AdminUser adminUser) {
        AdminUserResponse response = new AdminUserResponse();
        response.setId(adminUser.getAdminUserId());
        response.setPhone(adminUser.getAdminUserPhone());
        response.setName(adminUser.getAdminUserName());
        response.setEmail(adminUser.getAdminUserEmail());
        response.setFullName(adminUser.getAdminUserFullName());
        response.setAvatar(adminUser.getAdminUserAvatar());
        response.setPosition(adminUser.getAdminUserPosition());
        response.setAuthKey(adminUser.getAdminUserAuthKey());
        response.setPasswordHash(adminUser.getAdminUserPasswordHash());
        response.setPasswordResetToken(adminUser.getAdminUserPasswordResetToken());
        response.setStatus(adminUser.getAdminUserStatus());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminUser.getAdminUserCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminUser.getAdminUserUpdateAt()));
        response.setCreateBy(adminUser.getAdminUserCreateBy());
        response.setUpdateBy(adminUser.getAdminUserUpdateBy());

        List<String> list = new ArrayList<>();
        for (AdminRole adminRole : adminUser.getAdminRoles()) {
            list.add(adminRole.getAdminRoleName());
        }
        response.setRoles(list);
        return response;
    }

    private AdminUser formatModelDetail(AdminUserRequest request) {
        AdminUser adminUser = new AdminUser();
        adminUser.setAdminUserId(request.getId());
        adminUser.setAdminUserPhone(request.getPhone());
        adminUser.setAdminUserName(request.getName());
        adminUser.setAdminUserEmail(request.getEmail());
        adminUser.setAdminUserFullName(request.getFullName());
        adminUser.setAdminUserAvatar(request.getAvatar());
        adminUser.setAdminUserPosition(request.getPosition());
        adminUser.setAdminUserStatus(request.getStatus());
        adminUser.setAdminUserCreateAt(request.getCreateAt());
        adminUser.setAdminUserUpdateAt(request.getUpdateAt());
        adminUser.setAdminUserCreateBy(request.getCreateBy());
        adminUser.setAdminUserUpdateBy(request.getUpdateBy());
        return adminUser;
    }
}