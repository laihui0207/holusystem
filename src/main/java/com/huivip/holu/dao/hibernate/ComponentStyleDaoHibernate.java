package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.model.ComponentStyle;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("componentStyleDao")
public class ComponentStyleDaoHibernate extends GenericDaoHibernate<ComponentStyle, Long> implements ComponentStyleDao {

    public ComponentStyleDaoHibernate() {
        super(ComponentStyle.class);
    }

    @Override
    public List<ComponentStyle> getComponentStypeListByCompany(String companyId) {
        String queryString="From ComponentStyle where company.id="+companyId;
        Query query=getSession().createQuery(queryString);

        return query.list();
    }
}
