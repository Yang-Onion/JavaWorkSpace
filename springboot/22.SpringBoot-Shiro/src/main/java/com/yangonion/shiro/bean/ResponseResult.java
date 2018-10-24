package com.yangonion.shiro.bean;

import java.util.HashMap;
import java.util.Map;

public class ResponseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResponseResult() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static ResponseResult error() {
        return error(1, "操作失败");
    }

    public static ResponseResult error(String msg) {
        return error(500, msg);
    }

    public static ResponseResult error(int code, String msg) {
        ResponseResult ResponseResult = new ResponseResult();
        ResponseResult.put("code", code);
        ResponseResult.put("msg", msg);
        return ResponseResult;
    }

    public static ResponseResult ok(String msg) {
        ResponseResult ResponseResult = new ResponseResult();
        ResponseResult.put("msg", msg);
        return ResponseResult;
    }

    public static ResponseResult ok(Map<String, Object> map) {
        ResponseResult ResponseResult = new ResponseResult();
        ResponseResult.putAll(map);
        return ResponseResult;
    }

    public static ResponseResult ok() {
        return new ResponseResult();
    }

    @Override
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}