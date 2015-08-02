package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.model.CompanyDatabaseIndex;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyDatabaseIndexDao")
public class CompanyDatabaseIndexDaoHibernate extends GenericDaoHibernate<CompanyDatabaseIndex, Long> implements CompanyDatabaseIndexDao {

    public CompanyDatabaseIndexDaoHibernate() {
        super(CompanyDatabaseIndex.class);
    }

    @Override
    public String getTableNameByCompanyAndTableStyle(String companyId, String tableStyle) {
        String hqlString="From CompanyDatabaseIndex where company.companyId='"+companyId+"' and tableStyle='"+tableStyle+"'";
        Query query=getSession().createQuery(hqlString);
        List<CompanyDatabaseIndex> list=query.list();
        if(list!=null && list.size()>0){
            return list.get(0).getTableName();
        }
        return null;
    }
}
