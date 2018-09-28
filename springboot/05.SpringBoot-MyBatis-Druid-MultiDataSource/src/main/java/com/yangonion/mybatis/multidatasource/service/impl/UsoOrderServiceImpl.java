package com.yangonion.mybatis.multidatasource.service.impl;

import com.yangonion.mybatis.multidatasource.mssqldao.UsoOrderMapper;
import com.yangonion.mybatis.multidatasource.service.UsoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("usoOrderService")
public class UsoOrderServiceImpl implements UsoOrderService {
    @Autowired
    private UsoOrderMapper usoOrderMapper;

    @Override
    public List<Map<Integer, Object>> getAllUsoOrder() {
        return usoOrderMapper.getAllUsoOrder();
    }
}
