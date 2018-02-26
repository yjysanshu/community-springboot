package com.lxl.admin.config;

import com.lxl.common.consts.CommonConst;
import com.lxl.common.models.AdminResource;
import com.lxl.common.models.AdminRole;
import com.lxl.common.services.MenuService;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        ConsoleUtil.formatPrint("Class: "+this.getClass().getName()+" method: "+
                Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
                Thread.currentThread().getStackTrace()[1].getLineNumber());
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        ConsoleUtil.formatPrint("requestUrl = " + requestUrl);
        if (CommonConst.LOGIN_URL.equals(requestUrl) || CommonConst.ADMIN_USER_LOGIN_URL.equals(requestUrl)) {
            return null;
        }
        List<AdminResource> adminResourcesList = menuService.getAll();
        for (AdminResource adminResource : adminResourcesList) {
            if (antPathMatcher.match(adminResource.getAdminResourceUrl(), requestUrl) && adminResource.getAdminRoles().size() > 0) {
                List<AdminRole> roles = adminResource.getAdminRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getAdminRoleName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return null;
        //return SecurityConfig.createList(CommonConst.ALLOW_ACCESS_URL);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
