package com.huivip.holu.service;

import com.huivip.holu.model.Summary;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/summary")
public interface SummaryManager extends GenericManager<Summary, Long> {
   @GET
   List<Summary> summaryList(String userId);
}