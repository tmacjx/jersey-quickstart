package com.bokecc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.model.User;
import org.apache.ibatis.session.RowBounds;
import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectByUserId(String userid);

//    @Override
//    List<User> selectList(Wrapper<User> wrapper);
//
//    @Override
//    User selectById(Serializable serializable);
//
//    @Override
//    Integer updateById(User user);
//
//    @Override
//    Integer update(User user, Wrapper<User> updateWrapper);
//
//    @Override
//    Integer deleteById(Serializable id);
//
//    @Override
//    Integer insert(User user);

//
//    @Override
//    List<User> selectPage(RowBounds rowBounds, Wrapper<User> wrapper);

}
