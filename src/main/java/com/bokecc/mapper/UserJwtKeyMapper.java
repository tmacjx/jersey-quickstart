package com.bokecc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bokecc.model.UserJwtKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */

@Repository
public interface UserJwtKeyMapper extends BaseMapper<UserJwtKey> {

    /**
     * 获取用户key信息
     * @param appKey key
     * @return obj
     */
    UserJwtKey selectByAppKey(@Param("appKey") String appKey);

}
