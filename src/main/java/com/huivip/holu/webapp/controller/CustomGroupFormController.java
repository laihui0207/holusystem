package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Company;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.Department;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyManager;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/customGroupform*")
public class CustomGroupFormController extends BaseFormController {
    private CustomGroupManager customGroupManager = null;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private DepartmentManager departmentManager;

    @Autowired
    public void setCustomGroupManager(CustomGroupManager customGroupManager) {
        this.customGroupManager = customGroupManager;
    }

    public CustomGroupFormController() {
        setCancelView("redirect:customGroups");
        setSuccessView("redirect:customGroups");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected String showForm(HttpServletRequest request,Model model)
    throws Exception {
        String id = request.getParameter("id");
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        if(!currentUser.isAllowCreateGroup()){
            saveMessage(request,"No Allow create Group");
           // return "redirect:/customGroups";
        }
        if (!StringUtils.isBlank(id)) {
            model.addAttribute(customGroupManager.get(new Long(id)));
            return "customGroupForm";
        }
        model.addAttribute(new CustomGroup());
        model.addAttribute("user",currentUser);
        return "customGroupForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CustomGroup customGroup, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(customGroup, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "customGroupform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (customGroup.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            customGroupManager.remove(customGroup.getId());
            saveMessage(request, getText("customGroup.deleted", locale));
        } else {
            String[] members=request.getParameterValues("members");
            if(null!=members){
                customGroup.getMembers().clear();
                for(String id:members){
                    customGroup.getMembers().add(userManager.getUserByUserID(id));
                }
            }
            if(customGroup.getCompany()!=null){
                customGroup.setCompany(companyManager.getCompanyByCompanyID(customGroup.getCompany().getCompanyId()));
            }
            if(customGroup.getDepartment()!=null){
                customGroup.setDepartment(departmentManager.getDepartmentByDepartmentID(customGroup.getDepartment().getDepartmentID()));
            }
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            customGroup.setCreater(cleanUser);

            customGroupManager.save(customGroup);
            String key = (isNew) ? "customGroup.added" : "customGroup.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:customGroupform?id=" + customGroup.getId();
            }
        }

        return success;
    }
    @ModelAttribute("companyList")
    public List<Company> companyList(){
        return companyManager.getAll();
    }
    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("departmentList")
    public List<Department> departmentList(){
        return departmentManager.getAll();
    }
}
