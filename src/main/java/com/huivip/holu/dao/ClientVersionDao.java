package com.huivip.holu.dao;

import com.huivip.holu.model.ClientVersion;

/**
 * An interface that provides a data management interface to the ClientVersion table.
 */
public interface ClientVersionDao extends GenericDao<ClientVersion, Long> {
    ClientVersion getClientByVersion(String version);
    ClientVersion getLastedClient();

}