package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.TaskDao;
import com.huivip.holu.model.Task;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoHibernate extends GenericDaoHibernate<Task, Long> implements TaskDao {

    public TaskDaoHibernate() {
        super(Task.class);
    }

    @Override
    public List<Task> getTaskofUser(String projectId, String processId, String tableName, Boolean isAdmin, ExtendedPaginatedList pageList) {
        if(processId.length()==0 || projectId.length()==0) return null;
        String sql="Select * from "+tableName+" where ProjectID in ("+projectId+") ";
        if(!isAdmin){
            sql+=" and ProcessID in ("+processId+")";
        }
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Task.class);
        if(pageList!=null){
            int total=query.list().size();
            pageList.setTotalNumberOfRows(total);
            query.setFirstResult(pageList.getFirstRecordIndex());
            query.setMaxResults(pageList.getPageSize());
        }
        List<Task> dataList=query.list();
        if(pageList!=null){
            pageList.setList(dataList);
        }
        return dataList;
    }

    @Override
    public List<Object[]> getProject(String tableName, String taskType, String userID) {
        String sql="select pmt.projectID,rp.projectpathname from "+tableName+" pmt,R_project rp where pmt.ProjectID=rp.projectID and rp.projectID in ";
        sql+="(select rp1.projectID from R_project rp1,R_departmentProjectmappingtable rdp where rp1.projectid=rdp.projectid and rdp.departmentID in (Select rd.departmentID from r_department rd,R_departmentusermappingtable rdu where rd.departmentID=rdu.departmentid and rdu.userid='"+userID+"'))";
        /*if(taskType.equalsIgnoreCase("doing")){
            sql+=" and (pmt.StartDate  is  null or pmt.EndDate is  null) ";
        }
        else if(taskType.equalsIgnoreCase("finish")){
            sql+=" and pmt.StartDate is not null and pmt.EndDate is not  null ";
        }
*/
        sql+=" group by pmt.ProjectID,rp.ProjectPathName";
        Query query=getSession().createSQLQuery(sql);
        return query.list();
    }
}
