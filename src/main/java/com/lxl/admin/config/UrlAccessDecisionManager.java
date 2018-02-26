package com.lxl.admin.config;

import com.lxl.common.consts.CommonConst;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {
        ConsoleUtil.formatPrint("Class: "+this.getClass().getName()+" method: "+
                Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
                Thread.currentThread().getStackTrace()[1].getLineNumber());
        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            ConsoleUtil.formatPrint("needRole = " + needRole);
            if (CommonConst.ALLOW_ACCESS_URL.equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else {
                    ConsoleUtil.formatPrint("here = " + needRole);
                    return;
                }
            }

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        ConsoleUtil.formatPrint("需要权限,,,,,,,");
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        ConsoleUtil.formatPrint("Class: " + this.getClass().getName() + " method: " +
                Thread.currentThread().getStackTrace()[1].getMethodName() + " line:" +
                Thread.currentThread().getStackTrace()[1].getLineNumber());
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        ConsoleUtil.formatPrint("Class: " + this.getClass().getName() + " method: " +
                Thread.currentThread().getStackTrace()[1].getMethodName() + " line:" +
                Thread.currentThread().getStackTrace()[1].getLineNumber());
        return true;
    }
}
