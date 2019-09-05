package com.ljw.management.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version ：1.0.0
 * @Author: 郎建伟
 * @Modified By:
 * @Description: MD5工具类
 * @Date: Created in 2019/9/5 18:02
 */
public class MD5Util {

    private MD5Util(){
        // 私用构造主法.因为此类是工具类.
    }

    /**|
     * 对字符串自行2次MD5加密MD5(MD5(s))
     * @param s 需要加密的字符串
     * @return
     */
    public final static String md5x2(String s) {
        return md5(md5(s));
    }

    /**\
     * MD5加密
     * @param s 需要加密的字符串
     * @return
     */
    public final static String md5(String s) {

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**\
     * 使用MD5对两端加密后的密文进行比较
     * @param strOne
     * @param strTwo
     * @return
     */
    public static boolean check(String strOne, String strTwo) {
        if (md5(strOne).equals(strTwo)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        System.out.println(MD5Util.md5("admin"));
        System.out.println(MD5Util.md5("加密"));
        System.out.println(MD5Util.md5("20121lkkfaoisdfO$^#@!221"));
        System.out.println(MD5Util.md5("liangan0923A"));
        System.out.println(MD5Util.check("admin","21232F297A57A5A743894A0E4A801FC3"));
    }
}
