package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.model.CustomGroup;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customGroupDao")
public class CustomGroupDaoHibernate extends GenericDaoHibernate<CustomGroup, Long> implements CustomGroupDao {

    public CustomGroupDaoHibernate() {
        super(CustomGroup.class);
    }

    @Override
    public CustomGroup getGroupByGroupID(String groupID) {
        String hql="From CustomGroup where groupID='"+groupID+"'";
        Query query=getSession().createQuery(hql);
        List<CustomGroup> dataList=query.list();
        if(dataList!=null && dataList.size()>0){
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public List<CustomGroup> getGroupByCompany(String companyID) {
        String hql="From CustomGroup where company.companyId='"+companyID+"'";
        Query query=getSession().createQuery(hql);
        List<CustomGroup> dataList=query.list();
        return dataList;
    }
}
