package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.util.SteelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/clientVersions*")
public class ClientVersionController {
    private ClientVersionManager clientVersionManager;

    @Autowired
    public void setClientVersionManager(ClientVersionManager clientVersionManager) {
        this.clientVersionManager = clientVersionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(clientVersionManager.search(query, ClientVersion.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(clientVersionManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET,value = "{clientVersion}/download")
    public String Download(@PathVariable("clientVersion") String version,HttpServletRequest request,HttpServletResponse response){
        ClientVersion client=clientVersionManager.getClientByVersion(version);
        if(client==null) return null;

        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.ClientDirectory);
        String uploadDir=request.getServletContext().getRealPath("/");
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+client.getStorePath());
        //response.setContentType(file.getType());
        response.setContentLength(Integer.parseInt(file.length()+""));
        response.setHeader("Content-Disposition", "attachment; filename=\"ICMS2015.apk\"");

        try {
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
