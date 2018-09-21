package com.yangonion.config.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class GithubProperty{
    @Value("${github.account.name}")
    private  String account;
    @Value("${github.account.email}")
    private  String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "account='" + account + '\'' + ", email='" + email + '\'';
    }
}
