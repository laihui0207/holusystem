package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.model.Company;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyDao")
public class CompanyDaoHibernate extends GenericDaoHibernate<Company, Long> implements CompanyDao {

    public CompanyDaoHibernate() {
        super(Company.class);
    }

    @Override
    public Company getCompanyByCompanyID(String companyID) {
        String hqlString="From Company where companyId='"+companyID+"'";
        Query query=getSession().createQuery(hqlString);
        List<Company> companyList=query.list();
        if(null==companyList || companyList.size()==0){
            return null;
        }
        return companyList.get(0);
    }
}
