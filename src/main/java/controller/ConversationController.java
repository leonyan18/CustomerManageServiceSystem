package controller;

import DTO.ConversationDTO;
import dao.ConversationRepository;
import dao.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author yan
 */
@RestController
@Api("会话")
@RequestMapping("conversation")
public class ConversationController {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private static final Logger logger = LogManager.getLogger(ConversationController.class);

    @Autowired
    public ConversationController(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository=conversationRepository;
    }

    @RequestMapping(value = "/getAllConversation",method = RequestMethod.GET)
    @ApiOperation(value="获取所有对话")
    public ConversationDTO getConversation(){
        List<ConversationDTO> conversationEntityList=conversationRepository.findAllConversation();
        for (ConversationDTO conversationEntity:conversationEntityList) {
            logger.info(conversationEntity.getStarttime());
        }
        return conversationEntityList.get(0);
    }
}
