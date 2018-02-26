package com.lxl.common.services;

import com.lxl.admin.models.request.AdminRoleUserRequest;
import com.lxl.admin.models.request.UserRoleRequest;
import com.lxl.admin.models.response.AdminRoleUserResponse;
import com.lxl.common.consts.CommonConst;
import com.lxl.common.mapper.AdminRoleUserMapper;
import com.lxl.common.models.AdminPrivilege;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.models.AdminUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminRoleUserService {

    @Autowired
    private AdminRoleUserMapper adminRoleUserMapper;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 保存角色用户信息
     * @param request UserRoleRequest
     * @return 影响的行数
     */
    @Transactional
    public Integer saveRoleUser(UserRoleRequest request) {
        AdminRoleUser adminRoleUser;
        for (Integer userId : request.getList()) {
            Map<String, Integer> map = new HashMap<>();
            map.put("userId", userId);
            map.put("roleId", request.getId());
            adminRoleUser = adminRoleUserMapper.findByUserIdAndRoleId(map);
            if (adminRoleUser != null) {
                ConsoleUtil.formatPrint("已经存在" + userId);
                continue;
            }
            adminRoleUser = new AdminRoleUser();
            adminRoleUser.setAdminRoleUserAdminRoleId(request.getId());
            adminRoleUser.setAdminRoleUserAdminUserId(userId);
            adminRoleUser.setAdminRoleUserCreateAt(new Date());
            adminRoleUserMapper.insertByParams(adminRoleUser);
        }

        return CommonConst.SUCCESS;
    }

    /**
     * 判断一个角色在给定角色列表中，是否有其父角色
     * @param roleId
     * @param listJudgeRoles   祖先列表
     * @return
     */
    public AdminRole isAncestorRole(Integer roleId, List<AdminRoleUser> listJudgeRoles)
    {
        AdminRole adminRole = adminRoleService.getOneById(roleId);
        List<Integer> judgeRoleIds = new ArrayList<>();
        if (adminRole == null) {
            return null;
        }
        for (AdminRoleUser adminRoleUser : listJudgeRoles) {
            judgeRoleIds.add(adminRoleUser.getAdminRoleUserAdminRoleId());
        }
        List<AdminRole> listJudgeRole = adminRoleService.getByRoleIds(judgeRoleIds);
        if (listJudgeRole.size() <= 0) {
            return null;
        }
        while (adminRole != null) {
            for (AdminRole judgeRole : listJudgeRole) {
                if (judgeRole.getAdminRoleId().equals(adminRole.getAdminRoleId())) {
                    return judgeRole;
                }
            }
            adminRole = adminRoleService.getOneById(adminRole.getAdminRoleParentId());
        }
        return null;
    }

    /**
     * 获取当前角色的用户，以及可分配的用户
     * @return 用户信息
     */
    public Map<String, List<AdminUser>> getRoleUser(Integer roleId) {
        // 获取能够选择给定角色的用户列表
        List<AdminUser> optionalUserList = this.getOptionalUsers(roleId);

        // 获取拥有给定角色的用户列表
        List<AdminUser> currentUserList = adminUserService.getUserByRoleId(roleId);
        Map<String, List<AdminUser>> map = new HashMap<>();
        map.put("all", optionalUserList);
        map.put("right", currentUserList);
        return map;
    }

    /**
     * 获取能够选择给定角色的用户列表
     * @param roleId Integer
     * @return array
     */
    public List<AdminUser> getOptionalUsers(Integer roleId)
    {
        List<AdminUser> allUserList = adminUserService.getAll();
        List<AdminUser> adminUserList = this.getAncestorRoleUsers(roleId);
        for (AdminUser adminUser : adminUserList) {
            allUserList.remove(adminUser);
        }
        return allUserList;
    }

    /**
     * 获取拥有祖先角色的用户列表
     * @param roleId Integer
     * @return array
     */
    public List<AdminUser> getAncestorRoleUsers(Integer roleId)
    {
        List<AdminUser> adminUserList = new ArrayList<>();
        AdminRole adminRole = adminRoleService.getOneById(roleId);
        AdminRole fatherAdminRole = adminRoleService.getOneById(adminRole.getAdminRoleParentId());
        while(fatherAdminRole != null) {
            List<AdminUser> fatherUserList = adminUserService.getUserByRoleId(fatherAdminRole.getAdminRoleId());
            adminUserList.addAll(fatherUserList);
            fatherAdminRole = adminRoleService.getOneById(fatherAdminRole.getAdminRoleParentId());
        }
        return adminUserList;
    }

    public List<AdminRoleUserResponse> getList(AdminRoleUserRequest request) {
        AdminRoleUser adminRoleUserSearch = formatModelDetail(request);
        List<AdminRoleUser> listAdminRoleUser = adminRoleUserMapper.findByParams(adminRoleUserSearch);
        List<AdminRoleUserResponse> list = new ArrayList<>();
        for (AdminRoleUser adminRoleUser : listAdminRoleUser) {
            AdminRoleUserResponse adminRoleUserResponse = formatResponseDetail(adminRoleUser);
            list.add(adminRoleUserResponse);
        }
        return list;
    }

    public Integer getTotal(AdminRoleUserRequest request) {
        AdminRoleUser adminRoleUserSearch = formatModelDetail(request);
        return adminRoleUserMapper.findTotalByParams(adminRoleUserSearch);
    }

    public List<AdminRoleUser> getAdminRolesByUserId(Integer userId) {
        return adminRoleUserMapper.findRolesByUserId(userId);
    }

    public Integer save(AdminRoleUserRequest request) {
        AdminRoleUser adminRoleUser;
        if (request.getId() != null) {
            adminRoleUser = adminRoleUserMapper.findOneById(request.getId());
        } else {
            adminRoleUser = new AdminRoleUser();
            adminRoleUser.setAdminRoleUserCreateAt(new Date());
        }
        adminRoleUser.setAdminRoleUserAdminRoleId(request.getAdminRoleId());
        adminRoleUser.setAdminRoleUserAdminUserId(request.getAdminUserId());
        adminRoleUser.setAdminRoleUserCreateBy(request.getCreateBy());
        adminRoleUser.setAdminRoleUserUpdateBy(request.getUpdateBy());
        if (request.getId() != null) {
            return adminRoleUserMapper.updateByIdAndParams(adminRoleUser);
        } else {
            return adminRoleUserMapper.insertByParams(adminRoleUser);
        }
    }

    public Integer delete(Integer id) {
        return adminRoleUserMapper.deleteOneById(id);
    }

    private AdminRoleUserResponse formatResponseDetail(AdminRoleUser adminRoleUser) {
        AdminRoleUserResponse response = new AdminRoleUserResponse();
        response.setId(adminRoleUser.getAdminRoleUserId());
        response.setAdminRoleId(adminRoleUser.getAdminRoleUserAdminRoleId());
        response.setAdminUserId(adminRoleUser.getAdminRoleUserAdminUserId());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminRoleUser.getAdminRoleUserCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminRoleUser.getAdminRoleUserUpdateAt()));
        response.setCreateBy(adminRoleUser.getAdminRoleUserCreateBy());
        response.setUpdateBy(adminRoleUser.getAdminRoleUserUpdateBy());
        return response;
    }

    private AdminRoleUser formatModelDetail(AdminRoleUserRequest request) {
        AdminRoleUser adminRoleUser = new AdminRoleUser();
        adminRoleUser.setAdminRoleUserId(request.getId());
        adminRoleUser.setAdminRoleUserAdminRoleId(request.getAdminRoleId());
        adminRoleUser.setAdminRoleUserAdminUserId(request.getAdminUserId());
        adminRoleUser.setAdminRoleUserCreateAt(request.getCreateAt());
        adminRoleUser.setAdminRoleUserUpdateAt(request.getUpdateAt());
        adminRoleUser.setAdminRoleUserCreateBy(request.getCreateBy());
        adminRoleUser.setAdminRoleUserUpdateBy(request.getUpdateBy());
        return adminRoleUser;
    }
}