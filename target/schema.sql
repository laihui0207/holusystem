
    alter table R_Peply 
        drop 
        foreign key FK_f0s4sqlgrdhje2hrxxrlgqsvr;

    alter table R_Peply 
        drop 
        foreign key FK_ecxqk949v5un5kp1gob78awmd;

    alter table R_PostBar 
        drop 
        foreign key FK_odoxuy8ch3ixtx6nw8cgn1apt;

    alter table R_PostBar 
        drop 
        foreign key FK_rlf396wxnrmth9dqbr6a6662;

    alter table R_PostBar 
        drop 
        foreign key FK_hcuom4pafr88ry7ud1iybod39;

    alter table R_Project 
        drop 
        foreign key FK_mpqpmfiwhereygs96jkx2qdyh;

    alter table R_Project 
        drop 
        foreign key FK_ic4k24puw1jm8abskdyji5cgg;

    alter table R_UserPostMappingTable 
        drop 
        foreign key FK_62tpcel38b2r87ho0fxo84rxy;

    alter table R_UserPostMappingTable 
        drop 
        foreign key FK_fnqwjpqu5aexd3xorofbg7tmm;

    alter table R_groupMember 
        drop 
        foreign key FK_erl00brea7g2c9lmj8a7s7tdq;

    alter table R_groupMember 
        drop 
        foreign key FK_8qe1taybdan95ly5884y4p6pi;

    alter table R_messages 
        drop 
        foreign key FK_oy9dn821ecooys4oqc1g4emm4;

    alter table R_messages 
        drop 
        foreign key FK_fcrfg6ki6bkhqwt45uok9tnlv;

    alter table R_messages 
        drop 
        foreign key FK_8jkkk4vlfxj4wguqnou69kmva;

    alter table R_messages 
        drop 
        foreign key FK_fjn7rqepejl334r8wjilumdq2;

    alter table R_news 
        drop 
        foreign key FK_n7v03feqrig5e4b2tcn32j2le;

    alter table R_news 
        drop 
        foreign key FK_eqkeuufu32cp4obfwmislftv0;

    alter table R_newsType 
        drop 
        foreign key FK_j22qolkdgbhpwovei218yf4yb;

    alter table R_newsType 
        drop 
        foreign key FK_cgjhua6xesas9bxnywl3377am;

    alter table R_user 
        drop 
        foreign key FK_rk4q0m6fnqh0wld3ay5j5qnf;

    alter table R_userGroup 
        drop 
        foreign key FK_dvc1en6gcmamgg5fap7uybro9;

    alter table R_userGroup 
        drop 
        foreign key FK_9wl1uvd2gxmcdnr2c3atojn7j;

    alter table R_userGroup 
        drop 
        foreign key FK_745k2yy8372quwdtu09wqdrkq;

    alter table messagereceivegroups 
        drop 
        foreign key FK_l78ngyyiugpy9jmd2l8ed7sij;

    alter table messagereceivegroups 
        drop 
        foreign key FK_ex1o65in29vrul0tlfpont4ub;

    alter table messagereceiveusers 
        drop 
        foreign key FK_j43cxfks6d1e49mkls59wkw4l;

    alter table messagereceiveusers 
        drop 
        foreign key FK_7vvf1c34l5aw5e3n8ad0gnot7;

    alter table postBarReplyGroups 
        drop 
        foreign key FK_cj3ws5p8b6h8rxp7064a3160j;

    alter table postBarReplyGroups 
        drop 
        foreign key FK_f92wt6414rdttou1oe2hhcajs;

    alter table postBarReplyUsers 
        drop 
        foreign key FK_lssyu03swvkc35nhp6g5c9j99;

    alter table postBarReplyUsers 
        drop 
        foreign key FK_sufxnlot6as99etc4ijqowinv;

    alter table postBarViewGroups 
        drop 
        foreign key FK_dmauq13o1kegvf44dyhrcfq3i;

    alter table postBarViewGroups 
        drop 
        foreign key FK_a7peknboqvm1tvsaidoximet8;

    alter table postBarViewUsers 
        drop 
        foreign key FK_r8nsta9nmhw84cg6vlnktj4gy;

    alter table postBarViewUsers 
        drop 
        foreign key FK_hutlbo8cpta163flmbafyvdyt;

    alter table user_role 
        drop 
        foreign key FK_it77eq964jhfqtu54081ebtio;

    alter table user_role 
        drop 
        foreign key FK_apcc8lxk2xnug8377fatvbn04;

    drop table if exists R_Peply;

    drop table if exists R_Post;

    drop table if exists R_PostBar;

    drop table if exists R_PostSubject;

    drop table if exists R_Project;

    drop table if exists R_UserPostMappingTable;

    drop table if exists R_company;

    drop table if exists R_groupMember;

    drop table if exists R_messages;

    drop table if exists R_news;

    drop table if exists R_newsType;

    drop table if exists R_role;

    drop table if exists R_user;

    drop table if exists R_userGroup;

    drop table if exists messagereceivegroups;

    drop table if exists messagereceiveusers;

    drop table if exists postBarReplyGroups;

    drop table if exists postBarReplyUsers;

    drop table if exists postBarViewGroups;

    drop table if exists postBarViewUsers;

    drop table if exists user_role;

    create table R_Peply (
        id bigint not null auto_increment,
        content varchar(255),
        replyTime datetime,
        updateTime datetime,
        postBarID bigint,
        replierID bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_Post (
        id bigint not null auto_increment,
        company tinyblob,
        createDate datetime,
        postID varchar(255),
        postName varchar(255),
        postNote varchar(255),
        processName varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_PostBar (
        id bigint not null auto_increment,
        content varchar(255),
        createTime datetime,
        ifAccessAllReply bit not null,
        lastReplyTime datetime,
        thumbnailUrl varchar(255),
        title varchar(255),
        updateTime datetime,
        createrID bigint,
        lastReplierID bigint,
        postSubjectID bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_PostSubject (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        creater tinyblob,
        name varchar(255),
        updateTime datetime,
        updater tinyblob,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_Project (
        id bigint not null auto_increment,
        batchFullName varchar(255),
        batchShortName varchar(255),
        projectFullName varchar(255),
        projectID varchar(255),
        projectShortName varchar(255),
        unitFullName varchar(255),
        unitShortName varchar(255),
        companyID bigint,
        ownerID bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_UserPostMappingTable (
        UserID bigint not null,
        PostID bigint not null,
        primary key (UserID, PostID)
    ) ENGINE=InnoDB;

    create table R_company (
        id bigint not null auto_increment,
        comapnyAddress varchar(255),
        companyCode varchar(255),
        companyFax varchar(255),
        companyFullName varchar(255),
        companyId varchar(255),
        companyMaster varchar(255),
        companyNature varchar(255),
        companyNote varchar(255),
        companyPositionGPS varchar(255),
        companyShortNameCN varchar(255),
        companyShortNameEN varchar(255),
        companyTel varchar(255),
        companyWebSite varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_groupMember (
        group_id bigint not null,
        user_id bigint not null,
        primary key (group_id, user_id)
    ) ENGINE=InnoDB;

    create table R_messages (
        id bigint not null auto_increment,
        content varchar(255),
        createTime datetime,
        sendTime datetime,
        status integer not null,
        title varchar(255),
        updateTime datetime,
        creater_id bigint,
        owner_id bigint,
        sender_id bigint,
        updater_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_news (
        id bigint not null auto_increment,
        content varchar(255),
        createTime datetime,
        expiredTime datetime,
        thumbnailUrl varchar(255),
        title varchar(255),
        updateTime datetime,
        creater_id bigint,
        newsType_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_newsType (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        name varchar(255),
        updateTime datetime,
        creater_id bigint,
        updater_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_role (
        id bigint not null auto_increment,
        description varchar(64),
        name varchar(20),
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_user (
        id bigint not null auto_increment,
        QQ varchar(255),
        acceptRegistration bit not null,
        account_expired bit not null,
        account_locked bit not null,
        allowCreateGroup bit not null,
        allowCreateProject bit not null,
        credentials_expired bit not null,
        email varchar(255) not null,
        account_enabled bit,
        first_name varchar(50) not null,
        lastLoginDte datetime,
        lastLogoutDate datetime,
        last_name varchar(50) not null,
        loginCount integer not null,
        loginDate datetime,
        password varchar(255) not null,
        password_hint varchar(255),
        phone_number varchar(255),
        registrationDate datetime,
        totalHours integer not null,
        userState varchar(255),
        username varchar(50) not null,
        version integer,
        companyID bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table R_userGroup (
        id bigint not null auto_increment,
        comment varchar(255),
        createTime datetime,
        name varchar(255),
        updateTime datetime,
        creater_id bigint,
        owner_id bigint,
        updater_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table messagereceivegroups (
        message_id bigint not null,
        usergroup_id bigint not null,
        primary key (message_id, usergroup_id)
    ) ENGINE=InnoDB;

    create table messagereceiveusers (
        message_id bigint not null,
        user_id bigint not null,
        primary key (message_id, user_id)
    ) ENGINE=InnoDB;

    create table postBarReplyGroups (
        postbar_id bigint not null,
        group_id bigint not null,
        primary key (postbar_id, group_id)
    ) ENGINE=InnoDB;

    create table postBarReplyUsers (
        postbar_id bigint not null,
        user_id bigint not null,
        primary key (postbar_id, user_id)
    ) ENGINE=InnoDB;

    create table postBarViewGroups (
        postbar_id bigint not null,
        group_id bigint not null,
        primary key (postbar_id, group_id)
    ) ENGINE=InnoDB;

    create table postBarViewUsers (
        postbar_id bigint not null,
        user_id bigint not null,
        primary key (postbar_id, user_id)
    ) ENGINE=InnoDB;

    create table user_role (
        user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) ENGINE=InnoDB;

    alter table R_user 
        add constraint UK_3e6i69jk0e72s7ja3l83b1m32  unique (email);

    alter table R_user 
        add constraint UK_1uaf33us60un7fa0qno7d00f7  unique (username);

    alter table R_Peply 
        add constraint FK_f0s4sqlgrdhje2hrxxrlgqsvr 
        foreign key (postBarID) 
        references R_PostBar (id);

    alter table R_Peply 
        add constraint FK_ecxqk949v5un5kp1gob78awmd 
        foreign key (replierID) 
        references R_user (id);

    alter table R_PostBar 
        add constraint FK_odoxuy8ch3ixtx6nw8cgn1apt 
        foreign key (createrID) 
        references R_user (id);

    alter table R_PostBar 
        add constraint FK_rlf396wxnrmth9dqbr6a6662 
        foreign key (lastReplierID) 
        references R_user (id);

    alter table R_PostBar 
        add constraint FK_hcuom4pafr88ry7ud1iybod39 
        foreign key (postSubjectID) 
        references R_PostSubject (id);

    alter table R_Project 
        add constraint FK_mpqpmfiwhereygs96jkx2qdyh 
        foreign key (companyID) 
        references R_company (id);

    alter table R_Project 
        add constraint FK_ic4k24puw1jm8abskdyji5cgg 
        foreign key (ownerID) 
        references R_user (id);

    alter table R_UserPostMappingTable 
        add constraint FK_62tpcel38b2r87ho0fxo84rxy 
        foreign key (PostID) 
        references R_Post (id);

    alter table R_UserPostMappingTable 
        add constraint FK_fnqwjpqu5aexd3xorofbg7tmm 
        foreign key (UserID) 
        references R_user (id);

    alter table R_groupMember 
        add constraint FK_erl00brea7g2c9lmj8a7s7tdq 
        foreign key (user_id) 
        references R_user (id);

    alter table R_groupMember 
        add constraint FK_8qe1taybdan95ly5884y4p6pi 
        foreign key (group_id) 
        references R_userGroup (id);

    alter table R_messages 
        add constraint FK_oy9dn821ecooys4oqc1g4emm4 
        foreign key (creater_id) 
        references R_user (id);

    alter table R_messages 
        add constraint FK_fcrfg6ki6bkhqwt45uok9tnlv 
        foreign key (owner_id) 
        references R_user (id);

    alter table R_messages 
        add constraint FK_8jkkk4vlfxj4wguqnou69kmva 
        foreign key (sender_id) 
        references R_user (id);

    alter table R_messages 
        add constraint FK_fjn7rqepejl334r8wjilumdq2 
        foreign key (updater_id) 
        references R_user (id);

    alter table R_news 
        add constraint FK_n7v03feqrig5e4b2tcn32j2le 
        foreign key (creater_id) 
        references R_user (id);

    alter table R_news 
        add constraint FK_eqkeuufu32cp4obfwmislftv0 
        foreign key (newsType_id) 
        references R_newsType (id);

    alter table R_newsType 
        add constraint FK_j22qolkdgbhpwovei218yf4yb 
        foreign key (creater_id) 
        references R_user (id);

    alter table R_newsType 
        add constraint FK_cgjhua6xesas9bxnywl3377am 
        foreign key (updater_id) 
        references R_user (id);

    alter table R_user 
        add constraint FK_rk4q0m6fnqh0wld3ay5j5qnf 
        foreign key (companyID) 
        references R_company (id);

    alter table R_userGroup 
        add constraint FK_dvc1en6gcmamgg5fap7uybro9 
        foreign key (creater_id) 
        references R_user (id);

    alter table R_userGroup 
        add constraint FK_9wl1uvd2gxmcdnr2c3atojn7j 
        foreign key (owner_id) 
        references R_user (id);

    alter table R_userGroup 
        add constraint FK_745k2yy8372quwdtu09wqdrkq 
        foreign key (updater_id) 
        references R_user (id);

    alter table messagereceivegroups 
        add constraint FK_l78ngyyiugpy9jmd2l8ed7sij 
        foreign key (usergroup_id) 
        references R_userGroup (id);

    alter table messagereceivegroups 
        add constraint FK_ex1o65in29vrul0tlfpont4ub 
        foreign key (message_id) 
        references R_messages (id);

    alter table messagereceiveusers 
        add constraint FK_j43cxfks6d1e49mkls59wkw4l 
        foreign key (user_id) 
        references R_user (id);

    alter table messagereceiveusers 
        add constraint FK_7vvf1c34l5aw5e3n8ad0gnot7 
        foreign key (message_id) 
        references R_messages (id);

    alter table postBarReplyGroups 
        add constraint FK_cj3ws5p8b6h8rxp7064a3160j 
        foreign key (group_id) 
        references R_userGroup (id);

    alter table postBarReplyGroups 
        add constraint FK_f92wt6414rdttou1oe2hhcajs 
        foreign key (postbar_id) 
        references R_PostBar (id);

    alter table postBarReplyUsers 
        add constraint FK_lssyu03swvkc35nhp6g5c9j99 
        foreign key (user_id) 
        references R_user (id);

    alter table postBarReplyUsers 
        add constraint FK_sufxnlot6as99etc4ijqowinv 
        foreign key (postbar_id) 
        references R_PostBar (id);

    alter table postBarViewGroups 
        add constraint FK_dmauq13o1kegvf44dyhrcfq3i 
        foreign key (group_id) 
        references R_userGroup (id);

    alter table postBarViewGroups 
        add constraint FK_a7peknboqvm1tvsaidoximet8 
        foreign key (postbar_id) 
        references R_PostBar (id);

    alter table postBarViewUsers 
        add constraint FK_r8nsta9nmhw84cg6vlnktj4gy 
        foreign key (user_id) 
        references R_user (id);

    alter table postBarViewUsers 
        add constraint FK_hutlbo8cpta163flmbafyvdyt 
        foreign key (postbar_id) 
        references R_PostBar (id);

    alter table user_role 
        add constraint FK_it77eq964jhfqtu54081ebtio 
        foreign key (role_id) 
        references R_role (id);

    alter table user_role 
        add constraint FK_apcc8lxk2xnug8377fatvbn04 
        foreign key (user_id) 
        references R_user (id);
