package com.cailou.esigAtividadeTecnica.detail;

import com.cailou.esigAtividadeTecnica.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailData implements UserDetails {
    private UserModel user;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailData(UserModel user) {
        this.user = user;
        this.authorities = new ArrayList<SimpleGrantedAuthority>();
    }

    public static UserDetailData creat(UserModel user) {
        return new UserDetailData(user);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public String getId() {
        return user.getId().toString();
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
        return true;
    }
}



