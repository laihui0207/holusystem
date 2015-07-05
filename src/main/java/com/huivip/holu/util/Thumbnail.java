package com.huivip.holu.util;


/**
 * Created by sunlaihui on 6/9/15.
 */
public class Thumbnail {
    public static void thumbnail_create(String filePath,String name){
        if(!filePath.endsWith("/")){
            filePath+="/";
        }
        String sourceFile=filePath+name;
        String thumbnailFile=filePath+name.substring(0,name.lastIndexOf("."))+"_smaller"+name.substring(name.lastIndexOf("."));
        ImageUtil.compressImage(sourceFile,thumbnailFile,90,120);
    }
    public static void main(String[] args){
        String fileUrl="/holusystem/attached/aa.jpg";
        System.out.println(fileUrl.substring(fileUrl.indexOf("/attached"), fileUrl.lastIndexOf("/") + 1));
    }
}
