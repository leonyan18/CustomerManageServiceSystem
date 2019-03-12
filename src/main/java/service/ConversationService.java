package service;

import DTO.ConversationDTO;
import entity.ConversationEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yan
 */
public interface ConversationService {
    /**
     * description:获取所有的对话
     * @param
     * @return java.util.List<DTO.ConversationDTO>
     */
    List<ConversationDTO> findAllConversation(Pageable pageable);
    int startConversation(int customerId);
    void endConversation(int customerId,double evaluate);
    long countConversation();
    ConversationEntity matchStaff(int conversationId);
}
