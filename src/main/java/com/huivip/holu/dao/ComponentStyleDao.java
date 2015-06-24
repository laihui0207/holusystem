package com.huivip.holu.dao;

import com.huivip.holu.model.ComponentStyle;

import java.util.List;

/**
 * An interface that provides a data management interface to the ComponentStyle table.
 */
public interface ComponentStyleDao extends GenericDao<ComponentStyle, Long> {
    List<ComponentStyle> getComponentStypeListByCompany(String companyId);

}