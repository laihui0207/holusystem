package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.Post;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentStyleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.Set;

@Service("componentStyleManager")
@WebService(serviceName = "ComponentStyleService", endpointInterface = "com.huivip.holu.service.ComponentStyleManager")
public class ComponentStyleManagerImpl extends GenericManagerImpl<ComponentStyle, Long> implements ComponentStyleManager {
    ComponentStyleDao componentStyleDao;
    @Autowired
    UserDao userDao;

    @Autowired
    public ComponentStyleManagerImpl(ComponentStyleDao componentStyleDao) {
        super(componentStyleDao);
        this.componentStyleDao = componentStyleDao;
    }

    @Override
    public List<ComponentStyle> getComponentStypeListByCompany(String companyId) {
        return componentStyleDao.getComponentStypeListByCompany(companyId);
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleName, String companyId,String userId) {
        List<ComponentStyle> componentStyles=componentStyleDao.getProcessListByCompanyAndStyleName(styleName,companyId);
        User user=userDao.get(Long.parseLong(userId));
        Set<Post> posts=user.getPosts();
        for(ComponentStyle style:componentStyles){
            if(!user.isAllowCreateProject()){
                for(Post post:posts){
                    if(post.getProcessName().equals(style.getProcessName()) && user.getCompany().getId()==style.getCompany().getId()){
                        style.setOperationer(true);
                    }
                }

            }
            else {
                style.setOperationer(true);
            }
        }
        return componentStyles;
    }
}