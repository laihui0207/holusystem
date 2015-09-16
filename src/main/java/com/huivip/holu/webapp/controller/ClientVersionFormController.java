package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.model.LabelValue;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.util.SteelConfig;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
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
import java.util.*;

@Controller
@RequestMapping("/clientVersionform*")
public class ClientVersionFormController extends BaseFormController {
    private ClientVersionManager clientVersionManager = null;

    @Autowired
    public void setClientVersionManager(ClientVersionManager clientVersionManager) {
        this.clientVersionManager = clientVersionManager;
    }

    public ClientVersionFormController() {
        setCancelView("redirect:clientVersions");
        setSuccessView("redirect:clientVersions");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ClientVersion showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return clientVersionManager.get(new Long(id));
        }

        return new ClientVersion();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ClientVersion clientVersion, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(clientVersion, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "clientVersionform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (clientVersion.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            clientVersionManager.remove(clientVersion.getId());
            saveMessage(request, getText("clientVersion.deleted", locale));
        } else {
            handleUploadFile(request,clientVersion,isNew);
            handleQRCode(request,clientVersion);
            clientVersionManager.save(clientVersion);
            String key = (isNew) ? "clientVersion.added" : "clientVersion.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:clientVersionform?id=" + clientVersion.getId();
            }

        }
        ClientVersion lastedClient=clientVersionManager.getLastedClient();
        lastedClient.setVersion("lasted");
        handleQRCode(request,lastedClient);
        return success;
    }
    @ModelAttribute("clientTypes")
    public List<LabelValue> clientTypes(){
       List<LabelValue> list=new ArrayList<>();
        LabelValue ios=new LabelValue("IOS","ios");
        list.add(ios);
        LabelValue android=new LabelValue("Android","android");
        list.add(android);
        return list;
    }
    private void handleQRCode(HttpServletRequest request,ClientVersion clientVersion){
        String contextPath=request.getContextPath();
        String path=request.getRequestURL().toString();
        String requestPath=path.substring(0,path.indexOf(contextPath)+contextPath.length());
        String clientDownloadRequest=requestPath+"/services/api/client/"+clientVersion.getVersion()+"/download.json";

        String configQRcodeFilePath=SteelConfig.getConfigure(SteelConfig.EditorAttachedDirectory);
        String QRCodeFilePath=getServletContext().getRealPath("/");
        if(null!=configQRcodeFilePath && configQRcodeFilePath.length()>0){
            QRCodeFilePath=configQRcodeFilePath;
        }
        QRCodeFilePath+="/attached/client/";
        File QRPath=new File(QRCodeFilePath);
        if(!QRPath.exists()){
            QRPath.mkdirs();
        }
        String QRFileName=clientVersion.getVersion()+".png";
        File QRFile=new File(QRCodeFilePath+QRFileName);
        if(QRFile.exists()){
            QRFile.delete();
        }
        try {
            QRFile.createNewFile();
            FileOutputStream outputStream=new FileOutputStream(QRFile);
            QRCode.from(clientDownloadRequest).to(ImageType.PNG).writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientVersion.setQRCode("/attached/client/"+QRFileName);


    }
    private void handleUploadFile(HttpServletRequest request,ClientVersion clientVersion,boolean isNew){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.ClientDirectory);
        String uploadDir=getServletContext().getRealPath("/");
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        uploadDir+="/clientManager";
        String saveUrl = "/clientManager";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
        if(null==file || file.getSize()==0 ){
            if(!isNew){
                ClientVersion clientVersion1=clientVersionManager.get(clientVersion.getId());
                clientVersion.setClientUrl(clientVersion1.getClientUrl());
                clientVersion.setStorePath(clientVersion1.getStorePath());
                clientVersion.setQRCode(clientVersion1.getQRCode());
            }
            return;
        }
        String fileName = file.getOriginalFilename();

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
        clientVersion.setStorePath(saveUrl+newFileName);



    }
}
