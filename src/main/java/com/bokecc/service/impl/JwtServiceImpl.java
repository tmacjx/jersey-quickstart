//package com.bokecc.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.mapper.Condition;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.bokecc.constant.CacheMsgType;
//import com.bokecc.constant.Constant;
//import com.bokecc.entity.ErrorCode;
//import com.bokecc.entity.ResponseParam;
//import com.bokecc.event.CacheUpdateEvent;
//import com.bokecc.mapper.UserJwtKeyMapper;
//import com.bokecc.model.UserJwtKey;
//import com.bokecc.param.JwtParam;
//import com.bokecc.service.IJwtService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author: Faizel Lannister(chufuying)
// * @date: 2020/5/28
// * @version: 0.0.1
// * @jdk: 1.8
// * @email: chufuying@163.com
// **/
//
//@Slf4j
//@Service
//public class JwtServiceImpl implements IJwtService {
//
//    @Autowired
//    private UserJwtKeyMapper jwtKeyMapper;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    @Override
//    public List<UserJwtKey> findAll() {
//
//        return jwtKeyMapper.selectList(Condition.empty());
//    }
//
//    @Override
//    public ResponseParam<Integer> insert(JwtParam param) {
//
//        UserJwtKey userJwtKey = new UserJwtKey();
//        userJwtKey.setAppKey(param.getAppKey());
//        userJwtKey.setAppSecret(param.getAppSecret());
//        userJwtKey.setDepartment(param.getDepartment());
//        userJwtKey.setServiceName(param.getServiceName());
//        userJwtKey.setRemark(param.getRemark());
//
//        try {
//
//            int re = jwtKeyMapper.insert(userJwtKey);
//
//            if (re > 0) {
//
//                publishEvent(userJwtKey.getAppKey());
//
//                return ResponseParam.build(Integer.class).errorCode(ErrorCode.OK).res(re);
//            }
//
//            return ResponseParam.build(Integer.class).errorCode(ErrorCode.DB_EXCEPTION).res(re);
//
//        }catch (DuplicateKeyException e){
//
//            log.error("屏蔽主键重复异常");
//        }
//
//        return ResponseParam.build(Integer.class).errorCode(ErrorCode.JWT_INSERT_FAILURE).res(0);
//    }
//
//    @Override
//    public int update(JwtParam param) {
//
//        UserJwtKey userJwtKey = new UserJwtKey();
//        userJwtKey.setAppKey(param.getAppKey());
//        userJwtKey.setAppSecret(param.getAppSecret());
//        userJwtKey.setDepartment(param.getDepartment());
//        userJwtKey.setServiceName(param.getServiceName());
//        userJwtKey.setRemark(param.getRemark());
//
//        EntityWrapper<UserJwtKey> entityWrapper = Condition.wrapper();
//
//        entityWrapper.eq("app_key", param.getAppKey());
//
//        int re = jwtKeyMapper.update(userJwtKey, entityWrapper);
//
//        if(re > 0){
//
//            publishEvent(param.getAppKey());
//        }
//
//        return re;
//    }
//
//    @Override
//    public int delete(String jwtKey) {
//
//        EntityWrapper<UserJwtKey> entityWrapper = Condition.wrapper();
//
//        entityWrapper.eq("app_key", jwtKey);
//
//        int re = jwtKeyMapper.delete(entityWrapper);
//
//        if (re > 0){
//
//            publishEvent(jwtKey);
//        }
//
//        return re;
//    }
//
////    private void publishEvent(String jwtKey){
////
////        JSONObject jsonObject = new JSONObject();
////
////        jsonObject.put("jwt_key", jwtKey);
////
////        jsonObject.put(Constant.CACHE_TYPE_TAG, CacheMsgType.UPDATE_JWT_INFO);
////
////        CacheUpdateEvent event = new CacheUpdateEvent(new Object(), jsonObject);
////
////        publisher.publishEvent(event);
////    }
//}
