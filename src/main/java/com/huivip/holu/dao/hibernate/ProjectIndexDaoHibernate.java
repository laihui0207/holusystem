package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ProjectIndexDao;
import com.huivip.holu.model.ProjectIndex;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectIndexDao")
public class ProjectIndexDaoHibernate extends GenericDaoHibernate<ProjectIndex, Long> implements ProjectIndexDao {

    public ProjectIndexDaoHibernate() {
        super(ProjectIndex.class);
    }

    @Override
    public ProjectIndex getProjectIndexByProject(String projectID) {
        String hqlString="From ProjectIndex where projectID='"+projectID+"'";
        Query query=getSession().createQuery(hqlString);
        List<ProjectIndex> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
