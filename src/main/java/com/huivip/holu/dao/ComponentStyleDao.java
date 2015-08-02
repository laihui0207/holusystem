package com.huivip.holu.dao;

import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the ComponentStyle table.
 */
public interface ComponentStyleDao extends GenericDao<ComponentStyle, Long> {
    List<ComponentStyle> getComponentStypeListByCompany(String companyId);
    List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleName,String companyId,ExtendedPaginatedList list);
    ComponentStyle getComponentProcessByProcessID(String styleProcessID);

}