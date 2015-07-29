package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("customGroupDao")
public class CustomGroupDaoHibernate extends GenericDaoHibernate<CustomGroup, Long> implements CustomGroupDao {

    public CustomGroupDaoHibernate() {
        super(CustomGroup.class);
    }
}
