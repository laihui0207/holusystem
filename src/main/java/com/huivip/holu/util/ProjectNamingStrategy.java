package com.huivip.holu.util;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Created by sunlaihui on 7/28/15.
 */
public class ProjectNamingStrategy extends DefaultNamingStrategy {
    public static ProjectNamingStrategy Instance=new ProjectNamingStrategy();
    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName);
    }
}
