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
    public static void thumbnail_create(String filePath,String name){
        if(!filePath.endsWith("/")){
            filePath+="/";
        }
        String sourceFile=filePath+name;
        String thumbnailFile=filePath+name.substring(0,name.lastIndexOf("."))+"_smaller"+name.substring(name.lastIndexOf("."));
        File file=new File(thumbnailFile);
        if(!file.exists())
        ImageUtil.compressImage(sourceFile,thumbnailFile,200,200);


        String MidFile=filePath+name.substring(0,name.lastIndexOf("."))+"_mid"+name.substring(name.lastIndexOf("."));
        File midFile=new File(MidFile);
        if(!midFile.exists())
        ImageUtil.compressImage(sourceFile,MidFile,360,200);
    }
    public static String handleThumbnail(String content,ServletContext context){
        String thumbnailURL=null;
        String midImageUrl=null;
        String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m=r.matcher(content);
        String attacheDir= SteelConfig.getConfigure(SteelConfig.EditorAttachedDirectory);
        if(null==attacheDir || attacheDir.length()==0){
            attacheDir=context.getRealPath("/");
        }
        if(!attacheDir.endsWith("/")){
            attacheDir+="/";
        }
        while(m.find()){
            String imgUrl=m.group(1);
            if(imgUrl.indexOf("attached")<0){
                imgUrl=download(imgUrl,attacheDir+"/attached/");
            }
            if(imgUrl==null || imgUrl.equals("")){
                continue;
            }
            String fileUrl=attacheDir+imgUrl.substring(imgUrl.indexOf("/attached"));
            //to do  check if need create thumbnail
            thumbnail_create(fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1),
                    fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
            thumbnailURL=imgUrl.substring(imgUrl.indexOf("/attached"),imgUrl.lastIndexOf("/")+1)+imgUrl.substring(imgUrl.lastIndexOf("/")+1,
                    imgUrl.lastIndexOf("."))+"_smaller"+imgUrl.substring(imgUrl.lastIndexOf("."));
            break;
        }
        if(thumbnailURL==null || thumbnailURL.equalsIgnoreCase("")){
            thumbnailURL="/attached/holu_default.jpg";
        }
        return thumbnailURL;
    }
    public static String download(String imageUrl,String storePath){
        int byteRead = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        storePath+="/image/"+df.format(new Date())+"/";

        String returnUrl="";
        try {
            File storeDir=new File(storePath);
            if(!storeDir.exists()){
                storeDir.mkdirs();
            }
            URL url=new URL(imageUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            String fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1);
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
