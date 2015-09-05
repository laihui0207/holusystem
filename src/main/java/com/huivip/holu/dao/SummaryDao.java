package com.huivip.holu.dao;

import com.huivip.holu.model.Summary;

import java.util.List;

/**
 * An interface that provides a data management interface to the Summary table.
 */
public interface SummaryDao extends GenericDao<Summary, Long> {
    public List<Summary> summaryList(String tableName);

}