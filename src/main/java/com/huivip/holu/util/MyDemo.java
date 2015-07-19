package com.huivip.holu.util;

/**
 * Created by sunlaihui on 7/15/15.
 */
public class MyDemo {
    public static void main(String[] args){
        String fileUrl="https://login-ha1.qa.webex.com/cas/auth.do";
        System.out.println(fileUrl.substring(0, fileUrl.lastIndexOf("/")));
    }
}
