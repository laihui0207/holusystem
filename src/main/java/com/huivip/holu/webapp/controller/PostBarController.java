package com.huivip.holu.webapp.controller;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.model.User;
import com.huivip.holu.service.PostBarManager;
import com.huivip.holu.service.ReplyManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/postBars*")
public class PostBarController {
    private PostBarManager postBarManager;
    @Autowired
    private ReplyManager replyManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setPostBarManager(PostBarManager postBarManager) {
        this.postBarManager = postBarManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        User user=userManager.getUserByUsername(request.getRemoteUser());
        try {
            if(request.isUserInRole(Constants.ADMIN_ROLE)) {
                model.addAttribute(postBarManager.search(query, PostBar.class));
            }
            else {
                if(null == query || query.equals("")){
                    model.addAttribute(postBarManager.getAll());
                }
                else {

                }
            }
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
