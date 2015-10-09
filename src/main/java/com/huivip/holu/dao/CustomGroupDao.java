package com.huivip.holu.dao;

import com.huivip.holu.model.CustomGroup;

import java.util.List;

/**
 * An interface that provides a data management interface to the CustomGroup table.
 */
public interface CustomGroupDao extends GenericDao<CustomGroup, Long> {

    CustomGroup getGroupByGroupID(String groupID);
    List<CustomGroup> getGroupByCompany(String companyID);

}