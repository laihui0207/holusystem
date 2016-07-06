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
        /*String sql = "select sum(b.SumWeight_actual) as actual,sum(a.SumWeight_plan) as planData from  ( " +
                "SELECT itemID,SUM(TotalWeight)*0.001 AS SumWeight_plan " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql += " and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' "+
                "GROUP BY itemID,StartOrEnd,ActualPlanPredict) as a left join  " +
                "(SELECT itemID, SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000'";
        if (sumDateStart != null) {
            sql += " and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql += " and ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' " +
                "GROUP BY itemID,StartOrEnd,ActualPlanPredict) as b on a.itemID=b.itemID ";*/
        String sql2="select sum(ItemActualTotal) as actualtotal,sum(ItemPlanTotal) as plantotal from view_"+tableName+"_SummaryTableTotalByProjectByDate"+
                " where ProcessID in ("+processIds+") and ItemDate<='" + sumDateEnd + "'";
        if (sumDateStart != null) {
            sql2 += " and ItemDate >='" + sumDateStart + "' ";
        }
        //sql2+=" group by itemDate";
        Query query = getSession().createSQLQuery(sql2);
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSummaryValidItem(String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
       /* String sql = "select distinct a.ItemName,a.itemID from  (" +
                "SELECT ItemName,itemID,processID,SUM(TotalWeight)*0.001 AS SumWeight_plan " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ")  AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' " +
                "GROUP BY ItemName,itemID,ProcessID,StartOrEnd,ActualPlanPredict) as a ," +
                "(SELECT ItemName,itemID,processID,SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000' ";
        if (sumDateStart != null) {
            sql += " and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual' " +
                "GROUP BY ItemName,itemID,ProcessID,StartOrEnd,ActualPlanPredict) as b";*/

        String sql2="select distinct itemName,ItemId from view_"+tableName+"_SummaryTableTotalByProjectByDate"+
                " where ProcessID in ("+processIds+") and ItemDate<='" + sumDateEnd + "'";
        if (sumDateStart != null) {
            sql2 += " and ItemDate >='" + sumDateStart + "' ";
        }
        Query query = getSession().createSQLQuery(sql2);
        return query.list();
    }

    @Override
    public List<Object[]> getSummaryDetailByItem(String itemName, String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
        /*String sql = "select b.SumWeight_actual,a.SumWeight_plan,a.itemID,a.itemName from  (" +
                "SELECT  projectrootName as itemName,projectrootID as itemID,SUM(TotalWeight)*0.001 AS SumWeight_plan " +
                "FROM view_" + tableName + "_SummaryTableTotalByProject " +
                "WHERE ItemDate<='" + sumDateEnd + "' ";
        if(sumDateStart!=null){
            sql+=" and ItemDate >='" + sumDateStart + "' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' and itemID='" + itemName + "' " +
                "GROUP BY projectrootID,projectrootName,StartOrEnd,ActualPlanPredict) as a left Join " +
                "(SELECT projectrootName as itemName,projectrootID as itemID, SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" + tableName + "_SummaryTableTotalByProject " +
                "WHERE ItemDate<='" + sumDateEnd + "' ";
        if(sumDateStart!=null){
            sql+=" and ItemDate >='" + sumDateStart + "' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual'  and itemID='" + itemName + "' " +
                "GROUP BY projectrootID,projectrootName,StartOrEnd,ActualPlanPredict) as b on a.itemID=b.itemID";*/
        /*String sql2="select sum(ItemActualTotal) as actualTotal,sum(ItemPlanTotal) as plantotal,ItemId,itemName from view_"+tableName+"_SummaryTableTotalByProjectByDate"+
                " where ProcessID in ("+processIds+") and ItemDate<='" + sumDateEnd + "' and itemid='"+itemName+"' ";
        if (sumDateStart != null) {
            sql2 += " and ItemDate >='" + sumDateStart + "' ";
        }
        sql2+=" group by itemID,itemName";*/
        String sql3 = "select sum(SumWeight_actual) as actualTotal,sum(SumWeight_plan) as planTotal,itemID,itemname from (" +
                "select * from (" +
                "SELECT  projectrootName as itemName,projectrootID as itemID,SUM(TotalWeight)*0.001 AS SumWeight_plan, 0 as SumWeight_actual " +
                " FROM view_" + tableName + "_SummaryTableTotalByProject WHERE ItemDate<='" + sumDateEnd + "'";
        if (sumDateStart != null) {
            sql3 += " and ItemDate >='" + sumDateStart + "' ";
        }
        sql3 += "and  ProcessID in (" + processIds + ")  AND StartOrEnd='end' AND ActualPlanPredict='Plan' and itemID='" + itemName + "' " +
                "GROUP BY projectrootID,projectrootName,StartOrEnd,ActualPlanPredict " +
                ") as a UNION ALL select * from (" +
                "SELECT projectrootName as itemName,projectrootID as itemID,0 as SumWeight_plan,  SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                " FROM view_" + tableName + "_SummaryTableTotalByProject " +
                "WHERE ItemDate<='" + sumDateEnd + "'";
        if (sumDateStart != null) {
            sql3 += " and ItemDate >='" + sumDateStart + "' ";
        }
        sql3 += "and  ProcessID in (" + processIds + ")  AND StartOrEnd='end' AND ActualPlanPredict='Actual'  " +
                "and itemID='" + itemName + "'   GROUP BY projectrootID,projectrootName,StartOrEnd,ActualPlanPredict " +
                ") as b  " +
                ") x group by itemid,itemname";
        Query query = getSession().createSQLQuery(sql3);
        return query.list();
    }
    @Override
    public List<Object[]> getSummaryDetail(String itemName, String sumDateStart, String sumDateEnd, String processIds, String itemStyle, String startOrEnd, String tableName) {
       /* String sql = "select sum(b.SumWeight_actual) as actualData,sum(a.SumWeight_plan) as planData from  (" +
                "SELECT distinct processID, itemID,SUM(TotalWeight)*0.001 AS SumWeight_plan " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Plan' and ItemName='" + itemName + "' " +
                "GROUP BY ProcessID,itemID,StartOrEnd,ActualPlanPredict) as a left Join " +
                "(SELECT distinct processID, itemID, SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" + tableName + "_SummaryTableTotalByProjectRoot " +
                "WHERE ItemDate<='" + sumDateEnd + " 00:00:00.000' ";
        if(sumDateStart!=null){
            sql+=" and ItemDate >='" + sumDateStart + " 00:00:00.000' ";
        }
        sql+=" and  ProcessID in (" + processIds + ") AND StartOrEnd='" + startOrEnd + "' " +
                "AND ActualPlanPredict='Actual'  and ItemName='" + itemName + "' " +
                "GROUP BY ProcessID,itemID,StartOrEnd,ActualPlanPredict) as b on a.processID=b.processID";*/
        String sql2="select sum(ItemActualTotal) as actualTotal,sum(ItemPlanTotal) as planTotal,ItemId,itemName from view_"+tableName+"_SummaryTableTotalByProjectByDate "+
                " where ProcessID in ("+processIds+") and ItemDate<='" + sumDateEnd + "' and itemName='"+itemName+"'";
        if (sumDateStart != null) {
            sql2 += " and ItemDate >='" + sumDateStart + "' ";
        }
        sql2+=" group by itemID,itemName";
        Query query = getSession().createSQLQuery(sql2);
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
        /*String sql="select distinct a.ItemName,r.ProjectName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
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
                "R_Project r where a.itemName=r.ProjectID ";*/
        //String sql="SELECT  ProjectID ,ProjectPathName AS projectName,Min(YS1) AS SumDate_actual_start,Max(YE2) AS SumDate_actual_end,Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end " +
                String sql="SELECT  ProjectID ,ProjectPathName AS projectName,"+
                        "Min(AS1) AS SumDate_actual_start,Max(AE2) AS SumDate_actual_end,"+
                        "Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end,"+
                        "Min(YS1) SumDate_predict_start,Max(YE2) SumDate_predict_end,"+
                        "Min(PSAS1) as currentStatus,Max(PEYE2) as predictStatus " +
                " FROM view_"+tableName+"_SummaryDateByProjectProcessDetail " +
                " GROUP BY ProjectID,ProjectPathName";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getFactorySummaryProcess(String tableName) {
       /* String sql="select distinct a.ItemName,a.departmentPathName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
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
                "GROUP BY DepartmentID,departmentPathName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";*/
        //String sql="SELECT  DepartmentID,DepartmentPathName,Min(YS1) AS SumDate_actual_start,Max(YE2) AS SumDate_actual_end,Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end,MIN() " +
                String sql="SELECT  DepartmentID ,DepartmentPathName AS projectName,"+
                        "Min(AS1) AS SumDate_actual_start,Max(AE2) AS SumDate_actual_end,"+
                        "Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end,"+
                        "Min(YS1) AS SumDate_predict_start,Max(YE2) AS SumDate_predict_end," +
                        "Min(PSAS1) AS currentStatus,Max(PEYE2) AS predictStatus "+
                " FROM view_"+tableName+"_SummaryDateByProjectProcessDetail " +
                " GROUP BY DepartmentID,DepartmentPathName";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getProjectSummaryProcessDetail(String tableName, String projectID) {
       /* String sql="select distinct a.ItemName,a.processName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
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
                "GROUP BY ProjectRootID,processID,processName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";*/
       // String sql="SELECT  processID ,ProcessName ,Min(YS1) AS SumDate_actual_start,Max(YE2) AS SumDate_actual_end,Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end " +
        String sql="SELECT  processID ,ProcessName AS projectName,"+
                "Min(AS1) AS SumDate_actual_start,Max(AE2) AS SumDate_actual_end,"+
                "Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end,"+
                "Min(YS1) SumDate_predict_start,Max(YE2) SumDate_predict_end,"+
                "Min(PSAS1) as currentStatus,Max(PEYE2) as predictStatus,DepartmentName " +
                " FROM view_"+tableName+"_SummaryDateByProjectProcessDetail where projectID='" +projectID+"' "+
                " GROUP BY ProcessId,processName,DepartmentName";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getFactorySummaryProcessDetail(String tableName, String departmentID) {
       /* String sql="select a.projectID, a.ItemName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end " +
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
                "GROUP BY projectID,ProjectPathName,StartOrEnd,ActualPlanPredict) as f on a.itemName=f.itemName";*/
       // String sql="SELECT  processID ,ProcessName ,Min(YS1) AS SumDate_actual_start,Max(YE2) AS SumDate_actual_end,Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end " +
        String sql="SELECT  processID ,ProcessName AS projectName,"+
                "Min(AS1) AS SumDate_actual_start,Max(AE2) AS SumDate_actual_end,"+
                "Min(PS1) AS SumDate_plan_start,Max(PE2) AS SumDate_plan_end,"+
                "Min(YS1) AS SumDate_predict_start,Max(YE2) AS SumDate_predict_end," +
                "Min(PSAS1) AS currentStatus,Max(PEYE2) AS predictStatus "+
                " FROM view_"+tableName+"_SummaryDateByProjectProcessDetail where departmentID='" +departmentID+"' "+
                " GROUP BY ProcessId,processName";
        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> SearchProjectBetweenDate(String startDate, String endDate, String processIds, String companyId) {
       /* String sql="select b.SumWeight_actual,a.SumWeight_plan, a.ItemDate from " +
                "(" +
                "SELECT ItemDate,SUM(TotalWeight)*0.001 AS SumWeight_plan  " +
                "FROM view_" + companyId +"_SummaryTableTotalByProject "+
                "WHERE ItemDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' "+
                "GROUP BY ItemDate,StartOrEnd,ActualPlanPredict ) as a left join  " +
                "(" +
                "SELECT ItemDate,SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" + companyId +"_SummaryTableTotalByProject "+
                "WHERE ItemDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' "+
                "GROUP BY ItemDate,StartOrEnd,ActualPlanPredict ) as b on a.ItemDate=b.ItemDate " +
                "ORDER BY ItemDate DESC";
        Query query=getSession().createSQLQuery(sql);*/
        String sql2="select sum(ItemActualTotal) as actualTotal,sum(ItemPlanTotal) as planTotal from (select ItemActualTotal, ItemPlanTotal,ItemDate from view_"+companyId+"_SummaryTableTotalByProjectByDate"+
                " where ProcessID in ("+processIds+") and ItemDate Between '"+startDate+"' AND '"+endDate+"') as A";
        Query query = getSession().createSQLQuery(sql2);
        return query.list();
    }
    @Override
    public List<Object[]> SearchFactoryBetweenDate(String startDate, String endDate, String processIds, String tableName) {
        String sql="select sum(SumWeight_actual) as actualTotal,sum(SumWeight_plan) as planTotal,itemID,itemName from (" +
                " select * from ( "+
                "SELECT itemName,itemID, 0 as SumWeight_actual,SUM(TotalWeight)*0.001 AS  SumWeight_plan " +
                "FROM view_" +tableName+"_SummaryTableTotalByDepartment "+
                "WHERE ItemDate  Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' "+
                "GROUP BY itemName,itemID,StartOrEnd,ActualPlanPredict ) as a  UNION ALL select * from ( " +
                "SELECT itemName,itemID,SUM(TotalWeight)*0.001 AS SumWeight_actual, 0 as  SumWeight_plan " +
                "FROM view_" +tableName+"_SummaryTableTotalByDepartment "+
                "WHERE ItemDate  Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' "+
                "GROUP BY itemName,itemID,StartOrEnd,ActualPlanPredict ) as b ) AS X group by itemID,itemName ";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> SearchFactoryItemBetweenDate(String itemID,String startDate, String endDate, String processIds, String tableName) {
        String sql="select sum(SumWeight_actual) as actutalTotal,sum(SumWeight_plan) as planTotal, itemID, itemName from " +
                " ( select * from (" +
                "SELECT ItemPathName as itemName, itemID,SUM(TotalWeight)*0.001 AS  SumWeight_plan, 0 as SumWeight_actual " +
                "FROM view_" +tableName+"_SummaryTableTotalByProject  "+
                "WHERE ItemDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Plan' AND DepartmentID='"+itemID+"' "+
                "GROUP BY itemID,ItemPathName,StartOrEnd,ActualPlanPredict ) as a  UNION ALL select * from  " +
                "(" +
                "SELECT ItemPathName as itemName,itemID,0 as SumWeight_plan,SUM(TotalWeight)*0.001 AS SumWeight_actual " +
                "FROM view_" +tableName+"_SummaryTableTotalByProject  "+
                "WHERE ItemDate Between '"+startDate+" 00:00:00.000' AND '"+endDate+" 00:00:00.000' AND ProcessID in (" + processIds + ") " +
                "AND StartOrEnd='End' AND ActualPlanPredict='Actual' AND DepartmentID='"+itemID+"' "+
                "GROUP BY itemID,ItemPathName,StartOrEnd,ActualPlanPredict ) as b) as X group by itemid,itemName ";

        Query query = getSession().createSQLQuery(sql);
        return query.list();
    }
}
