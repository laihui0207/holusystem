package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postBarDao")
public class PostBarDaoHibernate extends GenericDaoHibernate<PostBar, Long> implements PostBarDao {

    public PostBarDaoHibernate() {
        super(PostBar.class);
    }

    @Override
    public List<PostBar> getPostBarListBySubject(String subjectId,ExtendedPaginatedList list) {
        String queryString="From PostBar where postSubject.id="+subjectId;
        Query query=getSession().createQuery(queryString);
        int totalNum=0;
        if(list!=null){
            List<PostBar> totalList=query.list();
            query.setMaxResults(list.getPageSize());
            query.setFirstResult(list.getFirstRecordIndex());
            totalNum=totalList.size();
        }
        List<PostBar> dataList=query.list();
        if(list!=null){
            list.setTotalNumberOfRows(totalNum);
            list.setList(dataList);
        }
        return dataList;
    }

    @Override
    public List<PostBar> postBarByUser(String userId) {
        String sqlString="select id from R_PostBar where id in ("+
                "select postbar_id from postBarViewUsers where user_id=1) or id in("+
                "select postbar_id  from postBarViewGroups"+
                "where group_id in (select id from R_userGroup where id in(Select group_id from R_groupMember where user_id=2)))";
        String queryString="From PostBar p Join User u where u.id=:userId ";
        //String sqlString="select id from ";
        Query query=getSession().createQuery(queryString);
        query.setString("userId",userId);
        return query.list();
    }
}
