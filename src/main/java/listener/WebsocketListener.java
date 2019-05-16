package listener;

import controller.MessageController;
import entity.ConversationEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import service.ConversationService;
import websocket.User;

@Component
public class WebsocketListener implements ApplicationListener {
    private final ConversationService conversationService;
    private static final Logger logger = LogManager.getLogger(WebsocketListener.class);

    @Autowired
    public WebsocketListener(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event.getClass()== SessionDisconnectEvent.class){
            SessionDisconnectEvent sessionDisconnectEvent= (SessionDisconnectEvent) event;
            StompHeaderAccessor sha=StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
            User user= (User) sha.getUser();
            if (user != null) {
                conversationService.endConversation(user.getConversationId(),3.0);
                logger.info("断开时"+user.toString());
            }
        }
    }
}
