package com.huivip.holu.service.impl;

import com.huivip.holu.dao.PostSubjectDao;
import com.huivip.holu.model.PostSubject;
import com.huivip.holu.service.PostSubjectManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("postSubjectManager")
@WebService(serviceName = "PostSubjectService", endpointInterface = "com.huivip.holu.service.PostSubjectManager")
public class PostSubjectManagerImpl extends GenericManagerImpl<PostSubject, Long> implements PostSubjectManager {
    PostSubjectDao postSubjectDao;

    @Autowired
    public PostSubjectManagerImpl(PostSubjectDao postSubjectDao) {
        super(postSubjectDao);
        this.postSubjectDao = postSubjectDao;
    }

    @Override
    public List<PostSubject> postSubjects(String page,String pageSize) {
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setIndex(Integer.parseInt(page));
        list.setPageSize(Integer.parseInt(pageSize));
        List<PostSubject> dataList= getAllPageable(list);
        return list.getList();
    }
}