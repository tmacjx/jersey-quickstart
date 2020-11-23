package com.bokecc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bokecc.model.User;
import org.springframework.stereotype.Repository;



@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectByUserId(String userId);

}
