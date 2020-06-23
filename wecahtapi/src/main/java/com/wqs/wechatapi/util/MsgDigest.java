package com.wqs.wechatapi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成消息签名
 */
public class MsgDigest {
    public static String digest(String str,String algorithm){

        try {
            MessageDigest sd = MessageDigest.getInstance(algorithm);
            sd.update(str.getBytes());
            byte[] digest = sd.digest();
            /*
             * 变为16进制，使用字符串进行拼接
             * */
            StringBuilder res = new StringBuilder();
            for (byte b : digest) {
                res.append(String.format("%02X", b));
            }
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String digest = MsgDigest.digest("1111111", "sha-1");
        System.out.println(digest);

        System.out.println(MsgDigest.digest("1111111", "sha-1"));
    }

}
