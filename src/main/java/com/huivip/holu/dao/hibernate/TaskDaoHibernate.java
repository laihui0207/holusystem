package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.TaskDao;
import com.huivip.holu.model.Task;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
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
}
