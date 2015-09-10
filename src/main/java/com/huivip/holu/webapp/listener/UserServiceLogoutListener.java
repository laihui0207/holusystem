package com.huivip.holu.webapp.listener;

import com.huivip.holu.model.User;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunlaihui on 9/10/15.
 */
@Service
public class UserServiceLogoutListener implements ApplicationListener<SessionDestroyedEvent> {
    @Autowired
    private UserManager userManager;
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {

        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            WebAuthenticationDetails details = (WebAuthenticationDetails) securityContext.getAuthentication().getDetails();
            String remoteAddress = details.getRemoteAddress();
            String userName = ud.getUsername();
            User user=userManager.getUserByLoginCode(userName);
            userManager.trackUserAction(user,"Out",remoteAddress );
        }
    }
}
