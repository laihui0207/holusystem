package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.dao.RGroupDao;
import org.springframework.stereotype.Repository;

@Repository("rGroupDao")
public class RGroupDaoHibernate extends GenericDaoHibernate<CustomGroup, Long> implements RGroupDao {

    public RGroupDaoHibernate() {
        super(CustomGroup.class);
    }
}
