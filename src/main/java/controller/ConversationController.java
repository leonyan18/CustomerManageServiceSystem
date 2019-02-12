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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    private static final Logger logger = LogManager.getLogger(ConversationController.class);

    @Autowired
    public ConversationController(UserService userService, ConversationService conversationService) {
        this.userService = userService;
        this.conversationService=conversationService;
    }

    @RequestMapping(value = "/getAllConversation",method = RequestMethod.GET)
    @ApiOperation(value="获取所有对话",notes = "为了安全不输出证件信息")
    public List<ConversationDTO> getConversation(@RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize){
        return conversationService.findAllConversation(PageRequest.of(pageNum-1, pageSize));
    }

    @RequestMapping(value = "/startConversation",method = RequestMethod.GET)
    @ApiOperation(value="开始对话")
    public void startConversation(int customerId,@RequestParam(defaultValue = "1") int staffId){
        conversationService.startConversation(customerId, staffId);
    }
}
