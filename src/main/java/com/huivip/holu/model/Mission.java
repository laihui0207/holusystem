package com.huivip.holu.model;

import java.io.Serializable;

/**
 * Created by sunlaihui on 8/28/15.
 */
public class Mission implements Serializable {
    private Component component;
    private String componentType;
    private String projectChain;
    private String type;
    private SubComponentList subComponent;
    private ComponentStyle componentStyle;
    private String componentId;
    private User user;
    private ProcessMid processMid;

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

    public SubComponentList getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(SubComponentList subComponent) {
        this.subComponent = subComponent;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ComponentStyle getComponentStyle() {
        return componentStyle;
    }

    public void setComponentStyle(ComponentStyle componentStyle) {
        this.componentStyle = componentStyle;
    }

    public String getProjectChain() {
        return projectChain;
    }

    public void setProjectChain(String projectChain) {
        this.projectChain = projectChain;
    }

    public ProcessMid getProcessMid() {
        return processMid;
    }

    public void setProcessMid(ProcessMid processMid) {
        this.processMid = processMid;
    }
}
