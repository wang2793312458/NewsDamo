package com.feicui.news.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密处理
 * Created by AAAAA on 2016/6/13.
 */
public class MD5utils {
    public static String encode(String password) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = digester.digest(password.getBytes());


            for (byte b : bytes) {
                int c = b & 0xff;
                String hexString = Integer.toHexString(c);
                System.out.println(hexString);
                if(hexString.length() == 1){
                    hexString = 0+hexString ;
                }

                sb.append(hexString);
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String result = sb+"";

        return result;


    }
}
