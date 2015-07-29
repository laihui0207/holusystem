package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("subComponentListDao")
public class SubComponentListDaoHibernate extends GenericDaoHibernate<SubComponentList, Long> implements SubComponentListDao {

    public SubComponentListDaoHibernate() {
        super(SubComponentList.class);
    }
}
