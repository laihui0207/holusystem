package com.huivip.holu.dao;

import com.huivip.holu.model.SummaryTotal;

import java.util.List;

/**
 * An interface that provides a data management interface to the SummaryTotal table.
 */
public interface SummaryTotalDao extends GenericDao<SummaryTotal, Long> {

    List<Object[]> getSummaryItem(String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName);
    List<String> getSummaryValidItem(String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName);
    List<Object[]> getSummaryDetailByItem(String itemName, String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName);
    List<String> getDetailValidProjectItem(String itemID, String sumDate, String processIds,  String startOrEnd, String tableName);
    List<String> getDetailValidFactoryItem(String itemID, String sumDate, String processIds,  String startOrEnd, String tableName);
    List<Object[]> getDetailProjectDataByItem(String itemID, String projectPathName, String sumDate, String processIds,  String startOrEnd, String tableName);
    List<Object[]> getDetailFactoryDataByItem(String itemID, String departmentPathName, String sumDate, String processIds,  String startOrEnd, String tableName);
    List<Object[]> getProjectSummaryProcess(String tableName);
    List<Object[]> getFactorySummaryProcess(String tableName);
    List<Object[]> getProjectSummaryProcessDetail(String tableName,String projectID);
    List<Object[]> getFactorySummaryProcessDetail(String tableName,String departmentID);

}