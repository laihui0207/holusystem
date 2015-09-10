package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.dao.ClientVersionDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("clientVersionDao")
public class ClientVersionDaoHibernate extends GenericDaoHibernate<ClientVersion, Long> implements ClientVersionDao {

    public ClientVersionDaoHibernate() {
        super(ClientVersion.class);
    }
}
