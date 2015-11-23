package com.huivip.holu.util;

import java.io.File;

/**
 * Created by sunlaihui on 11/23/15.
 */
public class VideoUtil {
    static String ffmpeg=SteelConfig.getConfigure(SteelConfig.VideoConvertTool);

    public static void compressVideo(String source){
        // ffmpeg
        String dest=source+".mp4";
        String command="";
        if(ffmpeg.indexOf(".exe")>0){
            command="cmd /c "+ffmpeg+" -i "+ source +" "+dest;
        }
        else {
            command=ffmpeg+" -i "+ source +" "+dest;
        }
        runCommand(command);
        File sourceFile=new File(source);
        File destFile=new File(dest);
        if(destFile.exists()){
            sourceFile.delete();
            destFile.renameTo(sourceFile);
        }
    }
    public static void videoThumbnail(String video,String image){
        // ffmpeg -i "test.avi" -y -f image2 -ss 8 -t 0.001 -s 350x240 'test.jpg'
        String command="";
        if(ffmpeg.indexOf(".exe")>0){
            command="cmd /c "+ffmpeg+" -i "+video+" -y -f image2 -ss 8 -t 0.001 -s 350x240 "+image;
        }
        else {
            command=ffmpeg+" -i "+video+" -y -f image2 -ss 8 -t 0.001 -s 350x240 "+image;
        }
        runCommand(command);

    }

    public static void runCommand(String command){
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
       // String command = "ping -c 3 baidu.com";
        ///VideoUtil.runCommand(command);
        VideoUtil.compressVideo("/Users/sunlaihui/Downloads/VID_20151113_000158.mp4");
    }
}
