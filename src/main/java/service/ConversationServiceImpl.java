package service;

import DTO.ConversationDTO;
import dao.ConversationRepository;
import entity.ConversationEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ConversationDTO> findAllConversation() {
        return conversationRepository.findAllConversation();
    }

    @Override
    public void startConversation() {
    }

}
