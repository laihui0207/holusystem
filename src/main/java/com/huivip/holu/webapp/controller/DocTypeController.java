package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.DocType;
import com.huivip.holu.service.DocTypeManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/docTypes*")
public class DocTypeController {
    private DocTypeManager docTypeManager;
    @Autowired
    PaginateListFactory paginateListFactory;

    @Autowired
    public void setDocTypeManager(DocTypeManager docTypeManager) {
        this.docTypeManager = docTypeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        try {
            docTypeManager.search(query, DocType.class,list);
            model.addAttribute("docTypeList",list);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            docTypeManager.getAllPageable(list);
            model.addAttribute("docTypeList",list);
        }
        return model;
    }
}
