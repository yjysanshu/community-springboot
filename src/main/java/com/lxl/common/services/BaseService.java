package com.lxl.common.services;

import com.lxl.common.consts.HeaderConst;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.mapper.OauthUserMapper;
import com.lxl.common.models.AdminUser;
import com.lxl.common.models.OauthUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/1/8.
 */
public class BaseService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AdminUserMapper mapper;
    @Autowired
    private OauthUserMapper oauthUserMapper;

    public AdminUser getCurrentUser() {
        String token = request.getHeader(HeaderConst.X_TOKEN);
        return mapper.findOneByToken(token);
    }

    public String getCurrentUserToken() {
        return request.getHeader(HeaderConst.X_TOKEN);
    }

    public OauthUser getUser() {
        String token = request.getHeader(HeaderConst.X_TOKEN);
        if (token == null) {
            return null;
        }
        ConsoleUtil.formatPrint(token);
        return oauthUserMapper.findOneByToken(token);
    }
}
