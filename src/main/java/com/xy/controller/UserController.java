package com.xy.controller;

import com.xy.service.UserService;
import com.xy.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    /*
````     user_type    0 游客登陆 1微信登陆 2qq登陆 3自定义注册账号
     */
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserAndPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult getUserAndPassword() {
        CommonResult commonResult = new CommonResult();
        Map<String, Object> map = new HashMap<>();
        String user = "yk" + getFixLenthString(6);
        String password = getFixLenthString(6);
        String token = UUIDUtils.GetUUID();
        String sign = MD5.sign(password, Params.MD5_KEY, Params.CHARSET);
        map.put("user", user);
 /*       map.put("token", token);*/
        map.put("user_type", 0);
        commonResult.setData(map);
        map.put("id",UUIDUtils.GetUUID());
        map.put("password",sign);
        map.put("create_time", new Date().getTime());
        map.put("update_time", new Date().getTime());
        int i = userService.registerUser(map);
        commonResult.setStatus(CommonResult.OK);
        commonResult.setTips("账号获取成功");
        return commonResult;
    }

    private static String getFixLenthString(int strLength) {

        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     *
     * @param user 用户名
     * @param password 密码
     * @return
     */

    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult UserLogin(@RequestParam("user") String user,@RequestParam String password) {
        CommonResult commonResult = new CommonResult();
        try {
            String passwordData = userService.findPassword(user);
            boolean verify = MD5.verify(password, passwordData, Params.MD5_KEY, Params.CHARSET);
            if (verify) {
                commonResult.setTips("登陆成功");
                commonResult.setStatus(CommonResult.OK);
            } else {
                commonResult.setTips("账号或者密码错误");
                commonResult.setStatus(CommonResult.FAIL);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return commonResult;
    }

    /**
     * 账号注册
     * @param user 用户
     * @param password 密码
     * @param user_type 0 游客登陆 1微信登陆 2qq登陆 3自定义注册账号
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "/register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public CommonResult UserRegister(@RequestParam("user") String user, @RequestParam String password, @RequestParam Integer user_type, HttpServletRequest request) {
        CommonResult commonResult = new CommonResult();
        try {
            String sign = MD5.sign(password, Params.MD5_KEY, Params.CHARSET);
            Map<String, Object> map = ParamsToMap.getMap(request);
            map.put("id",UUIDUtils.GetUUID());
            map.put("password",sign);
            map.put("create_time", new Date().getTime());
            map.put("update_time", new Date().getTime());
            //判断账号是否被注册了
               int userNum = userService.findUser(user);
               if (userNum>0){
                   commonResult.setStatus(CommonResult.FAIL);
                   commonResult.setTips("此账号已近被注册");
                   return commonResult;
               }
            int i = userService.registerUser(map);

            if (i > 0) {
                commonResult.setStatus(CommonResult.OK);
                commonResult.setTips("账号注册成功");
            }else {
                commonResult.setStatus(CommonResult.FAIL);
                commonResult.setTips("账号注册失败");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return commonResult;
    }


}
