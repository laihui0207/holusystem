package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.model.ProcessMid;
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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if(null!=processMid){
            sql="UPDATE " + tableName+
                    " SET  PostionGPS='"+object.getPositionGPS()+"', ProcessNote='"+  //EndDate='"+format.format(object.getCreateDate())+"',
                    object.getProcessNote()+"' " +  //, StartDate='"+format.format(object.getStartDate())+"'
                    "WHERE ID="+processMid.getId();
        }
        else {
            sql = "insert into " + tableName + " (CreateDate, PostionGPS, ProcessNote,  StyleProcessID, SubComponentID, UserID)" +  // EndDate,StartDate,
                    " values ('" + format.format(object.getCreateDate()) + "','" + object.getPositionGPS() + "','" +  //+ format.format(object.getEndDate()) + "','"
                    object.getProcessNote() + "','"  + object.getStyleProcessID() + "','" +  //+ format.format(object.getStartDate()) + "','"
                    object.getSubComponentID() + "','" + object.getUser().getUserID() + "')";
        }
        SQLQuery query = getSession().createSQLQuery(sql);
        query.addEntity(ProcessMid.class);
        query.executeUpdate();
        processMid=getProcessMid(object.getSubComponentID(),object.getStyleProcessID(),tableName);
        return processMid;
    }
}
