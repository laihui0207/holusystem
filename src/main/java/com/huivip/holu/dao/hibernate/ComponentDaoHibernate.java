package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("componentDao")
public class ComponentDaoHibernate extends GenericDaoHibernate<Component, Long> implements ComponentDao {

    public ComponentDaoHibernate() {
        super(Component.class);
    }

    @Override
    public List<Component> listComponentByProject(String projectID,String tableName,ExtendedPaginatedList list) {

        /*String queryString="From Component where project.projectID='"+projectID+"'";
        Query query=getSession().createQuery(queryString);
        return query.list();*/
        String sql="select * from "+tableName+ "  where projectID='"+projectID+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Component.class);
        if(list!=null){
            List<Component> totalList=query.list();
            list.setTotalNumberOfRows(totalList.size());
            query.setFirstResult(list.getFirstRecordIndex());
            query.setMaxResults(list.getPageSize());
        }
        List<Component> dataList=query.list();
        if(list!=null){
            list.setList(dataList);
        }
        return dataList;
    }

    @Override
    public Component getComponentByComponentID(String componentID,String tableName) {
        String sql="select * from "+tableName+ "  where ComponentID='"+componentID+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Component.class);
        List<Component> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> listComponentIDsOfProject(String projectID, String tableName) {
        String sql="select ComponentID from "+tableName+" where ProjectID='"+projectID+"'";
        Query query=getSession().createSQLQuery(sql);
        List<String> list=query.list();
        return list;
    }
}
