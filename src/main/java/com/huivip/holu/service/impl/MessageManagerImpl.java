package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.*;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.service.MessageManager;

import com.huivip.holu.service.MessageReceiverManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

@Service("messageManager")
@WebService(serviceName = "MessageService", endpointInterface = "com.huivip.holu.service.MessageManager")
public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager {
    MessageDao messageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CustomGroupDao customGroupDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    MessageReceiverManager messageReceiverManager;

    @Autowired
    public MessageManagerImpl(MessageDao messageDao) {
        super(messageDao);
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> messageByOwner(User user,ExtendedPaginatedList list) {
        return messageDao.messageByOwner(user,list);
    }

    @Override
    public List<MessageReceiver> myMessage(String userId, String page, String pageSize) {
        User user=userDao.get(Long.parseLong(userId));
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setPageSize(Integer.parseInt(pageSize));
        list.setIndex(Integer.parseInt(page));
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        List<MessageReceiver> datalist=messageReceiverManager.listMyMessage(userId,list);
        return list.getList();
    }

    @Override
    public int newMessageCount(String userId) {
        return messageDao.newMessageCount(userId);
    }

    @Override
    public Message getMessage(String id) {
        return messageDao.get(Long.parseLong(id));
    }

    @Override
    public Message readMessage(String id) {
        return messageDao.updateMessageStatus(id,"3");
    }

    @Override
    public Message saveMessage(String title, String content,String userId, String messageId) {
        User user=userDao.get(Long.parseLong(userId));
        Message message=new Message();
        if(messageId!=null && messageId.length()>0 && !messageId.equalsIgnoreCase("undefined")){
            message=messageDao.get(Long.parseLong(messageId));
        }
        message.setTitle(title);
        message.setContent(content);
        message.setCreater(user);
        message.setUpdater(user);
        message.setUpdateTime(new Timestamp(new Date().getTime()));
        message.setOwner(user);
        Message savedMessage=messageDao.save(message);

        MessageReceiver receiver=new MessageReceiver();
        receiver.setMessage(savedMessage);
        receiver.setStatus(1);
        receiver.setCreateTime(new Timestamp(new Date().getTime()));
        receiver.setReceiver(user);
        messageReceiverManager.save(receiver);

        return message;
    }

    @Override
    public Message sendMessage(String messageId, String users, String groups,String departments, String userId) {
        User user=userDao.get(Long.parseLong(userId));
        Message message=messageDao.get(Long.parseLong(messageId));
        List<User> receiverList=new ArrayList<>();
        if(null!=users && users.length()>0 && !users.equalsIgnoreCase("undefined")){
            if(message.getReceiveUsers()!=null){
                message.getReceiveUsers().clear();
            }
            String[] usersArray=users.split(";");
            for(String id:usersArray){
                User user1=userDao.get(Long.parseLong(id));
                if(!receiverList.contains(user1)){
                    receiverList.add(user1);
                }
                message.getReceiveUsers().add(user1);
            }
        }
        if(null!=groups && groups.length()>0 && !groups.equalsIgnoreCase("undefined")){
            if(message.getReceiveGroups()!=null){
                message.getReceiveGroups().clear();
            }
            String[] groupArray=groups.split(";");
            for(String id:groupArray){
                CustomGroup ug=customGroupDao.get(Long.parseLong(id));
                message.getReceiveGroups().add(ug);
                for(User u:ug.getMembers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        if(null!=departments && departments.length()>0 && !departments.equalsIgnoreCase("undefined")){
            String[] departmentArray=departments.split(";");
            for(String id: departmentArray){
                Department department=departmentDao.getDepartmentByDepartmentID(id);
                for(User u:department.getUsers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        message.setStatus(1);
        messageDao.save(message);

        Message copyMessage=new Message();
        copyMessage.setTitle(message.getTitle());
        copyMessage.setContent(message.getContent());
        copyMessage.setCreater(message.getCreater());
        copyMessage.setStatus(2);

        copyMessage.setSender(user);

        for(User u:receiverList){
            copyMessage.setId(null);
            copyMessage.setOwner(u);
            messageDao.save(copyMessage);
        }
        return message;
    }

    @Override
    public void deleteMessage(String messageId) {
        messageReceiverManager.deleteReceiverOfMessage(messageId);
        messageDao.remove(Long.parseLong(messageId));
    }
}