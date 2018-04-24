package com.xy.service.impl;

import com.xy.mapper.UserMapper;
import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String findPassword(String user) {
        return userMapper.findPassword(user);
    }

    @Override
    public int registerUser(Map<String,Object> map) {
        return userMapper.registerUser(map);
    }

    @Override
    public int findUser(String user) {
        return userMapper.findUser(user);
    }
}
