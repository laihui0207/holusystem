package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.SummaryTotalDao;
import com.huivip.holu.model.SummaryTotal;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("summaryTotalDao")
public class SummaryTotalDaoHibernate extends GenericDaoHibernate<SummaryTotal, Long> implements SummaryTotalDao {

    public SummaryTotalDaoHibernate() {
        super(SummaryTotal.class);
    }

    @Override
    public List<Object[]> getSummaryItem(String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select distinct a.processId,a.processName,a.itemID,b.SumWeight_actual,a.SumWeight_plan from  ( " +
                "SELECT processID, processName,itemID,curDate,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY curDate,ProcessID,processName,itemID,StartOrEnd,ActualPlanPredict) as a left join  " +
                "(SELECT processID, processName,itemID,curDate, SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY curDate,ProcessID,itemID,processName,StartOrEnd,ActualPlanPredict) as b on a.processID=b.processID ";
        Query query = getSession().createSQLQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public List<String> getSummaryValidItem(String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select distinct a.ItemName from  (" +
                "SELECT ItemName,processID,ProcessName,curDate,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM  " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ")  AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY curDate,ItemName,ProcessName,ProcessID,StartOrEnd,ActualPlanPredict) as a ," +
                "(SELECT ItemName,processID,ProcessName,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY curDate,ItemName,ProcessName,ProcessID,StartOrEnd,ActualPlanPredict) as b";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getSummaryDetailByItem(String itemName, String sumDate, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select distinct a.processId,a.processName,a.itemID,b.SumWeight_actual,a.SumWeight_plan from  (" +
                "SELECT processID, processName,itemID,curDate,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' and ItemName='" + itemName + "' " +
                "GROUP BY curDate,ProcessID,itemID,processName,StartOrEnd,ActualPlanPredict) as a left Join " +
                "(SELECT processID, processName,curDate,itemID, SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual'  AND ItemStyle='" + itemStyle + "' and ItemName='" + itemName + "' " +
                "GROUP BY curDate,ProcessID,itemID,processName,StartOrEnd,ActualPlanPredict) as b on a.processID=b.processID";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<String> getDetailValidProjectItem(String itemID, String sumDate, String processIds, String startOrEnd, String tableName) {
        String sql = "select distinct a.ItemName from (" +
                "SELECT ProjectID AS ItemID,processName,ProjectPathName AS ItemName,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                " AND ActualPlanPredict='plan' " +
                "AND ProjectRootID='"+itemID+"' "+
                "GROUP BY curDate,ProjectID,processName,ProjectPathName,ProcessID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectID AS ItemID,ProjectPathName AS ItemName,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                " AND ActualPlanPredict='Actual'  " +
                "AND ProjectRootID='"+itemID+"' "+
                "GROUP BY curDate,ProjectID,ProjectPathName,ProcessID,StartOrEnd,ActualPlanPredict) as b on a.ItemID=b.ItemID";
        Query query = getSession().createSQLQuery(sql);
        List list=query.list();
        return list;
    }

    @Override
    public List<String> getDetailValidFactoryItem(String itemID, String sumDate, String processIds, String startOrEnd, String tableName) {
        String sql = "select distinct a.ItemName from (" +
                "SELECT ProjectID AS ItemID,processName,DepartmentPathName AS ItemName,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND DepartmentRootID='"+itemID+"' "+
                " AND ActualPlanPredict='plan' " +
                "GROUP BY curDate,ProjectID,processName,DepartmentPathName,ProcessID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectID AS ItemID,ProjectPathName AS ItemName,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND DepartmentRootID='"+itemID+"' "+
                " AND ActualPlanPredict='Actual'  " +
                "GROUP BY curDate,ProjectID,ProjectPathName,ProcessID,StartOrEnd,ActualPlanPredict) as b on a.ItemID=b.ItemID";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getDetailProjectDataByItem(String itemID, String projectPathName, String sumDate, String processIds, String startOrEnd, String tableName) {
        String sql="select distinct a.processID,a.processName,a.itemID,b.SumWeight_actual,a.SumWeight_plan from (" +
                "SELECT ProjectID AS ItemID,processName,ProjectPathName AS ItemName,processID,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND ActualPlanPredict='plan' " +
                "AND ProjectRootID='"+itemID+"' "+
                "AND ProjectPathName='"+projectPathName+"' " +
                "GROUP BY curDate,ProjectID,processName,ProjectPathName,ProcessID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectID AS ItemID,ProjectPathName AS ItemName,processID,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND ActualPlanPredict='Actual' " +
                "AND ProjectRootID='"+itemID+"' "+
                "AND ProjectPathName='"+projectPathName+"' " +
                "GROUP BY curDate,ProjectID,ProjectPathName,ProcessID,StartOrEnd,ActualPlanPredict) as b on a.ItemID=b.ItemID";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getDetailFactoryDataByItem(String itemID, String departmentPathName, String sumDate, String processIds, String startOrEnd, String tableName) {
        String sql="select distinct a.processID,a.processName,a.itemID,b.SumWeight_actual,a.SumWeight_plan from (" +
                "SELECT ProjectID AS ItemID,processName,DepartmentPathName AS ItemName,processID,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND ActualPlanPredict='plan' " +
                "AND DepartmentRootID='"+itemID+"' "+
                "AND departmentPathName='"+departmentPathName+"' " +
                "GROUP BY curDate,ProjectID,processName,DepartmentPathName,ProcessID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectID AS ItemID,DepartmentPathName AS ItemName,processID,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate='" + sumDate + " 00:00:00.000' and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' "+
                "AND ActualPlanPredict='Actual' " +
                "AND DepartmentRootID='"+itemID+"' "+
                "AND departmentPathName='"+departmentPathName+"' " +
                "GROUP BY curDate,ProjectID,DepartmentPathName,ProcessID,StartOrEnd,ActualPlanPredict) as b on a.ItemID=b.ItemID";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

}
