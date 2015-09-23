package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.SettingDao;
import com.huivip.holu.model.Setting;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("settingDao")
public class SettingDaoHibernate extends GenericDaoHibernate<Setting, Long> implements SettingDao {

    public SettingDaoHibernate() {
        super(Setting.class);
    }

    @Override
    public List<Setting> getSettingByCompany(String companyID) {
        String sql="From Setting where companyID='"+companyID+"'";
        Query query=getSession().createQuery(sql);
        List<Setting> list=query.list();
        if(null!=list && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Setting getSettingBySearch(String companyID, String keyArea, String keyName) {
        String sql="From Setting where companyID='"+companyID+"' and keyArea='"+keyArea+"' and keyName='"+keyName+"'";
        Query query=getSession().createQuery(sql);
        List<Setting> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
