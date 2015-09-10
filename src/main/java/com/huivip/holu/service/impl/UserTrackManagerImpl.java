package com.huivip.holu.service.impl;

import com.huivip.holu.dao.UserTrackDao;
import com.huivip.holu.model.UserTrack;
import com.huivip.holu.service.UserTrackManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("userTrackManager")
@WebService(serviceName = "UserTrackService", endpointInterface = "com.huivip.holu.service.UserTrackManager")
public class UserTrackManagerImpl extends GenericManagerImpl<UserTrack, Long> implements UserTrackManager {
    UserTrackDao userTrackDao;

    @Autowired
    public UserTrackManagerImpl(UserTrackDao userTrackDao) {
        super(userTrackDao);
        this.userTrackDao = userTrackDao;
    }
}