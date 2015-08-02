package com.huivip.holu.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    String componentName;
    String size;
    String material;
    String length;
    Integer quantity;
    float weight;
    float price;
    String styleID;
    String pieceList;
    User creater;
    Timestamp createDate = new Timestamp(new Date().getTime());
    Set<SubComponentList> subComponentListSet=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="ComponentID")
    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }
    @ManyToOne
    @JoinColumn(name="ProjectID",referencedColumnName = "projectID")
    @Fetch(FetchMode.SELECT)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    @Column(name="ComponentName")
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componetName) {
        this.componentName = componetName;
    }
    @Column(name="Size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    @Column(name="Material")
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    @Column(name="Length")
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
    @Column(name="Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Column(name="Weight")
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    @Column(name="Price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @Column(name="StyleID")
    public String getStyleID() {
        return styleID;
    }

    public void setStyleID(String styleName) {
        this.styleID = styleName;
    }
    @Column(name="PieceList")
    public String getPieceList() {
        return pieceList;
    }

    public void setPieceList(String pieceList) {
        this.pieceList = pieceList;
    }
    @ManyToOne
    @JoinColumn(name="UserID",referencedColumnName = "userID")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User user) {
        this.creater = user;
    }
    @Column(name="CreateDate")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @OneToMany(mappedBy = "parentComponent")
    public Set<SubComponentList> getSubComponentListSet() {
        return subComponentListSet;
    }

    public void setSubComponentListSet(Set<SubComponentList> subComponentListSet) {
        this.subComponentListSet = subComponentListSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

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
        if (styleID != null ? !styleID.equals(component.styleID) : component.styleID != null) return false;
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
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (styleID != null ? styleID.hashCode() : 0);
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
                ", styleName='" + styleID + '\'' +
                ", pieceList='" + pieceList + '\'' +
                ", user=" + creater +
                ", createDate=" + createDate +
                '}';
    }
}
