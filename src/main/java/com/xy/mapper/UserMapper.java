package com.xy.mapper;

import java.util.Map;

public interface UserMapper {

     String findPassword(String user);

    int registerUser(Map<String,Object> map);

    int findUser(String user);
}
