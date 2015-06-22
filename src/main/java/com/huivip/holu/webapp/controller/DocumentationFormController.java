package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.DocType;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.DocTypeManager;
import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.SteelConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Controller
@RequestMapping("/documentationform*")
public class DocumentationFormController extends BaseFormController {
    private DocumentationManager documentationManager = null;
    @Autowired
    private DocTypeManager docTypeManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserGroupManager userGroupManager;

    @Autowired
    public void setDocumentationManager(DocumentationManager documentationManager) {
        this.documentationManager = documentationManager;
    }

    public DocumentationFormController() {
        setCancelView("redirect:documentations");
        setSuccessView("redirect:documentations");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Documentation showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return documentationManager.get(new Long(id));
        }

        return new Documentation();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Documentation documentation, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        /*if (validator != null) { // validator is null during testing
            validator.validate(documentation, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "documentationform";
            }
        }*/

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (documentation.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            deleteUploadFile(documentation);
            documentationManager.remove(documentation.getId());
            saveMessage(request, getText("documentation.deleted", locale));
        } else {

            if(documentation.isViewLimit()){
                String[] viewUsers=request.getParameterValues("viewUsers");
                String[] viewUserGroups=request.getParameterValues("viewUserGroups");
                if(null!=viewUsers){
                    if(null!=documentation.getViewUsers()){
                        documentation.getViewUsers().clear();
                    }
                    for(String userId:viewUsers){
                        documentation.getViewUsers().add(userManager.get(Long.parseLong(userId)));
                    }
                }
                if(null!=viewUserGroups){
                    if(null!=documentation.getViewUserGroups()){
                        documentation.getViewUserGroups().clear();
                    }
                    for(String userGroupId:viewUserGroups){
                        documentation.getViewUserGroups().add(userGroupManager.get(Long.parseLong(userGroupId)));
                    }
                }
            }

            handleUploadFile(request,documentation);
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            documentation.setCreater(cleanUser);
            documentation.setUpdater(cleanUser);
            documentationManager.save(documentation);
            String key = (isNew) ? "documentation.added" : "documentation.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:documentationform?id=" + documentation.getId();
            }
        }

        return success;
    }
    private void deleteUploadFile(Documentation documentation){
        if(documentation.getStorePath()==null || documentation.getStorePath().length()==0) return;
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.DocumentManagerDirectory);
        String uploadDir=getServletContext().getRealPath("/");
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+documentation.getStorePath());
        if(null!=file){
            file.delete();
        }
    }
    private void handleUploadFile(HttpServletRequest request,Documentation documentation){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.DocumentManagerDirectory);
        String uploadDir=getServletContext().getRealPath("/");
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        uploadDir+="/Documents";
        String saveUrl = request.getContextPath()+"/Documents";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
        if(null==file || file.getSize()==0 ) return;
        String fileName = file.getOriginalFilename();
        documentation.setFileName(fileName);
        documentation.setDocSize(Integer.parseInt(file.getSize()+""));

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String newFileName =fileName+"_"+ df.format(new Date()) + "_"
                + new Random().nextInt(1000) + "." + fileExt;

        uploadDir += "/" + request.getRemoteUser() +"/"+df.format(new Date())+"/";
        saveUrl+= "/" + request.getRemoteUser() +"/"+df.format(new Date())+"/";
        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        //retrieve the file data
        InputStream stream = null;
        try {
            stream = file.getInputStream();

            //write the file to the file specified
            OutputStream bos = new FileOutputStream(uploadDir + newFileName);
            int bytesRead;
            byte[] buffer = new byte[8192];

            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.close();

            //close the stream
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        documentation.setStorePath(saveUrl+newFileName);

    }
    @ModelAttribute("docTypeList")
    public List<DocType> docTypeList(){
       return docTypeManager.getAll();
    }

    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("userGroupList")
    public List<UserGroup> userGroupList(){
        return userGroupManager.getAll();
    }
}
