package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import service.MessageService;
import websocket.Msg;
import websocket.Shout;

import javax.management.Notification;

/**
 * @author yan
 * @date 2018/11/3 20:04
 * @descripition
 */
@Controller
@Api
public class MarcoController {
    private final MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(MarcoController.class);
    private final SimpUserRegistry userRegistry;
    private final WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    @Autowired
    public MarcoController(MessageService messageService, SimpUserRegistry userRegistry, WebSocketMessageBrokerStats webSocketMessageBrokerStats) {
        this.messageService = messageService;
        this.userRegistry = userRegistry;
        this.webSocketMessageBrokerStats = webSocketMessageBrokerStats;
    }

    @MessageMapping("/marco")
    @ApiOperation("消息处理")
    public Shout handleShout(Shout incoming) {
        System.out.println("++++++++++++");
        logger.info("Received message" + incoming.getMessage());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        Shout outgoing = new Shout();
        outgoing.setMessage("Polo!");
        return outgoing;
    }

    @MessageMapping("/message")
    @SendToUser("/queue/notifications")
    @ApiOperation("消息处理")
    public void handleMessage(Msg msg) {
        System.out.println(webSocketMessageBrokerStats.getWebSocketSessionStatsInfo());
        messageService.sendMsgTo(msg);
    }
}
