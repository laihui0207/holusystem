package com.huivip.holu.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HLPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encodeString="";
        try {
            MessageDigest md5=MessageDigest.getInstance("md5");
            byte[] md5encode=md5.digest(charSequence.toString().getBytes("utf-8"));
            encodeString=ConvertUtil.covertToString(md5encode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeString;
    }
    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {
        return encodePassword.equals(encode(charSequence));
    }
    public static void main(String[] args){
        HLPasswordEncoder encoder=new HLPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.matches("123456","E10ADC3949BA59ABBE56E057F20F883E"));
    }
}
