package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.SummaryDao;
import com.huivip.holu.model.Summary;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("summaryDao")
public class SummaryDaoHibernate extends GenericDaoHibernate<Summary, Long> implements SummaryDao {

    public SummaryDaoHibernate() {
        super(Summary.class);
    }

    @Override
    public List<Summary> summaryList(String tableName) {
        String sql="Select * from "+tableName;
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Summary.class);
        List<Summary> dataList=query.list();
        return dataList;
    }
}
