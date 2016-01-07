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
    public List<Object[]> getSummaryItem(String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select sum(b.SumWeight_actual) as actual,sum(a.SumWeight_plan) as planData from  ( " +
                "SELECT itemID,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql += " and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY itemID,StartOrEnd,ActualPlanPredict) as a left join  " +
                "(SELECT itemID, SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000'";
        if (sumDateStart != null) {
            sql += " and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql += " and ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY itemID,StartOrEnd,ActualPlanPredict) as b on a.itemID=b.itemID ";
        Query query = getSession().createSQLQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSummaryValidItem(String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select distinct a.ItemName,a.itemID from  (" +
                "SELECT ItemName,itemID,processID,ProcessName,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM  " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ")  AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY ItemName,itemID,ProcessName,ProcessID,StartOrEnd,ActualPlanPredict) as a ," +
                "(SELECT ItemName,itemID,processID,ProcessName,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' AND ItemStyle='" + itemStyle + "' " +
                "GROUP BY ItemName,itemID,ProcessName,ProcessID,StartOrEnd,ActualPlanPredict) as b";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getSummaryDetailByItem(String itemName, String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select b.SumWeight_actual,a.SumWeight_plan,a.itemID,a.itemName from  (" +
                "SELECT  projectPathName as itemName,projectID as itemID,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' and projectRootID='" + itemName + "' " +
                "GROUP BY projectID,projectPathName,StartOrEnd,ActualPlanPredict) as a left Join " +
                "(SELECT projectPathName as itemName,projectID as itemID, SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual'  and projectRootID='" + itemName + "' " +
                "GROUP BY projectID,projectPathName,StartOrEnd,ActualPlanPredict) as b on a.itemID=b.itemID";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
    @Override
    public List<Object[]> getSummaryDetail(String itemName, String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
        String sql = "select sum(b.SumWeight_actual) as actualData,sum(a.SumWeight_plan) as planData from  (" +
                "SELECT distinct processID, processName,itemID,SUM(SumWeight)*0.001 AS SumWeight_plan " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' AND ItemStyle='" + itemStyle + "' and ItemName='" + itemName + "' " +
                "GROUP BY ProcessID,itemID,processName,StartOrEnd,ActualPlanPredict) as a left Join " +
                "(SELECT distinct processID, processName,itemID, SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " + tableName + " " +
                "WHERE curDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and curDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual'  AND ItemStyle='" + itemStyle + "' and ItemName='" + itemName + "' " +
                "GROUP BY ProcessID,itemID,processName,StartOrEnd,ActualPlanPredict) as b on a.processID=b.processID";

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
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Actual' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT ProjectRootID AS ItemName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT ProjectRootID AS ItemName,Max(MaxDate) AS SumDate_plan_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Predict' " +
                "GROUP BY ProjectRootID,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT ProjectRootID AS ItemName,Max(MaxDate) AS SumDate_predict_end " +
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
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Actual' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Plan' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,MAX(MaxDate) AS SumDate_plan_end " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+tableName+" "+
                "WHERE StartOrEnd='Start' AND ActualPlanPredict='Predict' " +
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT DepartmentID AS ItemName,departmentPathName,MAX(MaxDate) AS SumDate_predict_end " +
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
                "And StartOrEnd='Start' AND ActualPlanPredict='Actual'  " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT processID AS ItemName,processName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "and StartOrEnd='End' AND ActualPlanPredict='Actual'  " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "And StartOrEnd='Start' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT processID AS ItemName,processName,Max(MaxDate) AS SumDate_plan_end " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "and StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT processID AS ItemName,processName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+ tableName+ " "+
                "WHERE ProjectRootID='"+projectID+"' " +
                "And StartOrEnd='Start' AND ActualPlanPredict='Predict' " +
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT processID AS ItemName,processName,Max(MaxDate) AS SumDate_predict_end " +
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
                "AND StartOrEnd='Start' AND ActualPlanPredict='Actual' " +
                "GROUP BY projectID, ProjectPathName,StartOrEnd,ActualPlanPredict) as a left join " +
                "(SELECT projectID, ProjectPathName AS ItemName,Max(MaxDate) AS SumDate_actual_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' " +
                "GROUP BY projectID, ProjectPathName,StartOrEnd,ActualPlanPredict) as b on a.itemName=b.itemName left join " +
                "(SELECT projectID, ProjectPathName AS ItemName,Min(MinDate) AS SumDate_plan_start " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='Start' AND ActualPlanPredict='Plan' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as c on a.itemname=c.itemname left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Max(MaxDate) AS SumDate_plan_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as d on a.itemName=d.itemName left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Min(MinDate) AS SumDate_predict_start " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='Start' AND ActualPlanPredict='Predict' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as e on a.itemName=e.itemName left join " +
                "(SELECT projectID,ProjectPathName AS ItemName,Max(MaxDate) AS SumDate_predict_end " +
                "FROM "+ tableName+" WHERE DepartmentID='"+departmentID+"' " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Predict' " +
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> SearchProjectBetweenDate(String startDate, String endDate, String processIds, String tableName) {
        String sql="select b.SumWeight_actual,a.SumWeight_plan, a.curDate from " +
                "(" +
                "SELECT curDate,SUM(SumWeight)*0.001 AS  SumWeight_plan " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' AND ItemStyle='Project' " +
                "GROUP BY curDate,StartOrEnd,ActualPlanPredict ) as a left join  " +
                "(" +
                "SELECT curDate,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' AND ItemStyle='Project' " +
                "GROUP BY curDate,StartOrEnd,ActualPlanPredict ) as b on a.curDate=b.curDate " +
                "ORDER BY curDate DESC";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
    @Override
    public List<Object[]> SearchFactoryBetweenDate(String startDate, String endDate, String processIds, String tableName) {
        String sql="select b.SumWeight_actual,a.SumWeight_plan, a.itemID, a.itemName from " +
                "(" +
                "SELECT itemName,itemID,SUM(SumWeight)*0.001 AS  SumWeight_plan " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' AND ItemStyle='Factory' " +
                "GROUP BY itemName,itemID,StartOrEnd,ActualPlanPredict ) as a left join  " +
                "(" +
                "SELECT itemName,itemID,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' AND ItemStyle='Factory' " +
                "GROUP BY itemName,itemID,StartOrEnd,ActualPlanPredict ) as b on a.itemID=b.itemID ";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> SearchFactoryItemBetweenDate(String itemID,String startDate, String endDate, String processIds, String tableName) {
        String sql="select b.SumWeight_actual,a.SumWeight_plan, a.itemID, a.itemName from " +
                "(" +
                "SELECT projectPathName as itemName,projectID as itemID,SUM(SumWeight)*0.001 AS  SumWeight_plan " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' AND DepartmentID='"+itemID+"' "+
                "GROUP BY projectID,projectPathName,StartOrEnd,ActualPlanPredict ) as a left join  " +
                "(" +
                "SELECT projectPathName as itemName,projectID as itemID,SUM(SumWeight)*0.001 AS SumWeight_actual " +
                "FROM " +tableName+" "+
                "WHERE curDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' AND DepartmentID='"+itemID+"' "+
                "GROUP BY projectID,projectPathName,StartOrEnd,ActualPlanPredict ) as b on a.itemID=b.itemID ";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
}
