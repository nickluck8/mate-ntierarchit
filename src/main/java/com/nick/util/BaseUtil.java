package com.nick.util;

import java.util.UUID;

public class BaseUtil {
    public static String getUserToken(){
        return UUID.randomUUID().toString();
    }
}
