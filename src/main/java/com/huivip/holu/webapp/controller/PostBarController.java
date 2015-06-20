package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.service.PostBarManager;
import com.huivip.holu.service.ReplyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/postBars*")
public class PostBarController {
    private PostBarManager postBarManager;
    @Autowired
    private ReplyManager replyManager;

    @Autowired
    public void setPostBarManager(PostBarManager postBarManager) {
        this.postBarManager = postBarManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(postBarManager.search(query, PostBar.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(postBarManager.getAll());
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET,value = "{postBarID}/replies")
    public ModelAndView repliesOfPost(@PathVariable("postBarID")String postID){
        ModelAndView view=new ModelAndView("replies");
        PostBar postBar=postBarManager.get(Long.parseLong(postID));
        view.addObject("replyList",postBar.getReplies());
        view.addObject("postBar",postBar);
        return view;
    }
}
