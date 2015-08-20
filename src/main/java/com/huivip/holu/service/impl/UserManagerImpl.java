package com.huivip.holu.service.impl;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.User;
import com.huivip.holu.service.*;
import com.huivip.holu.util.AccessToken;
import org.apache.commons.lang.StringUtils;
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
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private RoleManager roleManager;

    private MailEngine mailEngine;
    private SimpleMailMessage message;
    private PasswordTokenManager passwordTokenManager;

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
        return userDao.get(new Long(userId));
    }

    @Override
    public User getUserByLoginCode(String loginCode) {
        return userDao.getUserByLoginCode(loginCode);
    }

    @Override
    public User getUserByUserID(String userID) {
        return userDao.getUserByUserID(userID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {
        return userDao.getAllDistinct();
    }

    @Override
    public List<SelectLabelValue> getUsersLabelValue() {
        List<User> list=userDao.getAllDistinct();
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
            return userDao.saveUser(user);
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
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(final String userId) {
        log.debug("removing user: " + userId);
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
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User signup(String loginCode, String userName, String password, String companyId) {
        User user=new User();
        user.setUsername(userName);
        user.setLoginCode(loginCode);
        user.setPhoneNumber("");
        user.setPassword(password);
        SimpleDateFormat sdf=new SimpleDateFormat("ddssSSS");
        String userID=sdf.format(System.currentTimeMillis());
        user.addRole(roleManager.getRole(Constants.USER_ROLE));
        user.setUserID(userID);
        user.setCompany(companyDao.getCompanyByCompanyID(companyId));
        user.setAcceptRegistration(false);
        user.setRegistrationDate(new Date());
        try {
            this.saveUser(user);
        } catch (UserExistsException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when username not found
     */
    @Override
    public User getUserByUsername(final String username) throws UsernameNotFoundException {
        return (User) userDao.loadUserByUsername(username);
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
