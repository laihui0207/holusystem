package com.huivip.holu.service;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/messageReply")
public interface MessageReplyManager extends GenericManager<MessageReply, Long> {

    List<MessageReply> replyListOfMessage(String messageID,ExtendedPaginatedList list);
    @POST
    Message replyMessage(@FormParam("content") String content,@FormParam("userId") String userId,
                         @FormParam("messageId") String messageId);

}