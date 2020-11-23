package com.bokecc.service.impl;
import com.bokecc.mapper.UserJwtKeyMapper;
import com.bokecc.model.UserJwtKey;
import com.bokecc.service.ICacheService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CacheServiceImpl implements ICacheService {


    @Autowired
    private UserJwtKeyMapper jwtKeyMapper;


    @Override
    @Cacheable(cacheNames = "common-cache")
    public UserJwtKey getJwtCache(String appKey){

        return jwtKeyMapper.selectByAppKey(appKey);
    }

    @Override
    @CacheEvict(cacheNames = "common-cache")
    public void delJwtCache(String appKey){

        log.info("del jwt cache appkey = {}", appKey);
    }


}
