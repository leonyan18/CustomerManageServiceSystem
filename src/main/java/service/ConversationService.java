package service;

import DTO.ConversationDTO;

import java.util.List;

public interface ConversationService {
    /**
     * description:获取所有的对话
     * @param
     * @return java.util.List<DTO.ConversationDTO>
     */
    List<ConversationDTO> findAllConversation();
}
