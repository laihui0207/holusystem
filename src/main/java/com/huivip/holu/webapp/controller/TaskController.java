package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.User;
import com.huivip.holu.service.TaskManager;
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
@RequestMapping("/tasks*")
public class TaskController {
    private TaskManager taskManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        User user=userManager.getUserByLoginCode(request.getRemoteUser());

        /*try {
            model.addAttribute(taskManager.search(query, Task.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(taskManager.getAll());
        }*/
        model.addAttribute(taskManager.getTaskOfUser(user.getUserID(),"all" , "0", "0"));
        return model;
    }

}
