package service;

import DTO.MessageDTO;
import dao.MessageRepository;
import entity.MessageEntity;
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
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(SimpMessagingTemplate messaging,MessageRepository messageRepository) {
        this.messaging = messaging;
        this.messageRepository=messageRepository;
    }

    @Override
    public void sendMsgTo(Msg msg) {
        msg.setSendTime(new Date(System.currentTimeMillis()));
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
