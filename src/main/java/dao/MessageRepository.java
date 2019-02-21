package dao;

import DTO.MessageDTO;
import entity.AnswerEntity;
import entity.MessageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yan
 */
public interface MessageRepository extends JpaRepository<MessageEntity,Integer> {
    MessageEntity findByMid(int mid);
    long countByConversation_Cid(int cid);
    @Query("select new DTO.MessageDTO(m) from MessageEntity m where m.conversation.cid=?1")
    List<MessageDTO> findChatRecord(int cid, Pageable pageable);
}
