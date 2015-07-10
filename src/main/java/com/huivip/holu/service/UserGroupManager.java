package com.huivip.holu.service;

import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.UserGroup;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/usergroups")
public interface UserGroupManager extends GenericManager<UserGroup, Long> {

    @GET
    List<UserGroup> userGroups();

    @GET
    @Path("slv")
    List<SelectLabelValue> listUserGroupSLV();
}