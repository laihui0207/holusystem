package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("subComponentListDao")
public class SubComponentListDaoHibernate extends GenericDaoHibernate<SubComponentList, Long> implements SubComponentListDao {

    public SubComponentListDaoHibernate() {
        super(SubComponentList.class);
    }

    @Override
    public List<SubComponentList> getSubComponentListByComponentID(String componentID, String tableName,ExtendedPaginatedList list) {
        String sql="select * from "+tableName+" where ComponentID='"+componentID+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(tableName,SubComponentList.class);
        if(list!=null){
            List<SubComponentList> totalList=query.list();
            query.setMaxResults(list.getPageSize());
            query.setFirstResult(list.getFirstRecordIndex());
            list.setTotalNumberOfRows(totalList.size());
        }
        List<SubComponentList> dataList=query.list();
        if(null!=list){
            list.setList(dataList);
        }
        return dataList;
    }

    @Override
    public SubComponentList getSubComponentBySubComponentID(String subComponentID, String tableName) {
        String sql="select * from "+tableName+" where SubComponentID='"+subComponentID+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(SubComponentList.class);
        List<SubComponentList> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public String getParentComponentId(String SubComponentId, String tableName) {
        String sql="select ComponentID from "+tableName+" where SubComponentID='"+SubComponentId+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        List list=query.list();
        if(list!=null && list.size()>0){
            return (String) list.get(0);
        }
        return null;
    }
}
