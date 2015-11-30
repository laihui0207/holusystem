package com.huivip.holu.service.impl;

import com.huivip.holu.dao.GenericDao;
import com.huivip.holu.service.GenericManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cache2k.Cache;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.huivip.holu.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.huivip.holu.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.huivip.holu.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.huivip.holu.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.huivip.holu.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.huivip.holu.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Updated by jgarcia: added full text search + reindexing
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    Cache<String,ExtendedPaginatedList> listCache= Cache2kProvider.getinstance().getExtendedPaginatedListCache();
    Cache<String,Set<String>> keyCache=Cache2kProvider.getinstance().getSetCache();
    /*Cache<String,User> userCache=Cache2kProvider.getinstance().setCache(User.class,
            CacheBuilder.newCache(String.class,User.class).build());*/
    Cache<String,String> tableCache= Cache2kProvider.getinstance().getStringCache();
    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;


    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public List<T> getAllPageable(ExtendedPaginatedList list) {
        return dao.getAllPagable(list);
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {

        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Search implementation using Hibernate Search.
     */
    @SuppressWarnings("unchecked")
    public List<T> search(String q, Class clazz) {
        if (q == null || "".equals(q.trim())) {
            return getAll();
        }

        return dao.search(q);
    }

    @Override
    public List<T> search(String searchTerm, Class clazz, ExtendedPaginatedList list) {
        if(searchTerm==null || "".equals(searchTerm.trim())){
            dao.getAllPagable(list);
            return list.getList();
        }
        dao.search(searchTerm,list);
        return list.getList();
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        dao.reindex();
    }

    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        dao.reindexAll(async);
    }

    public void maintainCacheKey(String keyKey,String cacheKey){
        if(!keyCache.contains(keyKey)){
            Set<String> keys=new HashSet<>();
            keys.add(cacheKey);
            keyCache.put(keyKey,keys);
        }
        else {
            Set<String> keys=keyCache.get(keyKey);
            if(!keys.contains(cacheKey)){
                keys.add(cacheKey);
                keyCache.put(keyKey,keys);
            }
        }
    }
    public void removeCacheByKeyPrefix(String keyKeys,String keyPrefix){
        if(keyCache.contains(keyKeys)){
            Set<String> cacheKeys=keyCache.get(keyKeys);
            for(String k:cacheKeys){
                if(k.startsWith(keyPrefix)){
                    listCache.remove(k);
                }
            }
        }
    }
}
