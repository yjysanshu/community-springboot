package com.lxl.common.services;

import com.lxl.admin.models.request.AdminRoleUserRequest;
import com.lxl.admin.models.response.AdminRoleUserResponse;
import com.lxl.common.mapper.AdminRoleUserMapper;
import com.lxl.common.models.AdminPrivilege;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminRoleUserService {

    @Autowired
    private AdminRoleUserMapper adminRoleUserMapper;

    @Autowired
    private AdminRoleService adminRoleService;

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