package com.huivip.holu.util.cache;

import org.cache2k.Cache;
import org.cache2k.CacheBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunlaihui on 11/26/15.
 */
public class Cache2kProvider {

    Map<String,Cache> caches=new HashMap<>();

    private Cache2kProvider(){}
    private static final class CacheProviderFactoryHolder {
        private static Cache2kProvider theSingleton = new Cache2kProvider();

        public static Cache2kProvider getSingleton() {
            return theSingleton;
        }

        // following prevents accidental instantiation
        private CacheProviderFactoryHolder() {
        }
    }
    public static Cache2kProvider getinstance(){
        return CacheProviderFactoryHolder.getSingleton();
    }

    public Cache getCache(String className){
        if(!caches.containsKey(className)){
            Cache<String,Object> cache= CacheBuilder.newCache(String.class,Object.class).build();
            caches.put(className,cache);
        }
        return caches.get(className);
    }

}
