package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ClientVersionDao;
import com.huivip.holu.model.ClientVersion;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clientVersionDao")
public class ClientVersionDaoHibernate extends GenericDaoHibernate<ClientVersion, Long> implements ClientVersionDao {

    public ClientVersionDaoHibernate() {
        super(ClientVersion.class);
    }

    @Override
    public ClientVersion getClientByVersion(String version) {
        String hql="From ClientVersion where version='"+version+"'";
        Query query=getSession().createQuery(hql);
        List<ClientVersion> list=query.list();
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ClientVersion getLastedClient() {
        String hql="From ClientVersion cv where cv.version = (Select max(c.version) from ClientVersion c)";
        Query query=getSession().createQuery(hql);
        List<ClientVersion> list=query.list();
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
