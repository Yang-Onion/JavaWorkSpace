package com.yangonion.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SmsCodeException extends AuthenticationException {

    private static final long serialVersionUID = -3387531302587421774L;

    public  SmsCodeException(String message){
        super(message);
    }
}
