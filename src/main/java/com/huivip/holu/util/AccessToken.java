package com.huivip.holu.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sunlaihui on 8/15/15.
 */
public class AccessToken {
    public static String createAccessToken(String key){
        String token="";
        Base64 base64=new Base64();
        try {
            MessageDigest md5=MessageDigest.getInstance("md5");
            key+="~holu";
            token= base64.encodeToString(covertToString(md5.digest(key.getBytes("utf-8"))).getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }
    public static boolean matches(String key,String token){

        return createAccessToken(key).equals(token);
    }
    public static String covertToString(byte[] md5Bytes){
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    public static void  main(String[] args){
        String token=AccessToken.createAccessToken("admin");
        System.out.println(token);

        System.out.println(AccessToken.matches("admin",token));
    }
}
