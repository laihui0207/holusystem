package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.DocType;
import com.huivip.holu.dao.DocTypeDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("docTypeDao")
public class DocTypeDaoHibernate extends GenericDaoHibernate<DocType, Long> implements DocTypeDao {

    public DocTypeDaoHibernate() {
        super(DocType.class);
    }
}
