package com.bokecc.service;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.model.User;
import com.bokecc.model.UserJwtKey;
import com.bokecc.supports.ServiceResult;

/**
 *
 */
public interface ICacheService {
    UserJwtKey getJwtCache(String appKey);

    void delJwtCache(String appKey);

}