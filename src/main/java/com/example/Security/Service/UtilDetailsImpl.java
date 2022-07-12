package com.example.Security.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Security.Entites.Utilisateur;

public class UtilDetailsImpl implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UtilDetailsImpl(Long userId, String username,String password,
    Collection <? extends GrantedAuthority> authorities){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities=authorities;
    }
    public static UtilDetailsImpl build(Utilisateur utilisateur){
        List<GrantedAuthority> authorities = utilisateur.getRoles().stream()
                        .map(role ->new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UtilDetailsImpl(
            utilisateur.getUserId(),
            utilisateur.getUsername(),
            utilisateur.getPassword(),
            authorities);
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o==null || getClass() != o.getClass())
        return false;

        UtilDetailsImpl user = (UtilDetailsImpl) o;
        return Objects.equals(userId, user.userId);
    }

    
}
