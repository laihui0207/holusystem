package com.huivip.holu.service;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.SelectLabelValue;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/groups")
public interface CustomGroupManager extends GenericManager<CustomGroup, Long> {
    @GET
    @Path("slv")
    List<SelectLabelValue> getGroups();

    CustomGroup getCustomGroupByGroupId(String groupID);
}