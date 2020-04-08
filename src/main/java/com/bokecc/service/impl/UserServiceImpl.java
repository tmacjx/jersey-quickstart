package com.bokecc.service.impl;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.mapper.UserMapper;
import com.bokecc.model.User;
import com.bokecc.service.IuserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements IuserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> selectAll() {
        return mapper.selectList(null);
    }

    @Override
    public User selectById(String id) {
        return mapper.selectById(id);
    }

    @Override
    public int updateOne(User user) {
        return mapper.updateById(user);
    }

    @Override
    public int insertOne(User user) {
        return mapper.insert(user);
    }

    @Override
    public int deleteOne(Long id) {
        return mapper.deleteById(id);
    }

}
