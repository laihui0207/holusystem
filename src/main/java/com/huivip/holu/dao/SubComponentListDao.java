package com.huivip.holu.dao;

import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the SubComponentList table.
 */
public interface SubComponentListDao extends GenericDao<SubComponentList, Long> {
    List<SubComponentList> getSubComponentListByComponentID(String componentID,String tableName,ExtendedPaginatedList list);
    SubComponentList getSubComponentBySubComponentID(String subComponentID,String tableName);
    String getParentComponentId(String SubComponentId, String tableName);

}