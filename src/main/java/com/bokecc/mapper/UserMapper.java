package com.bokecc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.model.User;

import java.io.Serializable;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Override
    List<User> selectList(Wrapper wrapper);

    @Override
    User selectById(Serializable serializable);

    @Override
    Integer updateById(User user);

    @Override
    Integer delete(Wrapper<User> wrapper);

}
