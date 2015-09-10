package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.UserTrack;
import com.huivip.holu.dao.UserTrackDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("userTrackDao")
public class UserTrackDaoHibernate extends GenericDaoHibernate<UserTrack, Long> implements UserTrackDao {

    public UserTrackDaoHibernate() {
        super(UserTrack.class);
    }
}
