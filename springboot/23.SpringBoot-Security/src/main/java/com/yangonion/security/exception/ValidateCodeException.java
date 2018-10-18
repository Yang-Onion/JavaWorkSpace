package com.yangonion.security.exception;


import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException  extends AuthenticationException {
    private static final long serialVersionUID = -2758195324164358160L;
    public ValidateCodeException(String message){
        super(message);
    }
}
