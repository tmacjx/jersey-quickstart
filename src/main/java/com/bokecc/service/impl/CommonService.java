package com.bokecc.service.impl;

import com.bokecc.model.UserJwtKey;
import com.bokecc.service.ICacheService;
import com.bokecc.service.ICommonService;
import com.bokecc.util.JwtPaire;
import com.bokecc.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CommonService implements ICommonService {

    ICacheService cacheService;

    @Override
    public JwtPaire authJwt(String token) {

        try {

            String appKey = JwtUtil.getAppkey(token);

            UserJwtKey userJwtKey = cacheService.getJwtCache(appKey);

            if(null != userJwtKey){

                return JwtUtil.verifyToken(token, userJwtKey.getAppSecret());
            }

            log.info("appKey = {}, 不存在", appKey);

        }catch (Exception e){

            log.error("jwt auth 异常: {}", e.getMessage());
        }

        return JwtPaire.builder().left(-1).right("token is invalid").build();
    }

}
