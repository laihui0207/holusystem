package com.huivip.holu.model;

import com.huivip.holu.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 9/26/15.
 */
public class SummaryProcess implements Serializable {
    String itemID;
    String itemName;
    Date sumDate_plan_start;
    Date sumDate_plan_end;
    Date sumDate_actual_start;
    Date sumDate_actual_end;
    Date sumDate_predict_start;
    Date sumDate_predict_end;
    int planDuration;
    int actualDuration;
    int predictDuration;
    int currentStatus;
    int predictStatus;

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getSumDate_plan_start() {
        return sumDate_plan_start;
    }

    public void setSumDate_plan_start(Date sumDate_plan_start) {
        this.sumDate_plan_start = sumDate_plan_start;
    }

    public Date getSumDate_plan_end() {
        return sumDate_plan_end;
    }

    public void setSumDate_plan_end(Date sumDate_plan_end) {
        this.sumDate_plan_end = sumDate_plan_end;
    }

    public Date getSumDate_actual_start() {
        return sumDate_actual_start;
    }

    public void setSumDate_actual_start(Date sumDate_actual_start) {
        this.sumDate_actual_start = sumDate_actual_start;
    }

    public Date getSumDate_actual_end() {
        return sumDate_actual_end;
    }

    public void setSumDate_actual_end(Date sumDate_actual_end) {
        this.sumDate_actual_end = sumDate_actual_end;
    }

    public Date getSumDate_predict_start() {
        return sumDate_predict_start;
    }

    public void setSumDate_predict_start(Date sumDate_predict_start) {
        this.sumDate_predict_start = sumDate_predict_start;
    }

    public Date getSumDate_predict_end() {
        return sumDate_predict_end;
    }

    public void setSumDate_predict_end(Date sumDate_predict_end) {
        this.sumDate_predict_end = sumDate_predict_end;
    }

    public int getPlanDuration() {
        if(sumDate_plan_start==null || sumDate_plan_end==null) return 0;
        return DateUtil.betweenofTwoDate(sumDate_plan_start,sumDate_plan_end)+1;
    }

    public int getActualDuration() {
        if(sumDate_actual_end==null || sumDate_actual_start==null) return 0;
        return DateUtil.betweenofTwoDate(sumDate_actual_start,sumDate_actual_end)+1;
    }

    public int getPredictDuration() {
        if(sumDate_predict_end==null || sumDate_predict_start==null) return 0;
        return DateUtil.betweenofTwoDate(sumDate_predict_start,sumDate_predict_end)+1;
    }

    public int getCurrentStatus() {
        if(sumDate_actual_end==null || sumDate_plan_end==null) return 0;
        return DateUtil.betweenofTwoDate(sumDate_actual_end,sumDate_plan_end);
    }

    public int getPredictStatus() {
        if(sumDate_predict_end==null || sumDate_plan_end==null) return 0;
        return DateUtil.betweenofTwoDate(sumDate_predict_end,sumDate_plan_end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SummaryProcess that = (SummaryProcess) o;

        if (itemID != null ? !itemID.equals(that.itemID) : that.itemID != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null)
            return false;
        if (sumDate_plan_start != null ? !sumDate_plan_start.equals(that.sumDate_plan_start) : that.sumDate_plan_start != null)
            return false;
        if (sumDate_plan_end != null ? !sumDate_plan_end.equals(that.sumDate_plan_end) : that.sumDate_plan_end != null)
            return false;
        if (sumDate_actual_start != null ? !sumDate_actual_start.equals(that.sumDate_actual_start) : that.sumDate_actual_start != null)
            return false;
        if (sumDate_actual_end != null ? !sumDate_actual_end.equals(that.sumDate_actual_end) : that.sumDate_actual_end != null)
            return false;
        if (sumDate_predict_start != null ? !sumDate_predict_start.equals(that.sumDate_predict_start) : that.sumDate_predict_start != null)
            return false;
        return !(sumDate_predict_end != null ? !sumDate_predict_end.equals(that.sumDate_predict_end) : that.sumDate_predict_end != null);

    }

    @Override
    public int hashCode() {
        int result = itemID != null ? itemID.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (sumDate_plan_start != null ? sumDate_plan_start.hashCode() : 0);
        result = 31 * result + (sumDate_plan_end != null ? sumDate_plan_end.hashCode() : 0);
        result = 31 * result + (sumDate_actual_start != null ? sumDate_actual_start.hashCode() : 0);
        result = 31 * result + (sumDate_actual_end != null ? sumDate_actual_end.hashCode() : 0);
        result = 31 * result + (sumDate_predict_start != null ? sumDate_predict_start.hashCode() : 0);
        result = 31 * result + (sumDate_predict_end != null ? sumDate_predict_end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SummaryProcess{" +
                "itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", sumDate_plan_start=" + sumDate_plan_start +
                ", sumDate_plan_end=" + sumDate_plan_end +
                ", sumDate_actual_start=" + sumDate_actual_start +
                ", sumDate_actual_end=" + sumDate_actual_end +
                ", sumDate_predict_start=" + sumDate_predict_start +
                ", sumDate_predict_end=" + sumDate_predict_end +
                '}';
    }

}
