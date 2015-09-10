package com.huivip.holu.webapp.listener;

import com.huivip.holu.model.User;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * Created by sunlaihui on 9/9/15.
 */
@Service
public class UserServiceLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private UserManager userManager;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication().
                getPrincipal()).getUsername();
        WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        String remoteAddress = details.getRemoteAddress();
        User user=userManager.getUserByLoginCode(userName);
        userManager.trackUserAction(user,"In",remoteAddress );
    }
}
