package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
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
        queryString+=" group by company,styleName";
        Query query=getSession().createQuery(queryString);

        return query.list();
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String companyId,ExtendedPaginatedList list) {
        String queryString="From ComponentStyle where company.id="+companyId+" and styleID='"+styleID+"'";
        queryString+=" order by processOrder";
        Query query=getSession().createQuery(queryString);
        if(null!=list){
            List<ComponentStyle> totalList=query.list();
            query.setFirstResult(list.getFirstRecordIndex());
            query.setMaxResults(list.getPageSize());
            list.setTotalNumberOfRows(totalList.size());
        }
        List<ComponentStyle> dataList=query.list();
        if(null!=list){
            list.setList(dataList);
        }
        return dataList;
    }

    @Override
    public ComponentStyle getComponentProcessByProcessID(String styleProcessID) {
        String queryString="From ComponentStyle where styleProcessID='"+styleProcessID+"'";
        queryString+=" order by processOrder";
        Query query=getSession().createQuery(queryString);
        List<ComponentStyle> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
