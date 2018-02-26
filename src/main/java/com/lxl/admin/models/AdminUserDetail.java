package com.lxl.admin.models;

import com.lxl.common.consts.AdminUserConst;
import com.lxl.common.models.AdminRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AdminUserDetail implements UserDetails {
    private Integer adminUserId;
    private String adminUserPhone;
    private String adminUserName;
    private String adminUserEmail;
    private String adminUserFullName;
    private String adminUserAvatar;
    private String adminUserPosition;
    private String adminUserAuthKey;
    private String adminUserPasswordHash;
    private String adminUserPasswordResetToken;
    private Integer adminUserStatus;
    private Date adminUserCreateAt;
    private Date adminUserUpdateAt;
    private String adminUserCreateBy;
    private String adminUserUpdateBy;

    private List<AdminRole> adminRoles;

    public Integer getAdminUserId() {
        return this.adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminUserPhone() {
        return this.adminUserPhone;
    }

    public void setAdminUserPhone(String adminUserPhone) {
        this.adminUserPhone = adminUserPhone;
    }

    public String getAdminUserName() {
        return this.adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminUserEmail() {
        return this.adminUserEmail;
    }

    public void setAdminUserEmail(String adminUserEmail) {
        this.adminUserEmail = adminUserEmail;
    }

    public String getAdminUserFullName() {
        return this.adminUserFullName;
    }

    public void setAdminUserFullName(String adminUserFullName) {
        this.adminUserFullName = adminUserFullName;
    }

    public String getAdminUserAvatar() {
        return this.adminUserAvatar;
    }

    public void setAdminUserAvatar(String adminUserAvatar) {
        this.adminUserAvatar = adminUserAvatar;
    }

    public String getAdminUserPosition() {
        return this.adminUserPosition;
    }

    public void setAdminUserPosition(String adminUserPosition) {
        this.adminUserPosition = adminUserPosition;
    }

    public String getAdminUserAuthKey() {
        return this.adminUserAuthKey;
    }

    public void setAdminUserAuthKey(String adminUserAuthKey) {
        this.adminUserAuthKey = adminUserAuthKey;
    }

    public String getAdminUserPasswordHash() {
        return this.adminUserPasswordHash;
    }

    public void setAdminUserPasswordHash(String adminUserPasswordHash) {
        this.adminUserPasswordHash = adminUserPasswordHash;
    }

    public String getAdminUserPasswordResetToken() {
        return this.adminUserPasswordResetToken;
    }

    public void setAdminUserPasswordResetToken(String adminUserPasswordResetToken) {
        this.adminUserPasswordResetToken = adminUserPasswordResetToken;
    }

    public Integer getAdminUserStatus() {
        return this.adminUserStatus;
    }

    public void setAdminUserStatus(Integer adminUserStatus) {
        this.adminUserStatus = adminUserStatus;
    }

    public Date getAdminUserCreateAt() {
        return this.adminUserCreateAt;
    }

    public void setAdminUserCreateAt(Date adminUserCreateAt) {
        this.adminUserCreateAt = adminUserCreateAt;
    }

    public Date getAdminUserUpdateAt() {
        return this.adminUserUpdateAt;
    }

    public void setAdminUserUpdateAt(Date adminUserUpdateAt) {
        this.adminUserUpdateAt = adminUserUpdateAt;
    }

    public String getAdminUserCreateBy() {
        return this.adminUserCreateBy;
    }

    public void setAdminUserCreateBy(String adminUserCreateBy) {
        this.adminUserCreateBy = adminUserCreateBy;
    }

    public String getAdminUserUpdateBy() {
        return this.adminUserUpdateBy;
    }

    public void setAdminUserUpdateBy(String adminUserUpdateBy) {
        this.adminUserUpdateBy = adminUserUpdateBy;
    }

    public List<AdminRole> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (AdminRole role : adminRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getAdminRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getAdminUserPasswordHash();
    }

    @Override
    public String getUsername() {
        return this.adminUserEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return adminUserStatus == AdminUserConst.USER_STATUS_ACTIVE;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "adminUserId=" + adminUserId +
                ", adminUserPhone='" + adminUserPhone + '\'' +
                ", adminUserName='" + adminUserName + '\'' +
                ", adminUserEmail='" + adminUserEmail + '\'' +
                ", adminUserFullName='" + adminUserFullName + '\'' +
                ", adminUserAvatar='" + adminUserAvatar + '\'' +
                ", adminUserPosition='" + adminUserPosition + '\'' +
                ", adminUserAuthKey='" + adminUserAuthKey + '\'' +
                ", adminUserPasswordHash='" + adminUserPasswordHash + '\'' +
                ", adminUserPasswordResetToken='" + adminUserPasswordResetToken + '\'' +
                ", adminUserStatus=" + adminUserStatus +
                ", adminUserCreateAt=" + adminUserCreateAt +
                ", adminUserUpdateAt=" + adminUserUpdateAt +
                ", adminUserCreateBy='" + adminUserCreateBy + '\'' +
                ", adminUserUpdateBy='" + adminUserUpdateBy + '\'' +
                ", adminRoles=" + adminRoles +
                '}';
    }
}
