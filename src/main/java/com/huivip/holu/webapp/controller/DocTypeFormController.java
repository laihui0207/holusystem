package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.DocType;
import com.huivip.holu.model.User;
import com.huivip.holu.service.DocTypeManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/docTypeform*")
public class DocTypeFormController extends BaseFormController {
    private DocTypeManager docTypeManager = null;

    @Autowired
    public void setDocTypeManager(DocTypeManager docTypeManager) {
        this.docTypeManager = docTypeManager;
    }

    public DocTypeFormController() {
        setCancelView("redirect:docTypes");
        setSuccessView("redirect:docTypes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected DocType showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return docTypeManager.get(new Long(id));
        }

        return new DocType();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(DocType docType, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(docType, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "docTypeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (docType.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            docTypeManager.remove(docType.getId());
            saveMessage(request, getText("docType.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            docType.setCreater(cleanUser);
            docTypeManager.save(docType);
            String key = (isNew) ? "docType.added" : "docType.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:docTypeform?id=" + docType.getId();
            }
        }

        return success;
    }
}
