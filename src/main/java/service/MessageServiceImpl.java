package service;

import DTO.MessageDTO;
import controller.ConversationController;
import dao.ConversationRepository;
import dao.MessageRepository;
import entity.ConversationEntity;
import entity.MessageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.Msg;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final SimpMessagingTemplate messaging;
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    @Autowired
    public MessageServiceImpl(SimpMessagingTemplate messaging,MessageRepository messageRepository,ConversationRepository conversationRepository) {
        this.messaging = messaging;
        this.messageRepository=messageRepository;
        this.conversationRepository=conversationRepository;
    }

    @Override
    public void sendMsgTo(Msg msg) {
        msg.setSendTime(new Date(System.currentTimeMillis()));
        ConversationEntity conversationEntity=conversationRepository.findByCid(msg.getCid());
        if(conversationEntity.getStaff().getUid()==msg.getFrom()){
            msg.setTo(conversationEntity.getCustomer().getUid());
        }else{
            msg.setTo(conversationEntity.getStaff().getUid());
        }
        logger.info(msg.toString());
        MessageEntity messageEntity=new MessageEntity(msg);
        messaging.convertAndSendToUser(""+msg.getTo(),"/queue/notifications",msg.toString());
        messageRepository.save(messageEntity);
    }

    @Override
    public long count(int cid) {
        return messageRepository.countByConversation_Cid(cid);
    }

    @Override
    public List<MessageDTO> findChatRecord(int cid, Pageable pageable) {
        return messageRepository.findChatRecord(cid,pageable);
    }
}
