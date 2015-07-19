package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentationDao")
public class DocumentationDaoHibernate extends GenericDaoHibernate<Documentation, Long> implements DocumentationDao {

    public DocumentationDaoHibernate() {
        super(Documentation.class);
    }

    @Override
    public List<Documentation> myDocumentations(String userId,ExtendedPaginatedList list) {
        Criteria criteria=getSession().createCriteria(Documentation.class);
        criteria.add(Restrictions.eq("creater.id",Long.parseLong(userId)));
        List<Documentation> dataList=criteria.list();
        int totalCount=dataList.size();
        if(list!=null){
           criteria.setFirstResult(list.getFirstRecordIndex());
            criteria.setMaxResults(list.getPageSize());
            if (list.getSortCriterion() != null) {
                if (list.getSortDirection().equals(SortOrderEnum.ASCENDING)) {
                    criteria.addOrder(Order.asc(list.getSortCriterion()));
                }
                if (list.getSortDirection().equals(SortOrderEnum.DESCENDING)) {
                    criteria.addOrder(Order.desc(list.getSortCriterion()));
                }
            }
            dataList=criteria.list();
            list.setList(dataList);
            list.setTotalNumberOfRows(totalCount);
        }

        return dataList;
    }
}
