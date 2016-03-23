package com.huivip.holu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlaihui on 8/28/15.
 */
public class Mission implements Serializable {
    private Component component;
    private String componentName;
    private String projectID;
    private String projectPathName;

    private String componentType;
/*    private String projectChain;*/
    private String type;
/*    private SubComponentList subComponent;*/
    private String subComponentName;
    private String subComponentID;
    private ComponentStyle componentStyle;
    private String componentId;
    private String styleProcessID;
    private String styleName;
    private String processName;
    private Date startDate;
    private Date endDate;
/*    private User user;*/
    /*private String userId;
    private String userName;*/
    private ProcessMid processMid;
    private boolean owner;
    private boolean operater;
    private List<String> subComponentList=new ArrayList<>();

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    /*public SubComponentList getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(SubComponentList subComponent) {
        this.subComponent = subComponent;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public ComponentStyle getComponentStyle() {
        return componentStyle;
    }

    public void setComponentStyle(ComponentStyle componentStyle) {
        this.componentStyle = componentStyle;
    }

    /*public String getProjectChain() {
        return projectChain;
    }

    public void setProjectChain(String projectChain) {
        this.projectChain = projectChain;
    }*/

    public ProcessMid getProcessMid() {
        return processMid;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectPathName() {
        return projectPathName;
    }

    public void setProjectPathName(String projectPathName) {
        this.projectPathName = projectPathName;
    }

    public String getSubComponentName() {
        return subComponentName;
    }

    public void setSubComponentName(String subComponentName) {
        this.subComponentName = subComponentName;
    }

    public String getSubComponentID() {
        return subComponentID;
    }

    public void setSubComponentID(String subComponentID) {
        this.subComponentID = subComponentID;
    }

    public String getStyleProcessID() {
        return styleProcessID;
    }

    public void setStyleProcessID(String styleProcessID) {
        this.styleProcessID = styleProcessID;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStyleName() {
        return styleName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public boolean isOwner() {
        return owner;
    }

    public boolean isOperater() {
        return operater;
    }

    public void setOperater(boolean operater) {
        this.operater = operater;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<String> getSubComponentList() {
        return subComponentList;
    }

    public void setSubComponentList(List<String> subComponentList) {
        this.subComponentList = subComponentList;
    }

    public void setProcessMid(ProcessMid processMid) {
        this.processMid = processMid;
    }
}
