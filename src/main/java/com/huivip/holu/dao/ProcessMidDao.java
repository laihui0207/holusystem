package com.huivip.holu.dao;

import com.huivip.holu.model.ProcessMid;

import java.util.List;

/**
 * An interface that provides a data management interface to the ProcessMid table.
 */
public interface ProcessMidDao extends GenericDao<ProcessMid, Long> {
    ProcessMid getProcessMid(String componentID,String processID,String tableName);
    ProcessMid save(ProcessMid object,String tableName);
    List<Object[]> getComponentStylesOfProject(String projectID, String taskType, String tableName);
    List<Object[]> getProjectListOfUser(String taskType, String tableName);

}