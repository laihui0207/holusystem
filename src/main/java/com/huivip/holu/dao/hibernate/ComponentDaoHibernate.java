package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.model.Component;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("componentDao")
public class ComponentDaoHibernate extends GenericDaoHibernate<Component, Long> implements ComponentDao {

    public ComponentDaoHibernate() {
        super(Component.class);
    }

    @Override
    public List<Component> listComponentByProject(String projectID) {
        String queryString="From Component where project.id="+projectID;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}
