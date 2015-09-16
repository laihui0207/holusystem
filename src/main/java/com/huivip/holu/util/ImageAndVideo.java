package com.huivip.holu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunlaihui on 9/13/15.
 */
public class ImageAndVideo {

    public static String handleImage(String content,String contextPath){
        String newContent="";
       // String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        String imgRegex="(<img.*?(?: |\\t|\\r|\\n)?src=['\\\"]?)(.+?)(['\\\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>)";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m=r.matcher(content);
        while(m.find()){
            String totalString=m.group(0);
            String preString=m.group(1);
            String imageUrl=m.group(2);
            String postString=m.group(3);

            System.out.println(preString);
            System.out.println(imageUrl);
            System.out.println(postString);

            imageUrl=contextPath+imageUrl;

            totalString=preString+imageUrl+postString;

            System.out.println(totalString);

            String newAttribute=" ng-click=\"show('"+imageUrl+"')\"";
            totalString=totalString.substring(0,totalString.lastIndexOf("/>"))+newAttribute+ totalString.substring(totalString.lastIndexOf("/>"));

            System.out.println(totalString);
            newContent=totalString;
        }
        return newContent;

    }
    public static String handleVideo(String content,String contextPath,String clientType){
        String newContent=content;

        return newContent;

    }

    public static void main(String[] args){
        ImageAndVideo.handleImage("<p>\n" +
                "\tsdfasf\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<img src=\"/holusystem/attached/image/20150823/BEF39F94B19FFC31476B8362FA863186.jpg\" width=\"200\" height=\"200\" alt=\"\" />\n" +
                "</p>","http://localhost:8087");
    }
}
