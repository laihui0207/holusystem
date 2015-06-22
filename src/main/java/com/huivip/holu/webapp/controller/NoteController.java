package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Note;
import com.huivip.holu.service.NoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes*")
public class NoteController {
    private NoteManager noteManager;

    @Autowired
    public void setNoteManager(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(noteManager.search(query, Note.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(noteManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/view/{id}",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String viewNews(@PathVariable("id")String id) {
        Note note=noteManager.get(Long.parseLong(id));
        return note.getContent();
    }
}
