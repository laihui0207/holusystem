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

    @Override
    public List<Object[]> getProjectSummaryProcess(String tableName) {
        String sql="select distinct a.ItemName,r.ProjectName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
                "from  " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_actual_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectRootID AS ItemName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MaxDate) AS SumDate_plan_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_predict_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName, " +
                "R_Project r where a.itemName=r.ProjectID ";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getFactorySummaryProcess(String tableName) {
        String sql="select distinct a.ItemName,a.departmentPathName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
                "from  " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_actual_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MaxDate) AS SumDate_plan_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_predict_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getProjectSummaryProcessDetail(String tableName, String projectID) {
        String sql="select distinct a.ItemName,a.processName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
                "from  " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_actual_start " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "And StartOrEnd='End' AND ActualPlanPredict='Actual'  " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT processID AS ItemName,processName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "and StartOrEnd='End' AND ActualPlanPredict='Actual'  " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "And StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT processID AS ItemName,processName,Min(MaxDate) AS SumDate_plan_end " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "and StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "And StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_predict_end " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "and StartOrEnd='End' AND ActualPlanPredict='Predict'   " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getFactorySummaryProcessDetail(String tableName, String departmentID) {
        String sql="select a.projectID, a.ItemName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
                "from  " +
                "(SELECT projectID, ProjectPathName AS ItemName,Min(MinDate) AS SumDate_actual_start " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY projectID, ProjectPathName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT projectID, ProjectPathName AS ItemName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY projectID, ProjectPathName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT projectID, ProjectPathName AS ItemName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Min(MaxDate) AS SumDate_plan_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Min(MinDate) AS SumDate_predict_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

}
