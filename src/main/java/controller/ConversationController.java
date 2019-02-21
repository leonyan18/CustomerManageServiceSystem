package controller;

import DTO.ConversationDTO;
import dao.ConversationRepository;
import dao.UserRepository;
import entity.ConversationEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import service.ConversationService;
import service.UserService;

import java.util.List;


/**
 * @author yan
 */
@RestController
@Api("会话")
@RequestMapping("conversation")
public class ConversationController {
    private final UserService userService;
    private final ConversationService conversationService;
    private final WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    private static final Logger logger = LogManager.getLogger(ConversationController.class);

    @Autowired
    public ConversationController(WebSocketMessageBrokerStats webSocketMessageBrokerStats,UserService userService, ConversationService conversationService) {
        this.userService = userService;
        this.conversationService=conversationService;
        this.webSocketMessageBrokerStats=webSocketMessageBrokerStats;
    }

    @RequestMapping(value = "/getAllConversation",method = RequestMethod.POST)
    @ApiOperation(value="获取所有对话",notes = "为了安全不输出证件信息")
    public List<ConversationDTO> getConversation(@RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize){
        return conversationService.findAllConversation(PageRequest.of(pageNum-1, pageSize));
    }

    @RequestMapping(value = "/startConversation",method = RequestMethod.POST)
    @ApiOperation(value="开始对话")
    public void startConversation(int customerId,@RequestParam(defaultValue = "1") int staffId){
        conversationService.startConversation(customerId, staffId);
    }

    @RequestMapping(value = "/endConversation",method = RequestMethod.POST)
    @ApiOperation(value="结束对话")
    public void endConversation(int customerId,@RequestParam(defaultValue = "0")double evaluate){
        conversationService.endConversation(customerId,evaluate);
    }
    @RequestMapping(value = "getCurrentConversationNum",method = RequestMethod.POST)
    @ApiOperation("获取当前正在进行的会话数")
    public String getCurrentConversationNum(){
        String orgin=webSocketMessageBrokerStats.getWebSocketSessionStatsInfo();
        orgin=orgin.split(" ")[0];
        return orgin;
    }
}
