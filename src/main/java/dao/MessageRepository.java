package dao;

import dto.MessageDTO;
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
    @Query("select new dto.MessageDTO(m) from MessageEntity m where m.conversation.cid=?1 order by m.sendtime asc")
    List<MessageDTO> findChatRecord(int cid, Pageable pageable);
    @Query("select new dto.MessageDTO(m) from MessageEntity m where m.conversation.cid=?1 order by m.sendtime asc")
    List<MessageDTO> findChatRecord(int cid);
}
