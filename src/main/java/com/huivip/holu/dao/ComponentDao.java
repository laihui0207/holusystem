package com.huivip.holu.dao;

import com.huivip.holu.model.Component;

import java.util.List;

/**
 * An interface that provides a data management interface to the Component table.
 */
public interface ComponentDao extends GenericDao<Component, Long> {
    public List<Component> listComponentByProject(String projectID);

}