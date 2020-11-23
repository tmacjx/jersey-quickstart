package com.bokecc.service;

import com.bokecc.util.JwtPaire;

public interface ICommonService {

    /**
     * 验证 jwt
     *
     * @param token token
     * @return 结果
     */
    JwtPaire authJwt(String token);

}