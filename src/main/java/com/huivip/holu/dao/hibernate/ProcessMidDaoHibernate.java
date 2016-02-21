package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.model.ProcessMid;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository("processMidDao")
public class ProcessMidDaoHibernate extends GenericDaoHibernate<ProcessMid, Long> implements ProcessMidDao {

    public ProcessMidDaoHibernate() {
        super(ProcessMid.class);
    }

    @Override
    public ProcessMid getProcessMid(String componentID, String processID, String tableName) {
        String sql="select * from "+tableName+" where subComponentID='"+componentID+"' and styleProcessID='"+processID+"'";
        SQLQuery query=getSession().createSQLQuery(sql);
        query.addEntity(ProcessMid.class);
        List<ProcessMid> list=query.list();
        if(null!=list && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ProcessMid save(ProcessMid object, String tableName) {
        ProcessMid processMid=getProcessMid(object.getSubComponentID(),object.getStyleProcessID(),tableName);
        String sql="";
        String setString="";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(null!=processMid){
            if(object.getProcessNote()!=null){
                setString+=",ProcessNote='"+ object.getProcessNote()+"' ";
            }
            if(object.getStartDate()!=null){
                setString+=",StartDate='"+format.format(object.getStartDate())+"' ";
            }
            if(object.getEndDate()!=null){
                setString+=",EndDate='"+format.format(object.getCreateDate())+"' ";
            }
            if(!setString.equalsIgnoreCase("")){
                sql="UPDATE " + tableName+ " SET  PositionGPS='"+object.getPositionGPS()+"',PositionName='"+object.getPositionName()+"' ";
                sql+=setString+" WHERE ID="+processMid.getId();
            }
        }
       /* else {

            sql = "insert into " + tableName + " (CreateDate, PositionGPS, ProcessNote,  StyleProcessID,StartDate,EndDate, SubComponentID, UserID)" +  // EndDate,StartDate,
                    " values ('" + format.format(object.getCreateDate()) + "','" + object.getPositionGPS() +"','"+
                    object.getProcessNote() + "','" + object.getStyleProcessID() + "',";
            if (object.getStartDate() != null) {
                sql += "'" + format.format(object.getStartDate()) + "',";
            } else {
                sql += "'',";
            }
            if (object.getEndDate() != null) {

                sql += "'" + format.format(object.getEndDate()) + "',";
            } else {
                sql += "'',";
            }
            sql += "'" + object.getSubComponentID() + "','" + object.getUserID() + "')";
        }*/
        SQLQuery query = getSession().createSQLQuery(sql);
        query.addEntity(ProcessMid.class);
        int updated=query.executeUpdate();
        ProcessMid processMid2=getProcessMid(object.getSubComponentID(),object.getStyleProcessID(),tableName);
        return processMid2;
    }

    @Override
    public List<Object[]> getComponentStylesOfProject(String projectID, String taskType, String tableName) {
        String sql="select pmt.styleID,rc.StyleName from "+tableName+"  pmt,R_ComponentStyle rc " +
                "where pmt.StyleID=rc.StyleID and pmt.projectID='"+projectID+"' ";

        if(taskType.equalsIgnoreCase("doing")){
            sql+=" and (pmt.StartDate  is  null or pmt.EndDate is  null) ";
        }
        else if(taskType.equalsIgnoreCase("finish")){
            sql+=" and pmt.StartDate is not null and pmt.EndDate is not  null ";
        }
       sql+=" group by pmt.styleID,rc.styleName ";
        Query query=getSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getProjectListOfUser(String taskType, String tableName) {
        String sql="select pmt.projectID,rp.projectpathname from "+tableName+" pmt,R_project rp where pmt.ProjectID=rp.projectID ";
        if(taskType.equalsIgnoreCase("doing")){
            sql+=" and (pmt.StartDate  is  null or pmt.EndDate is  null) ";
        }
        else if(taskType.equalsIgnoreCase("finish")){
            sql+=" and pmt.StartDate is not null and pmt.EndDate is not  null ";
        }

        sql+=" group by pmt.ProjectID,rp.ProjectPathName";
        Query query=getSession().createSQLQuery(sql);
        return query.list();
    }
}
