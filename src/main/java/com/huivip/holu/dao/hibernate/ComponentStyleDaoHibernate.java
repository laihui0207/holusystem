package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("componentStyleDao")
public class ComponentStyleDaoHibernate extends GenericDaoHibernate<ComponentStyle, Long> implements ComponentStyleDao {

    public ComponentStyleDaoHibernate() {
        super(ComponentStyle.class);
    }
}
