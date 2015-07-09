package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.PostBar;
import com.huivip.holu.model.PostSubject;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.PostBarManager;
import com.huivip.holu.service.PostSubjectManager;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.Thumbnail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/postBarform*")
public class PostBarFormController extends BaseFormController {
    private PostBarManager postBarManager = null;
    @Autowired
    private PostSubjectManager postSubjectManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserGroupManager userGroupManager;

    @Autowired
    public void setPostBarManager(PostBarManager postBarManager) {
        this.postBarManager = postBarManager;
    }

    public PostBarFormController() {
        setCancelView("redirect:postBars");
        setSuccessView("redirect:postBars");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PostBar showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return postBarManager.get(new Long(id));
        }

        return new PostBar();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PostBar postBar, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

       /* if (validator != null) { // validator is null during testing
            validator.validate(postBar, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postBarform";
            }
        }*/

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (postBar.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postBarManager.remove(postBar.getId());
            saveMessage(request, getText("postBar.deleted", locale));
        } else {
            if(!postBar.isIfAccessAllReply()) {
                String[] replayUsers = request.getParameterValues("replyUsers");
                String[] replyGroups = request.getParameterValues("replyGroups");
                if(null!=replayUsers){
                    if(null!=postBar.getReplyUsers()){
                        postBar.getReplyUsers().clear();
                    }
                    for(String userID:replayUsers){
                        postBar.getReplyUsers().add(userManager.get(Long.parseLong(userID)));
                    }
                }
                if(null!=replyGroups){
                    if(null!=postBar.getReplyGroups()){
                        postBar.getReplyGroups().clear();
                    }
                    for(String groupId:replyGroups){
                        postBar.getReplyGroups().add(userGroupManager.get(Long.parseLong(groupId)));
                    }
                }
            }
            else {

                if(null!=postBar.getReplyUsers()) {
                    postBar.getReplyGroups().clear();
                }
                if(null!=postBar.getReplyGroups()) {
                    postBar.getReplyUsers().clear();
                }
            }
            if(!postBar.isIfAccessAllView()) {
                String[] viewUsers = request.getParameterValues("viewUsers");
                String[] viewGroups = request.getParameterValues("viewGroups");
                if(null!=viewUsers){
                    if(null!=postBar.getViewUsers()){
                        postBar.getViewUsers().clear();
                    }
                    for(String userID:viewUsers){
                        postBar.getViewUsers().add(userManager.get(Long.parseLong(userID)));
                    }
                }
                if(null!=viewGroups){
                    if(null!=postBar.getViewGroups()){
                        postBar.getViewGroups().clear();
                    }
                    for(String groupId:viewGroups){
                        postBar.getViewGroups().add(userGroupManager.get(Long.parseLong(groupId)));
                    }
                }
            }
            else {
                if(null!=postBar.getViewGroups()) {
                    postBar.getViewGroups().clear();
                }
                if(null!=postBar.getViewUsers()) {
                    postBar.getViewUsers().clear();
                }
            }

            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            postBar.setCreater(cleanUser);
            postBar.setThumbnailUrl(Thumbnail.handleThumbnail(postBar.getContent(),getServletContext()));
            postBarManager.save(postBar);
            String key = (isNew) ? "postBar.added" : "postBar.updated";
            saveMessage(request, getText(key, locale));

            /*if (!isNew) {
                success = "redirect:postBarform?id=" + postBar.getId();
            }*/
        }

        return success;
    }
    @ModelAttribute("userList")
    public List<User> usersList(){
        return userManager.getAll();
    }
    @ModelAttribute("userGroupList")
    public List<UserGroup> userGroupsList(){
        return userGroupManager.getAll();
    }
    @ModelAttribute("postSubjectList")
    public List<PostSubject> postSubjectsList(){
        return postSubjectManager.getAll();
    }
}
