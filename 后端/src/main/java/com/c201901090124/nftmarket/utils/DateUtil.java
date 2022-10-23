package com.c201901090124.nftmarket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 小小怪
 * @title DateUtil
 * @create 2022/9/12 10:01
 **/
public class DateUtil {
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
