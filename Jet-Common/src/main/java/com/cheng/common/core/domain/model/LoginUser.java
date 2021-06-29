package com.cheng.common.core.domain.model;

import com.cheng.common.core.domain.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 使用者登入身份權限
 */
@Data
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 8013567552274306371L;

    /**
     * 使用者Token
     */
    private String token;

    /**
     * 登入時間
     */
    private Long loginTime;

    /**
     * 逾期時間
     */
    private Long expireTime;

    /**
     * 登入IP
     */
    private String loginIp;

    /**
     * 登入地點
     */
    private String loginLocation;

    /**
     * 瀏覽器類型
     */
    private String browser;

    /**
     * 作業系統
     */
    private String os;

    /**
     * 權限列表
     */
    private Set<String> permissions;

    /**
     * 使用者資訊
     */
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 帳號是否未過期，過期無法驗證
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定的帳號是否解鎖，鎖定的帳號不能身份驗證
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已過期的帳號憑證(密碼)，過期的憑證防止認證
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 帳號是否可用，禁用的帳號不能身份驗證
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}
