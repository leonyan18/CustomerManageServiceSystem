package controller;

import DTO.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import service.MessageService;
import websocket.Msg;
import websocket.Shout;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/3 20:04
 * @descripition
 */
@RestController
@Api(tags = "消息接口")
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;
    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
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
    @ApiOperation("消息处理")
    public void handleMessage(Msg msg) {
        messageService.sendMsgTo(msg);
    }

    @ApiOperation("消息数目")
    @RequestMapping(value = "countMessage", method = RequestMethod.POST)
    public long count(int cid) {
        return messageService.count(cid);
    }

    @ApiOperation("消息记录")
    @RequestMapping(value = "findChatRecord", method = RequestMethod.POST)
    public List<MessageDTO> findChatRecord(int cid, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        return messageService.findChatRecord(cid, PageRequest.of(pageNum - 1, pageSize));
    }
    @MessageExceptionHandler
    public void handleMessageException(Throwable t){
        logger.error("Error handling messsage"+t.getMessage());
    }

}
