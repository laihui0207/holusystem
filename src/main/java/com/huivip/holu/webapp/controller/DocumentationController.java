package com.huivip.holu.webapp.controller;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.service.UserManager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/documentations*")
public class DocumentationController {
    private DocumentationManager documentationManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setDocumentationManager(DocumentationManager documentationManager) {
        this.documentationManager = documentationManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        User user= userManager.getUserByUsername(request.getRemoteUser());
        try {
            if(!request.isUserInRole(Constants.ADMIN_ROLE)){
                List<Documentation> resultList=new ArrayList<>();
                if(null==query || query==""){
                    resultList=documentationManager.myDocumentations(user.getId().toString());
                }
                else {
                    List<Documentation> documentationList = documentationManager.search(query, Documentation.class);
                    for (Documentation documentation : documentationList) {
                        if (documentation.getCreater().getUsername().equalsIgnoreCase(request.getRemoteUser())) {
                            resultList.add(documentation);
                        }
                    }
                }
                model.addAttribute(resultList);
            }
            else {
                model.addAttribute(documentationManager.search(query, Documentation.class));
            }
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(documentationManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET,value = "{id}/Download")
    public String downloadDocumentation(@PathVariable("id") String id,HttpServletRequest request,HttpServletResponse response){
        final User cleanUser = userManager.getUserByUsername(
                request.getRemoteUser());

        Documentation documentation=documentationManager.get(Long.parseLong(id));
        boolean canDownload=true;
        if(null!=documentation && documentation.isViewLimit()){
            canDownload=false;
            if(!documentation.getViewUsers().contains(cleanUser)){
                for(UserGroup userGroup:documentation.getViewUserGroups()){
                    if(userGroup.getMembers().contains(cleanUser)){
                        canDownload=true;
                        break;
                    }
                }
            }
        }
        if(!canDownload){
            Locale locale = request.getLocale();
            List errors = (List) request.getSession().getAttribute(BaseFormController.ERRORS_MESSAGES_KEY);
            if (errors == null) {
                errors = new ArrayList();
            }
            errors.add("Downlaod.notAllow");
            request.getSession().setAttribute(BaseFormController.ERRORS_MESSAGES_KEY, errors);

            return "redirect:/documentations";
        }
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.DocumentManagerDirectory);
        String uploadDir=request.getServletContext().getRealPath("/");
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+documentation.getStorePath());
        //response.setContentType(file.getType());
        response.setContentLength(Integer.parseInt(file.length()+""));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + documentation.getFileName() + "\"");

        try {
            FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
