package com.bokecc.service;
import com.bokecc.model.User;
import java.util.List;
import java.util.Set;

public interface IuserService {

    List<User> selectList(Set<String> userIds);

    int delete(String id);

    User selectById(String userId);

    int updateById(String userId);


}
