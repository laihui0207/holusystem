package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/noteform*")
public class NoteFormController extends BaseFormController {
    private NoteManager noteManager = null;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserGroupManager userGroupManager;

    @Autowired
    public void setNoteManager(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public NoteFormController() {
        setCancelView("redirect:notes");
        setSuccessView("redirect:notes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Note showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return noteManager.get(new Long(id));
        }

        return new Note();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Note note, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(note, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "noteform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (note.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            noteManager.remove(note.getId());
            saveMessage(request, getText("note.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            note.setCreater(cleanUser);
            note.setUpdater(cleanUser);
            note.setReceiver(cleanUser);
            noteManager.save(note);
            String key = (isNew) ? "note.added" : "note.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:noteform?id=" + note.getId();
            }
        }

        return success;
    }
    @RequestMapping(method = RequestMethod.GET,value = "{id}/Send")
    public ModelAndView sendNoteForm(@PathVariable("id") String noteId){
        Note note=noteManager.get(Long.parseLong(noteId));
        ModelAndView view=new ModelAndView("notesend");
        //note.setContent(StringEscapeUtils.escapeHtml(note.getContent()));
        view.addObject("note",note);
        return view;
    }
    @RequestMapping(method = RequestMethod.POST,value="{id}/Send")
    public String sendNoteSubmit(@PathVariable("id") String noteID,HttpServletRequest request){
        if (request.getParameter("cancel") != null) {
            return "redirect:/notes";
        }
        Note note=noteManager.get(Long.parseLong(noteID));

        String[] sendTousers=request.getParameterValues("sendToUserList");
        String[] sendToUserGroups=request.getParameterValues("sendToUserGroupList");

        Set<User> SendUserList=new HashSet<>();

        if(null!=sendTousers){
            for(String userId:sendTousers){
                SendUserList.add(userManager.get(Long.parseLong(userId)));
                note.getSendToUserList().add(userManager.get(Long.parseLong(userId)));
            }
        }
        if(null!=sendToUserGroups){
            for(String groupId:sendToUserGroups){
                SendUserList.addAll(userGroupManager.get(Long.parseLong(groupId)).getMembers());
                note.getSendToUserGroupList().add(userGroupManager.get(Long.parseLong(groupId)));
            }
        }
        noteManager.save(note);

        Note copyNote=new Note();
        copyNote.setTitle(note.getTitle());
        copyNote.setContent(note.getContent());
        copyNote.setCreater(note.getCreater());
        copyNote.setCreateTime(note.getCreateTime());

        final User cleanUser = getUserManager().getUserByUsername(
                request.getRemoteUser());

        copyNote.setFromUser(cleanUser);

        for(User toUser:SendUserList){
            copyNote.setId(null);
            copyNote.setReceiver(toUser);
            noteManager.save(copyNote);
        }
        saveMessage(request,"Send Success");
        return "redirect:/notes";
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
