package com.yangonion.security.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class SmsAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 2732231697043319116L;
    private  final Object principal;

    public SmsAuthenticationToken(String mobile){
        super(null);
        this.principal=mobile;
        setAuthenticated(false);
    }

    public  SmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal=principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws  IllegalArgumentException {
        if (authenticated){
            throw  new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(authenticated);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
