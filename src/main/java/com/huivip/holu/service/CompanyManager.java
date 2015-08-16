package com.huivip.holu.service;

import com.huivip.holu.model.Company;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/companies")
public interface CompanyManager extends GenericManager<Company, Long> {

    Company getCompanyByCompanyID(String companyID);

    @GET
    @Path("signup/company")
    List<Company> companyList();
}