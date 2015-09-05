package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.User;
import com.huivip.holu.service.SummaryManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/summaries*")
public class SummaryController {
    private SummaryManager summaryManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setSummaryManager(SummaryManager summaryManager) {
        this.summaryManager = summaryManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query, HttpServletRequest request)
            throws Exception {
        Model model = new ExtendedModelMap();
        User user = userManager.getUserByLoginCode(request.getRemoteUser());
        model.addAttribute(summaryManager.summaryList(user.getUserID()));
        return model;
    }
}
