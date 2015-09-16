package com.huivip.holu.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by sunlaihui on 6/9/15.
 */
public class SteelConfig {
   public static String EditorAttachedDirectory="editorAttachDirectory";
    public static String DocumentManagerDirectory="DocumentationDirectory";
    public static String ClientDirectory="ClientDirectory";
    public static String getConfigure(String key){
        Properties prop = new Properties();
        try {
            prop.load(new ClassPathResource("steel.properties").getInputStream());
        } catch (IOException e) {

        }
        // the directory to upload to
        String configureUploadDir=prop.getProperty(key,"");
        return configureUploadDir;
    }

}
