package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.dao.GroupDao;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDaoHibernate extends GenericDaoHibernate<CustomGroup, Long> implements GroupDao {

    public GroupDaoHibernate() {
        super(CustomGroup.class);
    }
}
