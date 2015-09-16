package com.huivip.holu.dao;

import com.huivip.holu.model.Task;

import java.util.List;

/**
 * An interface that provides a data management interface to the Task table.
 */
public interface TaskDao extends GenericDao<Task, Long> {
    List<Task> getTaskofUser(String projectId,String processId,String tableName);

}