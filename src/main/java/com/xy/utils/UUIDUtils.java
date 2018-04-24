package com.xy.utils;

import java.util.UUID;

public class UUIDUtils {


    public static String GetUUID(){

        UUID uuid = UUID.randomUUID();

        String str = uuid.toString().replace("-","");

        return str;
    }
}
