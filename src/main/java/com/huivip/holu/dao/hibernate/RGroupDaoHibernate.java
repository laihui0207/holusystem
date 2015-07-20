package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.RGroup;
import com.huivip.holu.dao.RGroupDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("rGroupDao")
public class RGroupDaoHibernate extends GenericDaoHibernate<RGroup, Long> implements RGroupDao {

    public RGroupDaoHibernate() {
        super(RGroup.class);
    }
}
