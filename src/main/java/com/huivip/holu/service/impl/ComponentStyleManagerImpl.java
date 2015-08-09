package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.Post;
import com.huivip.holu.model.ProcessMid;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
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
    ProcessMidDao processMidDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;

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
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String companyId, String userId,String componentId) {

        return getProcessListByCompanyAndStyleName(styleID,companyId,userId,componentId,null);
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String companyId, String userId,String componentID, ExtendedPaginatedList list) {
        List<ComponentStyle> componentStyles = componentStyleDao.getProcessListByCompanyAndStyleName(styleID, companyId, list);
        User user = userDao.getUserByUserID(userId);
        Set<Post> posts = user.getPosts();
        String processTableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyId,"ProcessMidTable");
        for (ComponentStyle style : componentStyles) {
            if (!user.isAllowCreateProject()) {
                for (Post post : posts) {
                    if (style.getProcessDictionary().getProcessID().equalsIgnoreCase(post.getProcessDictionary().getProcessID())
                            && user.getCompany().getCompanyId().equalsIgnoreCase(style.getCompany().getCompanyId())) {
                        style.setOperationer(true);
                        break;
                    }
                }
                ProcessMid processMid=processMidDao.getProcessMid(componentID,style.getStyleProcessID(),processTableName);
                if(null!=processMid){
                    style.setConfirmDate(processMid.getCreateDate());
                    style.setConfirmer(processMid.getUser());
                    style.setOperationer(false);
                }
                else {
                    break;
                }

            } else {
                ProcessMid processMid=processMidDao.getProcessMid(componentID,style.getStyleProcessID(),processTableName);
                if(null!=processMid){
                    style.setConfirmDate(processMid.getCreateDate());
                    style.setConfirmer(processMid.getUser());
                }
                style.setOperationer(true);
            }
        }
        return componentStyles;
    }

    @Override
    public ComponentStyle getComponentProcessByProcessID(String styleProcessID) {
        return componentStyleDao.getComponentProcessByProcessID(styleProcessID);
    }
}