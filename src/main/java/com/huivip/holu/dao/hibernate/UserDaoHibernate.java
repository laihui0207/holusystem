package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.User;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *         Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *         Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with
 *         the new BaseDaoHibernate implementation that uses generics.
 *         Modified by jgarcia (updated to hibernate 4)
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        getSession().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("loginCode", username))
                .add(Restrictions.eq("acceptRegistration",1)).list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            User user= (User) users.get(0);
            UserDetails userDetails= new org.springframework.security.core.userdetails.User(
                    user.getLoginCode(),user.getPassword(),true,true,true,true,user.getAuthorities());
            return userDetails;

        }
    }

    public User getUserByLoginCode(String loginCode) {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("loginCode", loginCode))
                .add(Restrictions.eq("acceptRegistration",1)).list();
        if (users == null || users.isEmpty()) {
            return null;
        } else {
            return (User) users.get(0);
        }
    }

    @Override
    public User getUserByUserID(String userID) {
        String sql="From User where id in(select id from User where userID='"+userID+"')";
        Query query=getSession().createQuery(sql);
        List<User> users=query.list();

        //List users = getSession().createCriteria(User.class).add(Restrictions.eq("userID", userID)).list();
        if (users == null || users.isEmpty()) {
            return null;
        } else {
            return (User) users.get(0);
        }
    }

    @Override
    public List<User> getUserByCompany(String companyID) {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("company.companyId", companyID)).list();
        return users;
    }

    /**
     * {@inheritDoc}
     */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
    }
}
