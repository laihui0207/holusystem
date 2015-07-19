package com.huivip.holu.dao;

import com.huivip.holu.model.Documentation;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the Documentation table.
 */
public interface DocumentationDao extends GenericDao<Documentation, Long> {

    List<Documentation> myDocumentations(String userId,ExtendedPaginatedList list);

}