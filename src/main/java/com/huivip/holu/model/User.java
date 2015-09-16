package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Spring Security's UserDetails interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Spring UserDetails interface
 *         by David Carter david@carter.net
 *          SELECT TOP 1000 [ID]
,[UserID]
,[LoginCode]
,[Password]
,[UserName]
,[MobileNumber]
,[Email]
,[QQ]
,[CompanyID]
,[LoginDate]
,[LastLoginDate]
,[LastLogoutDate]
,[LoginCount]
,[TotalHours]
,[UserState]
,[RegistrationDate]
,[AcceptRegistration]
,[AllowCreateGroup]
,[AllowCreateProject]  // means the user is a super admininstartor can confirm all process status
FROM [MidDatabase].[dbo].[R_User]
 */
@Entity
@Table(name = "R_User")
@Indexed
@XmlRootElement
public class User extends BaseObject implements Serializable, UserDetails {
    private static final long serialVersionUID = 3832626162173359411L;

    private Long id;
    private String userID;
    private String username;                    // required
    private String userNameEn;
    private String loginCode;
    private String password;                    // required
    private String confirmPassword;
    private String passwordHint;
    private String firstName;                   // required
    private String lastName;                    // required
    private String email;                       // required; unique
    private String phoneNumber;
    private String QQ;
   /* private Date loginDate;
    private Date lastLoginDate;
    private Date lastLogoutDate;
    private int loginCount;
    private int totalHours;*/
    private Date registrationDate;
    private int acceptRegistration;
    private boolean allowCreateGroup;
    private boolean allowCreateProject;
    private String userState;
    private Date acceptRegistrationDate;
    private String note;
    private Date birthday;
    private String gender;

    private Company company;

    private Integer version;
    private Set<Role> roles = new HashSet<Role>();
    private Set<Post> posts=new HashSet<>();
/*    private Set<Department> departments=new HashSet<>();*/
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private String access_token;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public User() {
    }

    /**
     * Create a new instance and set the username.
     *
     * @param username login name for user.
     */
    public User(final String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }

    @Column( length = 50)
    @Field
    public String getUsername() {
        return username;
    }

    @Column(nullable = true)
    @XmlTransient
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Column(name = "password_hint")
    @XmlTransient
    public String getPasswordHint() {
        return passwordHint;
    }

    @Column(name = "first_name", nullable = true, length = 50)
    @Field
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = true, length = 50)
    @Field
    public String getLastName() {
        return lastName;
    }

    @Column(nullable = true,name="Email")
    @Field
    public String getEmail() {
        return email;
    }

    @Column(name = "MobileNumber",nullable = false,unique = true)
    @Field(analyze= Analyze.NO)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return username;
    }

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Column(name="QQ")
    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
    /*@Column(name="LoginDate")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    @Column(name="LastLoginDate")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDte) {
        this.lastLoginDate = lastLoginDte;
    }
    @Column(name="LastLogoutDate")
    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }
    @Column(name="LoginCount",columnDefinition ="int default 0")
    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }
    @Column(name="TotalHours",columnDefinition ="int default 0")
    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }*/
    @Column(name="RegistrationDate")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    @Column(name="AcceptRegistrationDate")
    public Date getAcceptRegistrationDate() {
        return acceptRegistrationDate;
    }

    public void setAcceptRegistrationDate(Date acceptRegistrationDate) {
        this.acceptRegistrationDate = acceptRegistrationDate;
    }
    @Column(name="UserNote")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Column(name="Birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Column(name="Gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name="AcceptRegistration",columnDefinition ="int default 0")
    public int getAcceptRegistration() {
        return acceptRegistration;
    }

    public void setAcceptRegistration(int acceptRegistration) {
        this.acceptRegistration = acceptRegistration;
    }
    @Column(name="AllowCreateGroup",nullable = true,columnDefinition ="int default 0")
    public boolean isAllowCreateGroup() {
        return allowCreateGroup;
    }

    public void setAllowCreateGroup(boolean allowCreateGroup) {
        this.allowCreateGroup = allowCreateGroup;
    }
    @Column(name="AllowCreateProject",nullable = true,columnDefinition ="int default 0")
    public boolean isAllowCreateProject() {
        return allowCreateProject;
    }

    public void setAllowCreateProject(boolean allowCreateProject) {
        this.allowCreateProject = allowCreateProject;
    }
    @Column(name="UserNameEn")
    public String getUserNameEn() {
        return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }
    @Column(name="UserState")
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
    @ManyToOne
    @JoinColumn(name="companyID",referencedColumnName = "companyID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    /*@ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name="R_DepartmentUserMappingTable",
            joinColumns = {@JoinColumn(name="UserID",referencedColumnName = "userID")},
            inverseJoinColumns = {@JoinColumn(name = "DepartmentId",referencedColumnName = "departmentID")}
    )
    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
*/
    /**
     * Convert user roles to LabelValue objects for convenience.
     *
     * @return a list of LabelValue objects with role information
     */
    @Transient
    public List<LabelValue> getRoleList() {
        List<LabelValue> userRoles = new ArrayList<LabelValue>();

        if (this.roles != null) {
            for (Role role : roles) {
                // convert the user's roles to LabelValue Objects
                userRoles.add(new LabelValue(role.getName(), role.getName()));
            }
        }

        return userRoles;
    }
    @Transient
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * Adds a role for the user
     *
     * @param role the fully instantiated role
     */
    public void addRole(Role role) {
        getRoles().add(role);
    }

    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "R_UserPostMappingTable",
            joinColumns = { @JoinColumn(name = "UserID",referencedColumnName = "userID") },
            inverseJoinColumns = @JoinColumn(name = "PostID",referencedColumnName = "postID")
    )
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    /**
     * @return GrantedAuthority[] an array of roles.
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    @JsonIgnore // needed for UserApiITest in appfuse-ws archetype
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        authorities.addAll(roles);
        return authorities;
    }

    @Version
    @Column(columnDefinition ="int default 1")
    public Integer getVersion() {
        return version;
    }

    @Column(name = "account_enabled",columnDefinition ="int default 1")
    public boolean isEnabled() {
        return enabled;
    }

    @Column(name = "account_expired", nullable = true,columnDefinition ="int default 0")
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     * @return true if account is still active
     */
    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Column(name = "account_locked", nullable = true,columnDefinition ="int default 0")
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     * @return false if account is locked
     */
    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Column(name = "credentials_expired", nullable = true,columnDefinition ="int default 0")
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
    @Column(nullable = false)
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    @Column(nullable = false,unique = true)
    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username", this.username)
                .append("enabled", this.enabled)
                .append("accountExpired", this.accountExpired)
                .append("credentialsExpired", this.credentialsExpired)
                .append("accountLocked", this.accountLocked);

        if (roles != null) {
            sb.append("Granted Authorities: ");

            int i = 0;
            for (Role role : roles) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(role.toString());
                i++;
            }
        } else {
            sb.append("No Granted Authorities");
        }
        return sb.toString();
    }
}
