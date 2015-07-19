package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.GenericDao;
import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.News;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.huivip.holu.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.huivip.holu.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *         Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    @Resource
    private SessionFactory sessionFactory;
    private Analyzer defaultAnalyzer;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_36);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getAllPagable(null);
    }

    @Override
    public List<T> getAllPagable(ExtendedPaginatedList list) {
        Session sess = getSession();
        Criteria criteria=sess.createCriteria(persistentClass);
        if(list!=null){
            criteria.setFirstResult(list.getFirstRecordIndex());
            criteria.setMaxResults(list.getPageSize());
            if (list.getSortCriterion() != null) {
                if (list.getSortDirection().equals(SortOrderEnum.ASCENDING)) {
                    criteria.addOrder(Order.asc(list.getSortCriterion()));
                }
                if (list.getSortDirection().equals(SortOrderEnum.DESCENDING)) {
                    criteria.addOrder(Order.desc(list.getSortCriterion()));
                }
            }
        }

        List<T> dataList=criteria.list();
        if(null!=list){
            list.setList(dataList);
            list.setTotalNumberOfRows(getAllRecordsCount());
        }
        return dataList;
    }

    @Override
    public int getAllRecordsCount() {
        Criteria criteria=getSession().createCriteria(persistentClass);
        criteria.setProjection(Projections.rowCount());
        Long result= (Long) criteria.list().get(0);
        Integer i = result != null ? result.intValue() : null;
        return i.intValue();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> search(String searchTerm) throws SearchException {
       /* Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,
                this.persistentClass);

        return hibQuery.list();*/
        return search(searchTerm,null);
    }

    @Override
    public List<T> search(String searchTerm, ExtendedPaginatedList list) {
        Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,
                this.persistentClass);
        if(list!=null){
            hibQuery.setFirstResult(list.getFirstRecordIndex());
            hibQuery.setMaxResults(list.getPageSize());
            if (list.getSortCriterion()!= null) {

                //if (list.getSortCriterion().equals(SortOrderEnum.ASCENDING)) {
                    org.apache.lucene.search.Sort sort = new Sort(new SortField(list.getSortCriterion(),SortField.STRING));
                    hibQuery.setSort(sort);
                /*}
                if (list.getSortCriterion().equals(SortOrderEnum.DESCENDING)) {
                }*/
            }
        }
        List<T> dataList=hibQuery.list();
        if(null!=list){
            list.setList(dataList);
            list.setTotalNumberOfRows(hibQuery.getResultSize());
        }
        return dataList;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);
        for (String s : queryParams.keySet()) {
            Object val = queryParams.get(s);
            if (val instanceof Collection) {
                namedQuery.setParameterList(s, (Collection) val);
            } else {
                namedQuery.setParameter(s, val);
            }
        }
        return namedQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        HibernateSearchTools.reindex(persistentClass, getSessionFactory().getCurrentSession());
    }


    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        HibernateSearchTools.reindexAll(async, getSessionFactory().getCurrentSession());
    }
}
