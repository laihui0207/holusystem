package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.News;
import com.huivip.holu.model.NewsType;
import com.huivip.holu.model.User;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.service.NewsTypeManager;
import com.huivip.holu.util.SteelConfig;
import com.huivip.holu.util.Thumbnail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/newsform*")
public class NewsFormController extends BaseFormController {
    private NewsManager newsManager = null;
    @Autowired
    private NewsTypeManager newsTypeManager;

    @Autowired
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    public NewsFormController() {
        setCancelView("redirect:newss");
        setSuccessView("redirect:newss");
    }
    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
            new PropertyEditorSupport() {
                public void setAsText(String value) {
                    try {
                        Date parsedDate = new SimpleDateFormat("MM/dd/yyyy").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        setValue(null);
                    }
                }
            });
    }
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected News showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return newsManager.get(new Long(id));
        }

        return new News();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(News news, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(news, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "newsform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (news.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            newsManager.remove(news.getId());
            saveMessage(request, getText("news.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            handleThumbnail(news);
            news.setCreater(cleanUser);
            newsManager.save(news);
            String key = (isNew) ? "news.added" : "news.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:newsform?id=" + news.getId();
            }
        }

        return success;
    }
    @ModelAttribute("newsTypeList")
    public List<NewsType> newsTypeList(){
        return newsTypeManager.getAll();
    }
    private void handleThumbnail(News news){
        String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m=r.matcher(news.getContent());
        String attacheDir= SteelConfig.getConfigure(SteelConfig.EditorAttachedDirectory);
        if(null==attacheDir || attacheDir.length()==0){
            attacheDir=getServletContext().getRealPath("/");
        }
        if(!attacheDir.endsWith("/")){
            attacheDir+="/";
        }
        while(m.find()){
            String imgUrl=m.group(1);
            if(imgUrl.indexOf("attached")<0){
                continue;
            }
            String fileUrl=attacheDir+imgUrl;
            //to do  check if need create thumbnail
            Thumbnail.thumbnail_create(fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1),
                    fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
            String thumbnailURL=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1)+imgUrl.substring(imgUrl.lastIndexOf("/")+1,
                    imgUrl.lastIndexOf("."))+"_smaller"+imgUrl.substring(imgUrl.lastIndexOf("."));
            news.setThumbnailUrl(thumbnailURL);
            break;
        }
    }
}
