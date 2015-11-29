package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import org.apache.commons.lang.StringUtils;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
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
    private CustomGroupManager customGroupManager;

    Cache<String,Note> cache=null;

    @Autowired
    public void setNoteManager(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public NoteFormController() {
        cache= Cache2kProvider.getinstance().setCache(Note.class,
                CacheBuilder.newCache(String.class,Note.class).build());
        setCancelView("redirect:notes");
        setSuccessView("redirect:notes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Note showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            Note note=cache.peek(id);
            if(note==null){
                note=noteManager.get(new Long(id));
                cache.put(id,note);
            }
            return note;
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
            cache.remove(note.getId().toString());
            noteManager.remove(note.getId());
            saveMessage(request, getText("note.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            note.setCreater(cleanUser);
            note.setUpdater(cleanUser);
            note.setReceiver(cleanUser);
            Note savedNote=noteManager.save(note);
            String key = (isNew) ? "note.added" : "note.updated";
            saveMessage(request, getText(key, locale));
            cache.put(savedNote.getId().toString(),savedNote);
            /*if (!isNew) {
                success = "redirect:noteform?id=" + note.getId();
            }*/
        }
        removeCacheByKeyPrefix(Note.LIST_NEWS_CACHE_KEY,Note.LIST_CACHE);
        return success;
    }
    @RequestMapping(method = RequestMethod.GET,value = "{id}/Send")
    public ModelAndView sendNoteForm(@PathVariable("id") String noteId){
        Note note=cache.peek(noteId);
        if(note==null){
           note=noteManager.get(Long.parseLong(noteId));
            cache.put(noteId,note);
        }

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
        Note note=cache.peek(noteID);
        if(note==null){
            note=noteManager.get(Long.parseLong(noteID));
            cache.put(noteID,note);
        }

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
                SendUserList.addAll(customGroupManager.get(Long.parseLong(groupId)).getMembers());
                note.getSendToUserGroupList().add(customGroupManager.get(Long.parseLong(groupId)));
            }
        }
        Note savedNote=noteManager.save(note);
        cache.put(savedNote.getId().toString(),savedNote);

        Note copyNote=new Note();
        copyNote.setTitle(note.getTitle());
        copyNote.setContent(note.getContent());
        copyNote.setCreater(note.getCreater());
        copyNote.setCreateTime(note.getCreateTime());

        final User cleanUser = getUserManager().getUserByLoginCode(
                request.getRemoteUser());

        copyNote.setFromUser(cleanUser);

        for(User toUser:SendUserList){
            copyNote.setId(null);
            copyNote.setReceiver(toUser);
            Note newNote=noteManager.save(copyNote);
            cache.put(newNote.getId().toString(),newNote);
        }
        saveMessage(request,"Send Success");
        removeCacheByKeyPrefix(Note.LIST_NEWS_CACHE_KEY,Note.LIST_CACHE);
        return "redirect:/notes";
    }
    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("userGroupList")
    public List<CustomGroup> userGroupList(){
        return customGroupManager.getAll();
    }
}
