package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

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
            clientVersionManager.save(clientVersion);
            String key = (isNew) ? "clientVersion.added" : "clientVersion.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:clientVersionform?id=" + clientVersion.getId();
            }
        }

        return success;
    }
}
