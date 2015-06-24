package com.huivip.holu.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sunlaihui on 6/15/15.
 * SELECT TOP 1000 [ID]
 * ,[ComponentID]
 * ,[ProjectID]
 * ,[ComponentName]
 * ,[Size]
 * ,[Material]
 * ,[Length]
 * ,[Quantity]
 * ,[Weight]
 * ,[Price]
 * ,[StyleName]
 * ,[PieceList]
 * ,[UserID]
 * ,[CreateDate]
 * FROM [MidDatabase].[dbo].[U_HOLU_ComponentList]
 */
@Entity
@Table(name = "U_HOLU_ComponentList")
@Indexed
@XmlRootElement
public class Component extends BaseObject implements Serializable {
    Long id;
    String componentID;
    Project project;
    String projectID;
    String componentName;
    float size;
    String material;
    float length;
    String quantity;
    float weight;
    float price;
    String styleName;
    String pieceList;
    User creater;
    Timestamp createDate = new Timestamp(new Date().getTime());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }
    @ManyToOne
    @JoinColumn(name="Project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componetName) {
        this.componentName = componetName;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getPieceList() {
        return pieceList;
    }

    public void setPieceList(String pieceList) {
        this.pieceList = pieceList;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User user) {
        this.creater = user;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (Float.compare(component.size, size) != 0) return false;
        if (Float.compare(component.length, length) != 0) return false;
        if (Float.compare(component.weight, weight) != 0) return false;
        if (Float.compare(component.price, price) != 0) return false;
        if (id != null ? !id.equals(component.id) : component.id != null) return false;
        if (componentID != null ? !componentID.equals(component.componentID) : component.componentID != null)
            return false;
        if (project != null ? !project.equals(component.project) : component.project != null) return false;
        if (componentName != null ? !componentName.equals(component.componentName) : component.componentName != null)
            return false;
        if (material != null ? !material.equals(component.material) : component.material != null) return false;
        if (quantity != null ? !quantity.equals(component.quantity) : component.quantity != null) return false;
        if (styleName != null ? !styleName.equals(component.styleName) : component.styleName != null) return false;
        if (pieceList != null ? !pieceList.equals(component.pieceList) : component.pieceList != null) return false;
        if (creater != null ? !creater.equals(component.creater) : component.creater != null) return false;
        return !(createDate != null ? !createDate.equals(component.createDate) : component.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (componentID != null ? componentID.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        result = 31 * result + (size != +0.0f ? Float.floatToIntBits(size) : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (length != +0.0f ? Float.floatToIntBits(length) : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (styleName != null ? styleName.hashCode() : 0);
        result = 31 * result + (pieceList != null ? pieceList.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", componentID='" + componentID + '\'' +
                ", project=" + project +
                ", componetName='" + componentName + '\'' +
                ", size=" + size +
                ", material='" + material + '\'' +
                ", length=" + length +
                ", quantity='" + quantity + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", styleName='" + styleName + '\'' +
                ", pieceList='" + pieceList + '\'' +
                ", user=" + creater +
                ", createDate=" + createDate +
                '}';
    }
}
