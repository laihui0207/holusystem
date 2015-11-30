package com.huivip.holu.util.cache;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by sunlaihui on 11/26/15.
 */
public class Cache2kProvider {
    Logger logger= LoggerFactory.getLogger(this.getClass());

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

    public Cache getCache(Class objectClass){
        String className=objectClass.getName();
        if(!caches.containsKey(className)){
            Cache<String,Object> cache= CacheBuilder.newCache(String.class,objectClass).build();
            caches.put(className,cache);
        }
        return caches.get(className);
    }
    public Cache setCache(Class clazz,Cache cache){
        String className=clazz.getName();
        if(!caches.containsKey(className)){
            caches.put(className,cache);
        }
        else {
            cache=caches.get(className);
        }
        return cache;
    }
    public Cache getListCache(){
        if(!caches.containsKey(ArrayList.class.getName())){
            Cache<String,ArrayList> cache=CacheBuilder.newCache(String.class, ArrayList.class).build();
            caches.put(ArrayList.class.getName(),cache);
        }
        return caches.get(ArrayList.class.getName());
    }
    public Cache getSetCache(){
        if(!caches.containsKey(HashSet.class.getName())){
            Cache<String,HashSet> cache=CacheBuilder.newCache(String.class,HashSet.class).build();
            caches.put(HashSet.class.getName(),cache);
        }
        return caches.get(HashSet.class.getName());
    }
    public Cache getExtendedPaginatedListCache(){
        if(!caches.containsKey(ExtendedPaginatedList.class.getName())){
            Cache<String,ExtendedPaginatedList> cache=CacheBuilder.newCache(String.class,ExtendedPaginatedList.class)
                    .build();
            caches.put(ExtendedPaginatedList.class.getName(),cache);
        }
        return caches.get(ExtendedPaginatedList.class.getName());
    }

}
