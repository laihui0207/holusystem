package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.model.Department;
import com.huivip.holu.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/departmentform*")
public class DepartmentFormController extends BaseFormController {
    private DepartmentManager departmentManager = null;

    @Autowired
    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public DepartmentFormController() {
        setCancelView("redirect:departments");
        setSuccessView("redirect:departments");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Department showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return departmentManager.get(new Long(id));
        }

        return new Department();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Department department, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(department, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "departmentform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (department.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            departmentManager.remove(department.getId());
            saveMessage(request, getText("department.deleted", locale));
        } else {
            departmentManager.save(department);
            String key = (isNew) ? "department.added" : "department.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:departmentform?id=" + department.getId();
            }
        }

        return success;
    }
}
