package com.yangonion.security.service;

import com.yangonion.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User  user = new User();
            user.setUserName("Yang-Onion");
            String encodePassword = passwordEncoder.encode("test123");
            user.setPassWord(encodePassword);
            System.out.println(String.format("加密后的密码:%s",encodePassword));

            return  new org.springframework.security.core.userdetails.User(username,user.getPassWord(),user.isEnabled(),user.isAccountNonExpired(),
                    user.isCredentialsNonExpired(),user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
            );
    }
}
