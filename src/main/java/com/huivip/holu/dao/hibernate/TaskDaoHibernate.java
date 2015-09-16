package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.TaskDao;
import com.huivip.holu.model.Task;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoHibernate extends GenericDaoHibernate<Task, Long> implements TaskDao {

    public TaskDaoHibernate() {
        super(Task.class);
    }

    @Override
    public List<Task> getTaskofUser(String projectId, String processId, String tableName) {
        if(processId.length()==0 || projectId.length()==0) return null;
        String sql="Select * from "+tableName+" where ProjectID in ("+projectId+") and ProcessID in ("+processId+")";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(Task.class);
        List<Task> list=query.list();
        return list;
    }
}
