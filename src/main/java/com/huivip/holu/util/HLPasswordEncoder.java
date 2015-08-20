package com.huivip.holu.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HLPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encodeString="";
        Base64 base64=new Base64();
        try {
            MessageDigest md5=MessageDigest.getInstance("md5");
            byte[] md5encode=md5.digest(charSequence.toString().getBytes("utf-8"));
            encodeString=convertToHexString(md5encode);
            //encodeString=base64.encodeToString(md5encode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeString;
    }
    String convertToHexString(byte data[]) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {
        return encodePassword.equals(encode(charSequence));
    }
    public static void main(String[] args){
        HLPasswordEncoder encoder=new HLPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}