package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Component;
import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("componentDao")
public class ComponentDaoHibernate extends GenericDaoHibernate<Component, Long> implements ComponentDao {

    public ComponentDaoHibernate() {
        super(Component.class);
    }
}
