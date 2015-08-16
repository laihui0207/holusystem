package com.huivip.holu.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sunlaihui on 8/12/15.
 */
public class HLPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encodeString="";
        Base64 base64=new Base64();
        try {
            MessageDigest md5=MessageDigest.getInstance("md5");
            byte[] md5encode=md5.digest(charSequence.toString().getBytes("utf-8"));
            encodeString=base64.encodeToString(md5encode);
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
}
