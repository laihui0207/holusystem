package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Documentation;
import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("documentationDao")
public class DocumentationDaoHibernate extends GenericDaoHibernate<Documentation, Long> implements DocumentationDao {

    public DocumentationDaoHibernate() {
        super(Documentation.class);
    }
}
