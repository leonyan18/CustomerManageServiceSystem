package service;

import DTO.MessageDTO;
import controller.ConversationController;
import dao.ConversationRepository;
import dao.MessageRepository;
import dao.ProblemRepository;
import entity.ConversationEntity;
import entity.MessageEntity;
import entity.ProblemEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import util.ResultUtil;
import websocket.Msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final SimpMessagingTemplate messaging;
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ProblemRepository problemRepository;
    private final ConversationService conversationService;

    @Autowired
    public MessageServiceImpl(ConversationService conversationService,SimpMessagingTemplate messaging,MessageRepository messageRepository,ConversationRepository conversationRepository,ProblemRepository problemRepository) {
        this.messaging = messaging;
        this.messageRepository=messageRepository;
        this.conversationRepository=conversationRepository;
        this.problemRepository=problemRepository;
        this.conversationService=conversationService;
    }

    @Override
    public void handleMessage(Msg msg) {
        ConversationEntity conversationEntity=conversationRepository.findByCid(msg.getCid());
        logger.info(conversationEntity.getStaff());
        if(conversationEntity.getCustomer().getUid()==msg.getFrom()){
            if(conversationEntity.getStaff()!=null) {
                msg.setTo(conversationEntity.getStaff().getUid());
            }
        }else{
            msg.setTo(conversationEntity.getCustomer().getUid());
        }
        logger.info(msg.toString());
        MessageEntity messageEntity=new MessageEntity(msg);
        messageRepository.save(messageEntity);
        if(conversationEntity.getStaff()!=null) {
            messaging.convertAndSendToUser(""+msg.getTo(),"/queue/notifications",msg.toString());
        }else{
            logger.info(ResultUtil.getSentiment(msg.getContent()));
            if (ResultUtil.getSentiment(msg.getContent())>=0.0){
                List<ProblemEntity> problemEntityList=problemRepository.findAll();
                HashMap<String,ProblemEntity> problemEntityHashMap=new HashMap<>();
                List<String> strings=new ArrayList<>();
                for (ProblemEntity p:problemEntityList) {
                    strings.add(p.getContent());
                    problemEntityHashMap.put(p.getContent(),p);
                }
                List<String> newStrs= ResultUtil.getResult(strings,msg.getContent());
                StringBuilder ans= new StringBuilder();
                for (String s:newStrs) {
                    ProblemEntity problemEntity=problemEntityHashMap.get(s);
                    ans.append(problemEntity.getContent()+"\n");
                    ans.append(problemEntity.getAnswer().getContent()+"\n");
                }
                Msg newMsg=new Msg();
                newMsg.setContent(ans.toString());
                newMsg.setTo(msg.getFrom());
                newMsg.setCid(msg.getCid());
                messaging.convertAndSendToUser(""+newMsg.getTo(),"/queue/notifications",newMsg.toString());
                logger.info(newMsg.toString());
            }else{
                msg.setTo(conversationService.matchStaff(conversationEntity.getCid()).getStaff().getUid());
                messaging.convertAndSendToUser(""+msg.getTo(),"/queue/notifications",msg.toString());
            }

        }
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
