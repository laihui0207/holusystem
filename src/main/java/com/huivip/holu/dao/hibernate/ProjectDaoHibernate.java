package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectDao")
public class ProjectDaoHibernate extends GenericDaoHibernate<Project, Long> implements ProjectDao {

    public ProjectDaoHibernate() {
        super(Project.class);
    }

    @Override
    public List<Project> getProjectByUserID(String userID, String parentProject,ExtendedPaginatedList list) {
        String hsqlString="select p From Project p join p.departments pd "
                +" where pd.departmentID in (select d.departmentID From Department d join d.users u where u.userID='"+userID+"')";
        if(null!=parentProject && !parentProject.equals("")){
            hsqlString="select p From Project p join p.parentProject pa where pa.projectID='"+parentProject+"'";
        }
        Query query=getSession().createQuery(hsqlString);
        List<Project> totalList=query.list();
        int totalCount=totalList.size();
        if(list!=null){
            query.setFirstResult(list.getFirstRecordIndex());
            query.setMaxResults(list.getPageSize());
        }
        List<Project> dataList=query.list();
        if(list!=null){
            list.setList(dataList);
            list.setTotalNumberOfRows(totalCount);
        }
        return dataList;
    }

    @Override
    public Project getProjectByprojectID(String projectID) {
        String hqlString="From Project where projectID='"+projectID+"'";
        Query query=getSession().createQuery(hqlString);
        List<Project> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
