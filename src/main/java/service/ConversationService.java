package service;

import dto.ConversationDTO;
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
     * @return java.util.List<dto.ConversationDTO>
     */
    List<ConversationDTO> findAllConversation(Pageable pageable);
    int startConversation(int customerId);
    void endConversation(int customerId,double evaluate);
    long countConversation(String keyword);
    ConversationEntity matchStaff(int conversationId);
    List<ConversationDTO> findConversationByKeyword(Pageable pageable,String keyword);
}
