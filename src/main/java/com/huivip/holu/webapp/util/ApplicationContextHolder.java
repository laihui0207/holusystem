package com.huivip.holu.webapp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: sunlaihui
 * Date: 13-5-18
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static Log log = LogFactory.getLog(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext;

    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (this.applicationContext != null) {
            throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = context;
        log.info("holded applicationContext,displayName:" + applicationContext.getDisplayName());
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public static void cleanHolder() {
        applicationContext = null;
    }
}
