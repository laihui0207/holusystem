package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Company;
import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("companyDao")
public class CompanyDaoHibernate extends GenericDaoHibernate<Company, Long> implements CompanyDao {

    public CompanyDaoHibernate() {
        super(Company.class);
    }
}
