package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.Project;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoHibernate extends GenericDaoHibernate<Project, Long> implements ProjectDao {

    public ProjectDaoHibernate() {
        super(Project.class);
    }

    @Override
    public List<Project> getProjectListByCompany(String CompanyID) {
        String queryString="From Project where company.id="+CompanyID;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}
