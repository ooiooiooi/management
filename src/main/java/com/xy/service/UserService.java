package com.xy.service;

import java.util.Map;

public interface UserService {
    String findPassword(String user);

    int registerUser(Map<String,Object> map);

    int findUser(String user);
}
