package com.yangonion.security.sms;

import java.time.LocalDateTime;

public class SmsCode {

    private String code;
    private LocalDateTime expiredTime;

    public SmsCode(String code, int expiredIn) {
        this.code = code;
        this.expiredTime = expiredTime.plusSeconds(expiredIn);
    }

    public SmsCode(String code, LocalDateTime expiredTime) {
        this.code = code;
        this.expiredTime = expiredTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean isExpired(){
        return this.expiredTime.isBefore(LocalDateTime.now());
    }
}
