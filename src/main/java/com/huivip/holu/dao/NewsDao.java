package com.huivip.holu.dao;

import com.huivip.holu.model.News;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * An interface that provides a data management interface to the News table.
 */
public interface NewsDao extends GenericDao<News, Long> {

    List<News> getNewsByType(@PathParam("typeId") String typeID);
    List<News> getNewsPageable(ExtendedPaginatedList list);
    int getNewsCount();

}