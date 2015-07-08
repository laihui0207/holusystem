package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentationDao")
public class DocumentationDaoHibernate extends GenericDaoHibernate<Documentation, Long> implements DocumentationDao {

    public DocumentationDaoHibernate() {
        super(Documentation.class);
    }

    @Override
    public List<Documentation> myDocumentations(String userId) {
        String queryString="From Documentation where creater.id="+userId;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}
