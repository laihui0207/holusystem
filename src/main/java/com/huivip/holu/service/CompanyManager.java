package com.huivip.holu.service;

import com.huivip.holu.model.Company;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/companies")
public interface CompanyManager extends GenericManager<Company, Long> {

    Company getCompanyByCompanyID(String companyID);

    @GET
    @Path("signup/company")
    List<Company> companyList();
    @GET
    @Path("signup/valid/{note}")
    boolean isValidCompanyNote(@PathParam("note") String note);

    Company getCompanyFromNote(String note);
}