package service;

import dto.ConversationDTO;
import dao.ConversationRepository;
import entity.ConversationEntity;
import entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
/**
 * @author yan
 */
@Service
public class ConversationServiceImpl implements ConversationService{
    private final ConversationRepository conversationRepository;
    private static final Logger logger = LogManager.getLogger(ConversationServiceImpl.class);

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<ConversationDTO> findAllConversation(Pageable pageable) {
        return conversationRepository.findAllConversation(pageable);
    }

    @Override
    public int startConversation(int customerId) {
        ConversationEntity conversationEntity=new ConversationEntity();
        UserEntity userEntity1=new UserEntity();
        userEntity1.setUid(customerId);
        conversationEntity.setCustomer(userEntity1);
        conversationEntity.setStatus(true);
        conversationEntity.setStarttime(new Date(System.currentTimeMillis()));
        return conversationRepository.save(conversationEntity).getCid();
    }

    @Override
    public void endConversation(int customerId,double evaluate) {
        ConversationEntity conversationEntity=conversationRepository.findByCid(customerId);
        logger.info(conversationEntity);
        if (0.0 == evaluate) {
            evaluate=3.0;
        }
        conversationEntity.setEvaluate(evaluate);
        if(!conversationEntity.getStatus()){
            return;
        }
        conversationEntity.setStatus(false);
        conversationEntity.setEndtime(new Date());
        logger.info(conversationEntity);
        conversationRepository.save(conversationEntity);
    }

    @Override
    public long countConversation(String keyword) {
        return conversationRepository.countConversationBykeyword(keyword);
    }

    @Override
    public ConversationEntity matchStaff(int conversationId) {
        ConversationEntity conversationEntity=conversationRepository.findByCid(conversationId);
        UserEntity userEntity2=new UserEntity();
        userEntity2.setUid(3);
        conversationEntity.setStaff(userEntity2);
        return conversationRepository.save(conversationEntity);
    }

    @Override
    public List<ConversationDTO> findConversationByKeyword(Pageable pageable, String keyword) {
//        logger.info(conversationRepository.findConversationByKeyword(keyword,pageable).get(0));
        return conversationRepository.findConversationByKeyword(keyword,pageable);
    }
}
