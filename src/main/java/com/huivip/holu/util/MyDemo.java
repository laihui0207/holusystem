package com.huivip.holu.util;

import java.text.SimpleDateFormat;

/**
 * Created by sunlaihui on 7/15/15.
 */
public class MyDemo {
    public static void main(String[] args){
      /*  String fileUrl="https://login-ha1.qa.webex.com/cas/auth.do";
        System.out.println(fileUrl.substring(0, fileUrl.lastIndexOf("/")));
        String access_token="#2a$YH001%uufj9meNDpvhHP4UCQ3HQg==";
        String userID=access_token.substring(access_token.indexOf("$")+1,access_token.indexOf("%"));
        String token=access_token.substring(access_token.indexOf("%")+1);
        System.out.println(userID);
        System.out.println(token);*/
        SimpleDateFormat sdf=new SimpleDateFormat("ddssSSS");
        String userID=sdf.format(System.currentTimeMillis());
        System.out.println(userID);
    }
}
