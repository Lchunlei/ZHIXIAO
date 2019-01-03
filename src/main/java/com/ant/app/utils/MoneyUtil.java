package com.ant.app.utils;

import java.util.regex.Pattern;

/**
 * Created by sfb_liuchunlei on 2018/10/19 0029.
 */
public class MoneyUtil {

    //将字符串分，转为单位是元的字符串
    public static String FenTurnYuan(String fenNum){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        String yuanNum;
        if(pattern.matcher(fenNum).matches()){
            if(fenNum==null){
                return null;
            }else if(fenNum.length()==1){
                yuanNum="0.0"+fenNum;
            }else if(fenNum.length()==2){
                yuanNum="0."+fenNum;
            }else {
                yuanNum=fenNum.substring(0,fenNum.length()-2)+"."+fenNum.substring(fenNum.length()-2,fenNum.length());
            }
        }else {
            yuanNum="0.00";
        }
        return yuanNum;
    }
    //将字符串，转为单位是分的字符串
    public static String yuanTurnFen(String yuanNum){
        int index = yuanNum.indexOf(".");
        int length = yuanNum.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(yuanNum+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((yuanNum.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((yuanNum.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((yuanNum.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }
}
