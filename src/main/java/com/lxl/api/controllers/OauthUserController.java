package com.lxl.api.controllers;

import com.lxl.api.models.request.OauthUserRequest;
import com.lxl.api.models.response.OauthUserResponse;
import com.lxl.common.consts.ErrorConst;
import com.lxl.common.models.OauthUser;
import com.lxl.common.services.OauthUserService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth-user")
public class OauthUserController {

    @Autowired
    private OauthUserService oauthUserService;

    @RequestMapping("/login")
    public Map login(@RequestBody OauthUserRequest request) {
        OauthUserResponse response = oauthUserService.login(request);
        if (response != null) {
            return FormatUtil.success(response);
        }
        return FormatUtil.fail(ErrorConst.LOGIN_FAIL, null);
    }

    @RequestMapping("/list")
    public Map list(@RequestBody OauthUserRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", oauthUserService.getList(request));
        map.put("total", oauthUserService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody OauthUserRequest request) {
        return FormatUtil.success(oauthUserService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody OauthUserRequest request) {
        if (oauthUserService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}