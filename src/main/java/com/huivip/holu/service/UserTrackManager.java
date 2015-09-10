package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.UserTrack;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface UserTrackManager extends GenericManager<UserTrack, Long> {
    
}