package com.lxl.common.services;

import com.lxl.api.models.request.OauthUserRequest;
import com.lxl.api.models.response.OauthUserResponse;
import com.lxl.common.mapper.OauthUserMapper;
import com.lxl.common.models.OauthUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OauthUserService {

    @Autowired
    private OauthUserMapper oauthUserMapper;

    public OauthUserResponse login(OauthUserRequest request) {
        ConsoleUtil.formatPrint(request);
        OauthUser oauthUser = oauthUserMapper.findOneByPhone(request.getPhone());
        if (oauthUser != null && (new BCryptPasswordEncoder().matches(request.getPassword(), oauthUser.getOauthUserPasswordHash()))) {
            return formatResponseDetail(oauthUser);
        }
        return null;
    }

    public List<OauthUserResponse> getList(OauthUserRequest request) {
        OauthUser oauthUserSearch = formatModelDetail(request);
        List<OauthUser> listOauthUser = oauthUserMapper.findByParams(oauthUserSearch);
        List<OauthUserResponse> list = new ArrayList<>();
        for (OauthUser oauthUser : listOauthUser) {
            OauthUserResponse oauthUserResponse = formatResponseDetail(oauthUser);
            list.add(oauthUserResponse);
        }
        return list;
    }

    public Integer getTotal(OauthUserRequest request) {
        OauthUser oauthUserSearch = formatModelDetail(request);
        return oauthUserMapper.findTotalByParams(oauthUserSearch);
    }

    public String getUserNameById(int userId) {
        OauthUser oauthUser = oauthUserMapper.findOneById(userId);
        if (oauthUser == null) {
            return null;
        }
        return oauthUser.getOauthUserName();
    }

    public Integer save(OauthUserRequest request) {
        OauthUser oauthUser;
        if (request.getId() != null) {
            oauthUser = oauthUserMapper.findOneById(request.getId());
        } else {
            oauthUser = new OauthUser();
            oauthUser.setOauthUserCreateAt(new Date());
        }
        oauthUser.setOauthUserPhone(request.getPhone());
        oauthUser.setOauthUserName(request.getName());
        oauthUser.setOauthUserPasswordHash(request.getPassword());
        oauthUser.setOauthUserPasswordResetToken(request.getToken());
        oauthUser.setOauthUserHouseHoldId(request.getHouseHoldId());
        oauthUser.setOauthUserStatus(request.getStatus());
        if (request.getId() != null) {
            return oauthUserMapper.updateByIdAndParams(oauthUser);
        } else {
            return oauthUserMapper.insertByParams(oauthUser);
        }
    }

    public Integer addUser(int houseHoldId,  String houseHoldPhone, String houseHoldName) {
        OauthUser oauthUser = oauthUserMapper.findOneByPhone(houseHoldPhone);
        if (oauthUser == null) {
            oauthUser = new OauthUser();
            oauthUser.setOauthUserCreateAt(new Date());
            oauthUser.setOauthUserPhone(houseHoldPhone);
            String passwd = (new BCryptPasswordEncoder()).encode(houseHoldPhone);
            oauthUser.setOauthUserPasswordHash(passwd);
            oauthUser.setOauthUserPasswordResetToken(passwd.substring(20));
        }
        oauthUser.setOauthUserName(houseHoldName);
        oauthUser.setOauthUserHouseHoldId(houseHoldId);
        oauthUser.setOauthUserStatus(0);
        return oauthUserMapper.insertByParams(oauthUser);
    }

    public Integer delete(Integer id) {
        return oauthUserMapper.deleteOneById(id);
    }

    private OauthUserResponse formatResponseDetail(OauthUser oauthUser) {
        OauthUserResponse response = new OauthUserResponse();
        response.setId(oauthUser.getOauthUserId());
        response.setPhone(oauthUser.getOauthUserPhone());
        response.setName(oauthUser.getOauthUserName());
        //response.setPasswordHash(oauthUser.getOauthUserPasswordHash());
        response.setToken(oauthUser.getOauthUserPasswordResetToken());
        response.setHouseHoldId(oauthUser.getOauthUserHouseHoldId());
        response.setStatus(oauthUser.getOauthUserStatus());
        //response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(oauthUser.getOauthUserCreateAt()));
        //response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(oauthUser.getOauthUserUpdateAt()));
        return response;
    }

    private OauthUser formatModelDetail(OauthUserRequest request) {
        OauthUser oauthUser = new OauthUser();
        oauthUser.setOauthUserId(request.getId());
        oauthUser.setOauthUserPhone(request.getPhone());
        oauthUser.setOauthUserName(request.getName());
        oauthUser.setOauthUserPasswordHash(request.getPassword());
        oauthUser.setOauthUserPasswordResetToken(request.getToken());
        oauthUser.setOauthUserHouseHoldId(request.getHouseHoldId());
        oauthUser.setOauthUserStatus(request.getStatus());
        return oauthUser;
    }
}