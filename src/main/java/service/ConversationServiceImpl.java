package service;

import DTO.ConversationDTO;
import dao.ConversationRepository;
import entity.ConversationEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @author yan
 */
@Service
public class ConversationServiceImpl implements ConversationService{
    private final ConversationRepository conversationRepository;

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
        UserEntity userEntity2=new UserEntity();
        //注意及时修改
        userEntity2.setUid(matchStaff());
        conversationEntity.setStaff(userEntity2);
        conversationEntity.setStatus(true);
        conversationEntity.setStarttime(new Date(System.currentTimeMillis()));
        return conversationRepository.save(conversationEntity).getCid();
    }

    @Override
    public void endConversation(int customerId,double evaluate) {
        ConversationEntity conversationEntity=conversationRepository.findByCid(customerId);
        if (evaluate!=0.0) {
            conversationEntity.setEvaluate(evaluate);
        }
        if(!conversationEntity.getStatus()){
            return;
        }
        conversationEntity.setStatus(false);
        conversationEntity.setEndtime(new Date(System.currentTimeMillis()));
        conversationRepository.save(conversationEntity);
    }

    @Override
    public long countConversation() {
        return conversationRepository.count();
    }

    @Override
    public int matchStaff() {
        return 3;
    }

}
