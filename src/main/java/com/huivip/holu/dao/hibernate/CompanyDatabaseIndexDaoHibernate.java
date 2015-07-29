package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.CompanyDatabaseIndex;
import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("companyDatabaseIndexDao")
public class CompanyDatabaseIndexDaoHibernate extends GenericDaoHibernate<CompanyDatabaseIndex, Long> implements CompanyDatabaseIndexDao {

    public CompanyDatabaseIndexDaoHibernate() {
        super(CompanyDatabaseIndex.class);
    }
}
