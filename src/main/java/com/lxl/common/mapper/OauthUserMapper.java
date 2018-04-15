package com.lxl.common.mapper;

import com.lxl.common.models.OauthUser;

public interface OauthUserMapper extends BaseMapper<OauthUser> {
    OauthUser findOneByPhone(String phone);
    OauthUser findOneByToken(String token);
}