package com.huivip.holu.webapp.controller;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/notes*")
public class NoteController {
    private NoteManager noteManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    Cache<String,Note> cache=null;

    @Autowired
    public void setNoteManager(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public NoteController() {
        super();
        cache= Cache2kProvider.getinstance().setCache(Note.class,
                CacheBuilder.newCache(String.class,Note.class).build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query, HttpServletRequest request)
            throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list = paginateListFactory.getPaginatedListFromRequest(request);
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        try {
            if (request.isUserInRole(Constants.ADMIN_ROLE)) {
                noteManager.search(query, Note.class, list);
                model.addAttribute("noteList", list);
            } else {
                User user = userManager.getUserByLoginCode(request.getRemoteUser());
                if (null == query || "".equals(query)) {
                    noteManager.myNotes(user.getId().toString(), list);
                    model.addAttribute("noteList", list);
                } else {
                   List<Note> dataList= noteManager.search(query, Note.class);
                   List<Note> resultList=new ArrayList<>();
                    for(Note note:dataList){
                        if(note.getReceiver().getId()==user.getId()){
                            resultList.add(note);
                        }
                    }
                    model.addAttribute(resultList);
                }

            }
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            noteManager.getAllPageable(list);
            model.addAttribute("noteList", list);
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{id}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String viewNews(@PathVariable("id") String id) {
        Note note = cache.peek(id);
        if(note==null){
            note=noteManager.get(Long.parseLong(id));
            cache.put(id,note);
        }

        return note.getContent();
    }
}
