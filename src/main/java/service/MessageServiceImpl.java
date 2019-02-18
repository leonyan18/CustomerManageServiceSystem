package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.Msg;

@Service
public class MessageServiceImpl implements MessageService {
    private final SimpMessagingTemplate messaging;

    @Autowired
    public MessageServiceImpl(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }

    @Override
    public void sendMsgTo(Msg msg) {
        messaging.convertAndSendToUser(""+msg.getTo(),"/queue/notifications",msg.toString());
    }
}
