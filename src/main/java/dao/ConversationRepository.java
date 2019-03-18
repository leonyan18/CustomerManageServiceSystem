package dao;

import dto.ConversationDTO;
import entity.ConversationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Integer> {
    /**
     * description:获取所有的对话
     *
     * @param
     * @return java.util.List<dto.ConversationDTO>
     */
    @Query("select new dto.ConversationDTO(c)FROM ConversationEntity c")
    List<ConversationDTO> findAllConversation(Pageable pageable);

    ConversationEntity findByCid(int cid);
    @Query("select avg(c.evaluate) FROM ConversationEntity c where c.staff.uid=?1 group by c.staff.uid")
    Double getMeanEvaluate(int uid);

    @Query("select c.staff.uid FROM ConversationEntity c where c.staff.uid=?1 group by c.staff.uid")
    Double getStaffId(int uid);
}
