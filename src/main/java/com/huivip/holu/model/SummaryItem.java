package com.huivip.holu.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by sunlaihui on 9/20/15.
 */
public class SummaryItem implements Serializable {
    String itemName;
    String itemID;
    String processID;
    String itemStyle;
    double actual;
    double plan;
    Date sumDate;
    String percent;
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(String itemStyle) {
        this.itemStyle = itemStyle;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public double getPlan() {
        return plan;
    }

    public void setPlan(double plan) {
        this.plan = plan;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

    public String getPercent() {
        DecimalFormat df = new DecimalFormat("##.0%");
        if(plan!=0){
            double data=actual*1.0/plan*1.0;
            return df.format(data);
        }
        return "0%";
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SummaryItem that = (SummaryItem) o;

        if (Double.compare(that.actual, actual) != 0) return false;
        if (Double.compare(that.plan, plan) != 0) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemID != null ? !itemID.equals(that.itemID) : that.itemID != null) return false;
        if (itemStyle != null ? !itemStyle.equals(that.itemStyle) : that.itemStyle != null) return false;
        return !(sumDate != null ? !sumDate.equals(that.sumDate) : that.sumDate != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemName != null ? itemName.hashCode() : 0;
        result = 31 * result + (itemID != null ? itemID.hashCode() : 0);
        result = 31 * result + (itemStyle != null ? itemStyle.hashCode() : 0);
        temp = Double.doubleToLongBits(actual);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(plan);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sumDate != null ? sumDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SummaryItem{" +
                "itemName='" + itemName + '\'' +
                ", itemID='" + itemID + '\'' +
                ", itemStyle='" + itemStyle + '\'' +
                ", actual=" + actual +
                ", plan=" + plan +
                ", sumDate=" + sumDate +
                '}';
    }
}
