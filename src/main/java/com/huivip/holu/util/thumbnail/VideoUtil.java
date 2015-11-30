package com.huivip.holu.util.thumbnail;

import com.huivip.holu.util.SteelConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunlaihui on 11/23/15.
 */
public class VideoUtil {
     Logger logger= LoggerFactory.getLogger(this.getClass());
     String ffmpeg= SteelConfig.getConfigure(SteelConfig.VideoConvertTool);
     String toolCommand=VideoUtil.class.getResource("/").getPath()+ffmpeg;

     public void compressVideo(String source){
        // ffmpeg
        String dest=source+".mp4";
        List<String> commandStr=new ArrayList<String>();
        if(ffmpeg.indexOf(".exe")>0){
            toolCommand=toolCommand.substring(1);
        }
         commandStr.add(toolCommand);
         commandStr.add("-i");
         commandStr.add(source);
         commandStr.add(dest);
         logger.info("Run command:"+commandStr.toString());
        runCommand(commandStr);
        File sourceFile=new File(source);
        File destFile=new File(dest);
        if(destFile.exists()){
            sourceFile.delete();
            destFile.renameTo(sourceFile);
        }
    }
    public void compressVideo_thread(String source){
        CompressThread compressThread=new CompressThread();
        compressThread.setSource(source);
        Thread thread=new Thread(compressThread);
        thread.start();
    }
    public  void videoThumbnail(String video,String image){
        // ffmpeg -i "test.avi" -y -f image2 -ss 8 -t 0.001 -s 350x240 'test.jpg'
        List<String> commandStr=new ArrayList<>();
        if(ffmpeg.indexOf(".exe")>0){
            toolCommand=toolCommand.substring(1);
        }
        commandStr.add(toolCommand);
        commandStr.add("-i");
        commandStr.add(video);
        commandStr.add("-y");
        commandStr.add("-f");
        commandStr.add("image2");
        commandStr.add("-ss");
        commandStr.add("8");
        commandStr.add("-t");
        commandStr.add("0.001");
        commandStr.add("-s");
        commandStr.add("350*240");
        commandStr.add(image);
        logger.info("Run command:"+commandStr.toString());
        runCommand(commandStr);

    }

    private void runCommand(List command){
        Process p;
        try {
            //p = Runtime.getRuntime().exec(command);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            p=builder.start();
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedInputStream err = new BufferedInputStream(p.getErrorStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            BufferedReader errBr = new BufferedReader(new InputStreamReader(err));
            String lineStr;
            while ((lineStr = errBr.readLine()) != null)
                System.out.println(lineStr);
            while ((lineStr = inBr.readLine()) != null)
                System.out.println(lineStr);
            //检查命令是否执行失败。
            try {
                if (p.waitFor()!=0) {
                    if(p.exitValue()==1)//p.exitValue()==0表示正常结束，1：非正常结束
                    {
                        System.err.println("命令执行失败!");
                        logger.error("Run command Failed:"+command);
                    }

                }
            }catch (InterruptedException e){
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
       // String command = "ping -c 3 baidu.com";
        ///VideoUtil.runCommand(command);
        VideoUtil videoUtil=new VideoUtil();
        videoUtil.compressVideo_thread("/Users/sunlaihui/Downloads/VID_20151113_000158.mp4");
    }

    public class CompressThread implements Runnable{
        private String source;
        @Override
        public void run() {
            VideoUtil videoUtil=new VideoUtil();
            videoUtil.compressVideo(source);
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
