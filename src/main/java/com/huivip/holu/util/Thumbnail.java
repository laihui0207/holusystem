package com.huivip.holu.util;


import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunlaihui on 6/9/15.
 */
public class Thumbnail {
    static String attacheDir= SteelConfig.getConfigure(SteelConfig.EditorAttachedDirectory);
    static String smallerPlayImage=attacheDir+"/attached/play_smaller.png";
    static String midPlayerImage=attacheDir+"/attached/play_mid.png";
    public static void thumbnail_create(String filePath,String name,boolean mergePlayImage){
        if(!filePath.endsWith("/")){
            filePath+="/";
        }
        String sourceFile=filePath+name;
        String thumbnailFile=filePath+name.substring(0,name.lastIndexOf("."))+"_smaller"+name.substring(name.lastIndexOf("."));
        File file=new File(thumbnailFile);
        if(!file.exists())
        ImageUtil.compressImage(sourceFile,thumbnailFile,200,200);
        if(mergePlayImage){
            ImageUtil.mergeImage(thumbnailFile,smallerPlayImage);
        }


        String MidFile=filePath+name.substring(0,name.lastIndexOf("."))+"_mid"+name.substring(name.lastIndexOf("."));
        File midFile=new File(MidFile);
        if(!midFile.exists())
        ImageUtil.compressImage(sourceFile,MidFile,720,1280);
        if(mergePlayImage){
            ImageUtil.mergeImage(MidFile,midPlayerImage);
        }

       /* String bigFile=filePath+name.substring(0,name.lastIndexOf("."))+"_big"+name.substring(name.lastIndexOf("."));
        File hugeFile=new File(bigFile);
        if(!hugeFile.exists())
            ImageUtil.compressImage(sourceFile,bigFile,720,1280);*/
    }
    public static String handleThumbnail(String content,ServletContext context){
        String thumbnailURL=null;
        String midImageUrl=null;


        if(null==attacheDir || attacheDir.length()==0){
            attacheDir=context.getRealPath("/");
        }
        if(!attacheDir.endsWith("/")){
            attacheDir+="/";
        }

        String videoRegex="(<embed.*flashvars=\"f=(.*)?\"\\stype=\".*/>)";
        Pattern videoPattern=Pattern.compile(videoRegex);
        Matcher videoMathcher=videoPattern.matcher(content);

        while(videoMathcher.find()){
            String videoUrl=videoMathcher.group(2);

            String fileUrl=attacheDir+videoUrl.substring(videoUrl.indexOf("/attached"));
            File file=new File(fileUrl);
            if(!file.exists()){
                continue;
            }
            String videoThumbnail=attacheDir+"/attached/image/"+fileUrl.substring(fileUrl.lastIndexOf("/")+1,fileUrl.lastIndexOf("."))+".png";
            VideoUtil.compressVideo(fileUrl);
            VideoUtil.videoThumbnail(fileUrl,videoThumbnail);

            File imageFile=new File(videoThumbnail);
            if(!imageFile.exists()){
                continue;
            }
            thumbnail_create(videoThumbnail.substring(0, videoThumbnail.lastIndexOf("/") + 1),
                    videoThumbnail.substring(videoThumbnail.lastIndexOf("/") + 1),true);

            thumbnailURL=videoThumbnail.substring(videoThumbnail.indexOf("/attached"),videoThumbnail.lastIndexOf("/")+1)+videoThumbnail.substring(videoThumbnail.lastIndexOf("/")+1,
                    videoThumbnail.lastIndexOf("."))+"_smaller"+videoThumbnail.substring(videoThumbnail.lastIndexOf("."));
        }
        if(thumbnailURL!=null && !thumbnailURL.equalsIgnoreCase("")) return thumbnailURL;

        String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m=r.matcher(content);

        while(m.find()){
            String imgUrl=m.group(1);
            if(imgUrl.indexOf("attached")<0){
                imgUrl=download(imgUrl,attacheDir+"/attached/","image" );
            }
            if(imgUrl==null || imgUrl.equals("")){
                continue;
            }
            String fileUrl=attacheDir+imgUrl.substring(imgUrl.indexOf("/attached"));
            File orignalImage=new File(fileUrl);
            if(orignalImage.length()< 2000){
                continue;
            }
            //to do  check if need create thumbnail
            thumbnail_create(fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1),
                    fileUrl.substring(fileUrl.lastIndexOf("/") + 1),false);


            thumbnailURL=imgUrl.substring(imgUrl.indexOf("/attached"),imgUrl.lastIndexOf("/")+1)+imgUrl.substring(imgUrl.lastIndexOf("/")+1,
                    imgUrl.lastIndexOf("."))+"_smaller"+imgUrl.substring(imgUrl.lastIndexOf("."));

            break;
        }
        if(thumbnailURL==null || thumbnailURL.equalsIgnoreCase("")){
            thumbnailURL="/attached/holu_default.jpg";
        }
        return thumbnailURL;
    }
    public static String download(String sourceUrl, String storePath, String type){
        int byteRead = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        storePath+="/"+type+"/"+df.format(new Date())+"/";

        String returnUrl="";
        try {
            File storeDir=new File(storePath);
            if(!storeDir.exists()){
                storeDir.mkdirs();
            }
            URL url=new URL(sourceUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            //String fileName=sourceUrl.substring(sourceUrl.lastIndexOf("/")+1);
            String fileName= sourceUrl.hashCode()+"";
            if(type.equalsIgnoreCase("image")){
                fileName+=".jpg";
            }
            else {

            }
            FileOutputStream fs = new FileOutputStream(storePath+fileName);

            byte[] buffer = new byte[1204];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            returnUrl=storePath+fileName;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnUrl;
    }
    public static void main(String[] args){
        String fileUrl="https://login-ha1.qa.webex.com/cas/auth.do";
        System.out.println(fileUrl.substring(0, fileUrl.lastIndexOf("/")));
    }
}
