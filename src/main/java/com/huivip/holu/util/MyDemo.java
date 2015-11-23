package com.huivip.holu.util;

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
        /*SimpleDateFormat sdf=new SimpleDateFormat("ddssSSS");
        String userID=sdf.format(System.currentTimeMillis());
     /*   System.out.println(userID);*//*
      String content="<h1>\t鸿路钢构 | 檩条价格表</h1><p>\t2015/9/2</p><p>\t<br /></p><hr /><p>\t<br /></p><p>\t<img style=\"line-height:25px;background-color:#ffffff;font-family:微软雅黑;max-width:100%;white-space:normal;height:auto !important;color:#3e3e3e;font-size:16px;\" src=\"https://mmbiz.qlogo.cn/mmbiz/zcnjOfDzJxnfqiaMNRM49PPuAA0ZIqKuNBF8mgdTN95QOYVYp2BccV1D4ibhlcwKDZbRIvpVkPj0XLnIXdM4gyQQ/0?wx_fmt=gif\" data-type=\"gif\" _src=\"https://mmbiz.qlogo.cn/mmbiz/zcnjOfDzJxnfqiaMNRM49PPuAA0ZIqKuNBF8mgdTN95QOYVYp2BccV1D4ibhlcwKDZbRIvpVkPj0XLnIXdM4gyQQ/0?wx_fmt=gif\" data-w=\"\" data-ratio=\"0.6679841897233202\" /></p><p>\t<br /></p><p>\t<img style=\"line-height:24px;font-family:sans-serif;max-width:100%;white-space:normal;height:auto !important;color:#76923c;font-size:14px;\" src=\"https://mmbiz.qlogo.cn/mmbiz/zcnjOfDzJxnwHrRWEWd4B51Mzw0GS3gvenXBp5L6icEKuZbibzogiaUsqBuwqicPUvxvS9SbTgftukpkM4eFgKGK8A/0?wx_fmt=jpeg\" data-type=\"jpeg\" _src=\"https://mmbiz.qlogo.cn/mmbiz/zcnjOfDzJxnwHrRWEWd4B51Mzw0GS3gvenXBp5L6icEKuZbibzogiaUsqBuwqicPUvxvS9SbTgftukpkM4eFgKGK8A/0?wx_fmt=jpeg\" data-w=\"\" data-ratio=\"0.9703557312252964\" source=\"upload\" format=\"jpeg\" file_id=\"213371531\" url=\"https://mmbiz.qlogo.cn/mmbiz/zcnjOfDzJxnwHrRWEWd4B51Mzw0GS3gvenXBp5L6icEKuZbibzogiaUsqBuwqicPUvxvS9SbTgftukpkM4eFgKGK8A/0?wx_fmt=jpeg\" data-s=\"300,640\" /></p><section style=\"border-bottom:#1e9be8 3px solid;border-left:#1e9be8 3px solid;padding-bottom:10px;line-height:24px;margin:3px;padding-left:10px;padding-right:10px;color:#3e3e3e;border-top:#1e9be8 3px solid;border-right:#1e9be8 3px solid;padding-top:10px;border-top-right-radius:8px;border-top-left-radius:8px;border-bottom-right-radius:8px;border-bottom-left-radius:8px;\" class=\"wxqq-borderTopColor wxqq-borderRightColor wxqq-borderBottomColor wxqq-borderLeftColor\"><fieldset style=\"box-sizing:border-box;border-bottom:#1e9be8 0px;border-left:#1e9be8 0px;padding-bottom:0px;margin:0px;padding-left:0px;padding-right:0px;clear:both;border-top:#1e9be8 0px;border-right:#1e9be8 0px;padding-top:0px;\">\t<section style=\"border-bottom-color:#8ac1e2;background-color:#1e9be8;border-top-color:#8ac1e2;width:6px;margin-bottom:-2px;height:6px !important;color:#ffffff;border-right-color:#8ac1e2;border-left-color:#8ac1e2;box-shadow:#757575 0px 0px 5px;border-top-right-radius:50%;border-top-left-radius:50%;border-bottom-right-radius:50%;border-bottom-left-radius:50%;\" class=\"wxqq-bg\"></section></fieldset><section style=\"padding-bottom:10px;margin:0px;padding-left:10px;padding-right:10px;padding-top:10px;\" data-style=\"color:rgb(33,33,33);text-align:center;\"><p style=\"text-align:left;\">\t<b>备注：</b>以上为C型钢价格，Z型钢在此基础上加50元/吨，黑带报价不含打孔油漆费。此价格为常规檩条价格，如需双抱焊及焊连接板.切口等价格另议！(因市场变化较快，以当天报价为准）</p></section><fieldset style=\"box-sizing:border-box;border-bottom:#1e9be8 0px;border-left:#1e9be8 0px;padding-bottom:0px;margin:0px;padding-left:0px;padding-right:0px;clear:both;border-top:#1e9be8 0px;border-right:#1e9be8 0px;padding-top:0px;\">\t<section style=\"border-bottom-color:#8ac1e2;background-color:#1e9be8;border-top-color:#8ac1e2;width:6px;margin-bottom:-3px;float:right;height:6px !important;color:#ffffff;border-right-color:#8ac1e2;border-left-color:#8ac1e2;box-shadow:#757575 0px 0px 5px;border-top-right-radius:50%;border-top-left-radius:50%;border-bottom-right-radius:50%;border-bottom-left-radius:50%;\" class=\"wxqq-bg\"></section><section style=\"border-bottom-color:#8ac1e2;background-color:#1e9be8;border-top-color:#8ac1e2;width:6px;margin-bottom:-2px;height:6px !important;color:#ffffff;border-right-color:#8ac1e2;border-left-color:#8ac1e2;box-shadow:#757575 0px 0px 5px;border-top-right-radius:50%;border-top-left-radius:50%;border-bottom-right-radius:50%;border-bottom-left-radius:50%;\" class=\"wxqq-bg\"></section></fieldset></section><p style=\"line-height:normal;font-family:sans-serif;white-space:normal;font-size:16px;\">\t<br /></p><p style=\"line-height:normal;font-family:sans-serif;white-space:normal;font-size:16px;\">\t<br /></p><p>\t<br /></p><p>\t<br /></p>";
      String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
      Pattern r = Pattern.compile(imgRegex);
      Matcher m=r.matcher(content);
      while (m.find()){
        String imaurl=m.group(1);
        System.out.println(imaurl);
      }*/
      /*  File file = QRCode.from("Hello World").file("QRcode");
        File outfile=new File("/Users/sunlaihui/Downloads/qrcode.png");
        try {
            if(!outfile.exists()){
                outfile.createNewFile();
            }
            FileOutputStream fos=new FileOutputStream(outfile);
            QRCode.from("Hello World").to(ImageType.PNG).writeTo(fos);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* Cache<String,DocType> cache= CacheBuilder.newCache(String.class,DocType.class).build();
        DocType value=cache.peek("got");
        assertNull(value);
        DocType docType=new DocType();
        docType.setComment("good");
        cache.put("got",docType);
        value=cache.peek("got");
        System.out.println(value.getComment());*/

        String file1="/Users/sunlaihui/Downloads/back.jpg";
        String file2="/Users/sunlaihui/Downloads/play.png";
        String file3="/Users/sunlaihui/Downloads/merge.png";
        ImageUtil.mergeImage(file1,file2);

    }
}
