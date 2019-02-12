package dao;

import entity.AnnouncementEntity;
import entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yan
 */
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity,Integer> {
    AnnouncementEntity findByAid(int aid);
}
