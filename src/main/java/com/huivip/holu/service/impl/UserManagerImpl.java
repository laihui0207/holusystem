package com.huivip.holu.service.impl;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserTrack;
import com.huivip.holu.service.*;
import com.huivip.holu.util.AccessToken;
import com.huivip.holu.util.cache.Cache2kProvider;
import org.apache.commons.lang.StringUtils;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.huivip.holu.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    Cache<String,User> cache= Cache2kProvider.getinstance().setCache(User.class, CacheBuilder.newCache(String.class,User.class).build());
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private UserTrackManager userTrackManager;

    private MailEngine mailEngine;
    private SimpleMailMessage message;
    private PasswordTokenManager passwordTokenManager;
    @Autowired
    private ComponentStyleManager componentStyleManager;

    private String passwordRecoveryTemplate = "passwordRecovery.vm";
    private String passwordUpdatedTemplate = "passwordUpdated.vm";

    @Autowired
    @Qualifier("passwordEncoder")
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }

    @Autowired(required = false)
    public void setMailEngine(final MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    @Autowired(required = false)
    public void setMailMessage(final SimpleMailMessage message) {
        this.message = message;
    }

    @Autowired(required = false)
    public void setPasswordTokenManager(final PasswordTokenManager passwordTokenManager) {
        this.passwordTokenManager = passwordTokenManager;
    }

    /**
     * Velocity template name to send users a password recovery mail (default
     * passwordRecovery.vm).
     *
     * @param passwordRecoveryTemplate the Velocity template to use (relative to classpath)
     * @see com.huivip.holu.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordRecoveryTemplate(final String passwordRecoveryTemplate) {
        this.passwordRecoveryTemplate = passwordRecoveryTemplate;
    }

    /**
     * Velocity template name to inform users their password was updated
     * (default passwordUpdated.vm).
     *
     * @param passwordUpdatedTemplate the Velocity template to use (relative to classpath)
     * @see com.huivip.holu.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordUpdatedTemplate(final String passwordUpdatedTemplate) {
        this.passwordUpdatedTemplate = passwordUpdatedTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final String userId) {
        User user=/*cache.peek(userId);
        if(user==null){
            user=*/userDao.get(new Long(userId));
       /*     cache.put(userId,user);
        }*/
        return user;
    }

    @Override
    public User getUserByLoginCode(String loginCode) {
        User user=/*cache.peek(loginCode);
        if(user==null || user.getAcceptRegistration()==0){
            user=*/userDao.getUserByLoginCode(loginCode);
         /*   cache.put(loginCode,user);
        }*/
        return user;
    }

    @Override
    public User getUserByUserID(String userID) {
        User user=/*cache.peek(userID);
        if(user==null){
            user=*/userDao.getUserByUserID(userID);
        /*    cache.put(userID,user);
        }*/
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    @Override
    public List<SelectLabelValue> getUsersLabelValue(String userID) {
        List<User> list;
        if(userID==null || userID.equalsIgnoreCase("") || userID.equalsIgnoreCase("undefined"))
        {
            list=userDao.getAllDistinct();
        }
        else {
            User user=getUserByUserID(userID);
            list=userDao.getUserByCompany(user.getCompany().getCompanyId());
        }
        List<SelectLabelValue> result=new ArrayList<>();
        for(User user:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setId(user.getId().toString());
            slv.setText(user.getFullName());
            slv.setChecked(false);
            slv.setIcon(null);
            result.add(slv);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveUser(User user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                final String currentPassword = userDao.getUserPassword(user.getId());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            User user1=userDao.saveUser(user);
            cache.put(user1.getId().toString(),user1);
            cache.put(user1.getUserID(),user1);
            cache.put(user1.getLoginCode(),user1);
            cache.put(user1.getUsername(),user1);
            return user1;
        } catch (final Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(final User user) {
        log.debug("removing user: " + user);
        cache.remove(user.getLoginCode());
        cache.remove(user.getUserID());
        cache.remove(user.getId().toString());
        cache.remove(user.getUsername());
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(final String userId) {
        log.debug("removing user: " + userId);
        User user=userDao.get(new Long(userId));
        cache.remove(user.getLoginCode());
        cache.remove(user.getUserID());
        cache.remove(user.getId().toString());
        cache.remove(user.getUsername());
        userDao.remove(new Long(userId));
    }

    @Override
    public User userLogin(String username, String password) {
        try {
            User user = getUserByLoginCode(username);
            org.apache.commons.codec.binary.Base64 base64=new org.apache.commons.codec.binary.Base64();
            if (user == null) return null;
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return null;
            }
            String token= "#2a$"+user.getUserID()+"%"+ AccessToken.createAccessToken(user.getUserID());
            user.setAccess_token(base64.encodeToString(token.getBytes()));
            trackUserAction(user,"In","mobile" );
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User userLogout(String userId) {
        User user=getUserByUserID(userId);
        if(user!=null){
            trackUserAction(user,"Out","mobile");
        }
        return user;
    }

    @Override
    public void trackUserAction(User user, String action, String remoteIp){
        UserTrack track=new UserTrack();
        track.setUser(user);
        track.setInOrOut(action);
        track.setLoginDate(new Date());
        track.setIp(remoteIp);
        userTrackManager.save(track);
    }
    @Override
    public User signup(String loginCode, String userName, String password, String userNote) {
        User user=new User();
        user.setUsername(userName);
        user.setLoginCode(loginCode);
        user.setPhoneNumber("");
        user.setPassword(password);
/*        user.setBirthday(null);*/
        SimpleDateFormat sdf=new SimpleDateFormat("ddssSSS");
        String userID=sdf.format(System.currentTimeMillis());
        user.addRole(roleManager.getRole(Constants.USER_ROLE));
        user.setUserID(userID);
        user.setNote(userNote);
/*        user.setCompany(companyDao.getCompanyByCompanyID(companyId));*/
        user.setAcceptRegistration(0);
        user.setRegistrationDate(new Date());
        try {
            this.saveUser(user);
        } catch (UserExistsException e) {
            e.printStackTrace();
        }
        return user;
    }

   /* @Override
    public List<Task> myTask(String userId) {
        return componentStyleManager.getMyTask(userId);
    }*/

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when username not found
     */
    @Override
    public User getUserByUsername(final String username) throws UsernameNotFoundException {
        User user=cache.peek(username);
        if(user==null || user.getAcceptRegistration()==0) {
            user = (User) userDao.loadUserByUsername(username);
            cache.put(username,user);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> search(final String searchTerm) {
        return super.search(searchTerm, User.class);
    }

    @Override
    public String buildRecoveryPasswordUrl(final User user, final String urlTemplate) {
        final String token = generateRecoveryToken(user);
        final String username = user.getUsername();
        return StringUtils.replaceEach(urlTemplate,
                new String[]{"{username}", "{token}"},
                new String[]{username, token});
    }

    @Override
    public String generateRecoveryToken(final User user) {
        return passwordTokenManager.generateRecoveryToken(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecoveryTokenValid(final String username, final String token) {
        return isRecoveryTokenValid(getUserByUsername(username), token);
    }

    @Override
    public boolean isRecoveryTokenValid(final User user, final String token) {
        return passwordTokenManager.isRecoveryTokenValid(user, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPasswordRecoveryEmail(final String username, final String urlTemplate) {
        log.debug("Sending password recovery token to user: " + username);

        final User user = getUserByUsername(username);
        final String url = buildRecoveryPasswordUrl(user, urlTemplate);

        sendUserEmail(user, passwordRecoveryTemplate, url, "Password Recovery");
    }

    private void sendUserEmail(final User user, final String template, final String url, final String subject) {
        message.setTo(user.getFullName() + "<" + user.getEmail() + ">");
        message.setSubject(subject);

        final Map<String, Serializable> model = new HashMap<String, Serializable>();
        model.put("user", user);
        model.put("applicationURL", url);

        mailEngine.sendMessage(message, template, model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User updatePassword(final String username, final String currentPassword, final String recoveryToken, final String newPassword, final String applicationUrl) throws UserExistsException {
        User user = getUserByLoginCode(username);
        if (isRecoveryTokenValid(user, recoveryToken)) {
            log.debug("Updating password from recovery token for user: " + username);
            user.setPassword(newPassword);
            user = saveUser(user);
            passwordTokenManager.invalidateRecoveryToken(user, recoveryToken);

            sendUserEmail(user, passwordUpdatedTemplate, applicationUrl, "Password Updated");

            return user;
        } else if (StringUtils.isNotBlank(currentPassword)) {
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.debug("Updating password (providing current password) for user:" + username);
                user.setPassword(newPassword);
                user = saveUser(user);
                return user;
            }
        }
        // or throw exception
        return null;
    }

}
