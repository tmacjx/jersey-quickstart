package com.bokecc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bokecc.mapper.UserMapper;
import com.bokecc.model.User;
import com.bokecc.service.ICacheService;
import com.bokecc.service.IUserService;
import com.bokecc.supports.CommonErrorCode;
import com.bokecc.supports.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> selectAll() {
        return mapper.selectList(null);
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public User selectById(Long id) {
        return mapper.selectById(id);
    }

    @Cacheable(value = "user", key = "#user.id")
    @Override
    public User selectOne(User user){
       return mapper.selectOne(user);
    }

    @Cacheable(value = "user", key = "#userId")
    @Override
    public User selectByUserId(String userId){
        return mapper.selectByUserId(userId);
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public int updateOne(User user, Wrapper<User> wrapper) {
        return mapper.update(user, wrapper);
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public int insertOne(User user) {
        return mapper.insert(user);
    }

    @CacheEvict(value = "user", key = "#id")
    @Override
    public int deleteOne(Long id) {
        return mapper.deleteById(id);
    }

}
