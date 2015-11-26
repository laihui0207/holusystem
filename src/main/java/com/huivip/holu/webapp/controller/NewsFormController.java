package com.huivip.holu.webapp.controller;

import com.huivip.holu.Constants;
import com.huivip.holu.model.News;
import com.huivip.holu.model.NewsType;
import com.huivip.holu.model.User;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.service.NewsTypeManager;
import com.huivip.holu.util.Thumbnail;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.commons.lang.StringUtils;
import org.cache2k.Cache;
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
import java.util.*;

@Controller
@RequestMapping("/newsform*")
public class NewsFormController extends BaseFormController {
    Cache<String,News> cache= Cache2kProvider.getinstance().getCache(News.class.getName());
    Cache<String,ExtendedPaginatedList> listCache=Cache2kProvider.getinstance().getCache(ExtendedPaginatedList.class.getName());
    Cache<String,Set<String>> keyCache=Cache2kProvider.getinstance().getCache(HashSet.class.getName());
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
        if(!request.isUserInRole(Constants.ADMIN_ROLE)){
            saveError(request, getText("privilege.NotAllow", request.getLocale()));
        }
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            News news=cache.peek(id);
            if(news==null){
                news=newsManager.get(new Long(id));
                cache.put(id,news);
            }
            return news;
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
        if(!request.isUserInRole(Constants.ADMIN_ROLE)){
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
            cache.remove(news.getId().toString());
            saveMessage(request, getText("news.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            String imageUrl=Thumbnail.handleThumbnail(news.getContent(),getServletContext());
            if(imageUrl!=null){
                news.setThumbnailUrl(imageUrl);
                news.setMidImageUrl(imageUrl.replace("_smaller","_mid"));
            }
            news.setCreater(cleanUser);
            newsManager.save(news);
            cache.put(news.getId().toString(),news);
            String key = (isNew) ? "news.added" : "news.updated";
            saveMessage(request, getText(key, locale));

           /* if (!isNew) {
                success = "redirect:newsform?id=" + news.getId();
            }*/
        }
        //remove all list cache
        if(keyCache.contains(News.LIST_NEWS_CACHE_KEY)){
            Set<String> cacheKeys=keyCache.get(News.LIST_NEWS_CACHE_KEY);
            for(String key:cacheKeys){
                listCache.remove(key);
            }
            keyCache.remove(News.LIST_NEWS_CACHE_KEY);
        }
        return success;
    }
    @ModelAttribute("newsTypeList")
    public List<NewsType> newsTypeList(){
        return newsTypeManager.getAll();
    }
}
