package com.huivip.holu.service;

import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface SubComponentListManager extends GenericManager<SubComponentList, Long> {

    List<SubComponentList> getSubComponentListByComponentID(String componentID,String userID,ExtendedPaginatedList list);
    SubComponentList getSubComponentBySubComponentID(String subComponentID,String userID);
    
}