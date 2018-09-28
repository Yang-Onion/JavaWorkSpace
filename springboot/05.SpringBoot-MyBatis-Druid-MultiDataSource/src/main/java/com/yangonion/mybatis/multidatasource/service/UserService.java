package com.yangonion.mybatis.multidatasource.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<Long,Object>> getAllUser();
}
