package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.UserGroup;
import com.huivip.holu.dao.UserGroupDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("userGroupDao")
public class UserGroupDaoHibernate extends GenericDaoHibernate<UserGroup, Long> implements UserGroupDao {

    public UserGroupDaoHibernate() {
        super(UserGroup.class);
    }
}
