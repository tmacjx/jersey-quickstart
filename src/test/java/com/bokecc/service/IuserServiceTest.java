package com.bokecc.service;

import com.bokecc.model.User;
import com.bokecc.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class IuserServiceTest {

    @Autowired
    private IuserService userService;

    @Test
    void selectAll() {
    }

    @Test
    void selectById() {
        long id = 1;
        User user = userService.selectById(String.valueOf(id));
        Assert.assertEquals((long) user.getId(), id);
    }

    @Test
    void updateOne() {
    }

    @Test
    void insertOne() {
    }

    @Test
    void deleteOne() {
    }

}

