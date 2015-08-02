package com.huivip.holu.service;

import com.huivip.holu.model.ProcessMid;

import javax.jws.WebService;

@WebService
public interface ProcessMidManager extends GenericManager<ProcessMid, Long> {
   ProcessMid save(ProcessMid object,String userID);

   ProcessMid getProcessMid(String componentID,String processID,String userID);
}