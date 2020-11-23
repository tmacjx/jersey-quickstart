package com.bokecc.service;

import com.bokecc.supports.ServiceResult;
import com.bokecc.model.UserJwtKey;
import com.bokecc.param.JwtParam;

import java.util.List;

/**
 * @author: Faizel Lannister(chufuying)
 * @date: 2020/5/28
 * @version: 0.0.1
 * @jdk: 1.8
 * @email: chufuying@163.com
 **/
public interface IJwtService {

    /**
     * 获取所有jwt  key  value
     * @return list
     */
    List<UserJwtKey> findAll();

    /**
     * 插入
     * @param param 参数
     * @return responseParam
     */
    ServiceResult<Integer, Integer> insert(JwtParam param);

    /**
     * 更新
     * @param jwtParam param 参数
     * @return int
     */
    int update(JwtParam jwtParam);

    /**
     * 删除
     * @param jwtKey jwt key
     * @return int
     */
    int delete(String jwtKey);
}
