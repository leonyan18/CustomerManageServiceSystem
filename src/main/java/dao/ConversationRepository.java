package dao;

import DTO.ConversationDTO;
import entity.ConversationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationEntity,Integer> {
    /**
     * description:获取所有的对话
     * @param
     * @return java.util.List<DTO.ConversationDTO>
     */
    @Query("select new DTO.ConversationDTO(c)FROM ConversationEntity c")
    public List<ConversationDTO> findAllConversation(Pageable pageable);
}
