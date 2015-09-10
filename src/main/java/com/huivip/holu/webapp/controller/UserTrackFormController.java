package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.UserTrackManager;
import com.huivip.holu.model.UserTrack;
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
@RequestMapping("/userTrackform*")
public class UserTrackFormController extends BaseFormController {
    private UserTrackManager userTrackManager = null;

    @Autowired
    public void setUserTrackManager(UserTrackManager userTrackManager) {
        this.userTrackManager = userTrackManager;
    }

    public UserTrackFormController() {
        setCancelView("redirect:userTracks");
        setSuccessView("redirect:userTracks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected UserTrack showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return userTrackManager.get(new Long(id));
        }

        return new UserTrack();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(UserTrack userTrack, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(userTrack, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "userTrackform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (userTrack.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            userTrackManager.remove(userTrack.getId());
            saveMessage(request, getText("userTrack.deleted", locale));
        } else {
            userTrackManager.save(userTrack);
            String key = (isNew) ? "userTrack.added" : "userTrack.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:userTrackform?id=" + userTrack.getId();
            }
        }

        return success;
    }
}
