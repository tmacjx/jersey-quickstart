package com.bokecc.service.impl;


import com.bokecc.mapper.UserMapper;
import com.bokecc.model.User;
import com.bokecc.service.IuserService;
import lombok.extern.slf4j.Slf4j;
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
    public List<User> selectList(Set<String> userIds) {
        return null;
    }

    @Override
    public int delete(String id) {
        return mapper.deleteById(id);
    }

    @Override
    public User selectById(String userId) {
        return mapper.selectById(userId);
    }

    @Override
    public int updateById(String userId) {
        User user = mapper.selectById(userId);
        return 0;
    }
}
