package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sunlaihui on 7/21/15.
 */
@Entity
@Table(name="R_ProjectIndex")
@Indexed
public class ProjectIndex extends  BaseObject implements Serializable {
    Long id;
    String projectID;
    String midTableName;
    String partListTableName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="ProjectID")
    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    @Column(name="ProcessMidTableName")
    public String getMidTableName() {
        return midTableName;
    }

    public void setMidTableName(String midTableName) {
        this.midTableName = midTableName;
    }
    @Column(name="PartListTableName")
    public String getPartListTableName() {
        return partListTableName;
    }

    public void setPartListTableName(String partListTableName) {
        this.partListTableName = partListTableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectIndex that = (ProjectIndex) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (projectID != null ? !projectID.equals(that.projectID) : that.projectID != null) return false;
        if (midTableName != null ? !midTableName.equals(that.midTableName) : that.midTableName != null) return false;
        return !(partListTableName != null ? !partListTableName.equals(that.partListTableName) : that.partListTableName != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectID != null ? projectID.hashCode() : 0);
        result = 31 * result + (midTableName != null ? midTableName.hashCode() : 0);
        result = 31 * result + (partListTableName != null ? partListTableName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PorjectIndex{" +
                "id=" + id +
                ", itemID='" + projectID + '\'' +
                ", midTableName='" + midTableName + '\'' +
                ", partListTableName='" + partListTableName + '\'' +
                '}';
    }
}
