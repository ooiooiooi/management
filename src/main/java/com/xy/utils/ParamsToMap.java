package com.xy.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamsToMap {
    public static Map getMap(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        // 遍历
        Map paramMap = new HashMap();
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            // key值
            Object strKey = element.getKey();
            // value,数组形式
            String[] value = (String[]) element.getValue();
            for (int i = 0; i < value.length; i++) {
                paramMap.put(strKey.toString(),value[i]);
            }
        }

        return paramMap;
    }
    }

        

