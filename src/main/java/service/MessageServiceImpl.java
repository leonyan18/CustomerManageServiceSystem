package service;

import dto.MessageDTO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.ConversationRepository;
import dao.MessageRepository;
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
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final SimpMessagingTemplate messaging;
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ProblemService problemService;
    private final ConversationService conversationService;

    @Autowired
    public MessageServiceImpl(ConversationService conversationService,SimpMessagingTemplate messaging,MessageRepository messageRepository,ConversationRepository conversationRepository,ProblemService problemService) {
        this.messaging = messaging;
        this.messageRepository=messageRepository;
        this.conversationRepository=conversationRepository;
        this.problemService=problemService;
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
        logger.info(msg.toString());
        messageRepository.save(messageEntity);
        if(conversationEntity.getStaff()!=null) {
            messaging.convertAndSendToUser(""+msg.getTo(),"/queue/notifications",msg.toString());
        }else{
            logger.info(ResultUtil.getSentiment(msg.getContent()));
            if (ResultUtil.checkMotion(msg.getContent())){
                Msg newMsg=new Msg();
                newMsg.setContent(matchAnswer(msg.getContent()).toString());
                newMsg.setTo(msg.getFrom());
                newMsg.setCid(msg.getCid());
                messaging.convertAndSendToUser(""+newMsg.getTo(),"/queue/notifications",newMsg.toString());
                logger.info(newMsg.toString());
            }else{
                msg.setTo(conversationService.matchStaff(conversationEntity.getCid()).getStaff().getUid());
                logger.info(msg.toString());
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

    @Override
    public JSONArray matchAnswer(String problem) {
        List<ProblemEntity> problemEntityList=problemService.findAll();
        HashMap<String,ProblemEntity> problemEntityHashMap=new HashMap<>();
        List<String> strings=new ArrayList<>();
        for (ProblemEntity p:problemEntityList) {
            strings.add(p.getContent());
            problemEntityHashMap.put(p.getContent(),p);
        }
        List<String> newStrs= ResultUtil.getResult(strings,problem);
        JSONArray jsonArray=new JSONArray();
        for (String s:newStrs) {
            ProblemEntity problemEntity=problemEntityHashMap.get(s);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("problem",problemEntity.getContent());
            jsonObject.put("answer",problemEntity.getAnswer().getContent());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
