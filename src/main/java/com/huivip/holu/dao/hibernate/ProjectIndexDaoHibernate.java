package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.ProjectIndex;
import com.huivip.holu.dao.ProjectIndexDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("projectIndexDao")
public class ProjectIndexDaoHibernate extends GenericDaoHibernate<ProjectIndex, Long> implements ProjectIndexDao {

    public ProjectIndexDaoHibernate() {
        super(ProjectIndex.class);
    }
}
