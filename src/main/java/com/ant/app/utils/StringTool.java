package com.ant.app.utils;

/**
 * @author lchunlei
 * @date 2018/9/27
 */
public class StringTool {

    public static boolean isRealStr(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return true;
            }
        }
        return false;
    }

}
