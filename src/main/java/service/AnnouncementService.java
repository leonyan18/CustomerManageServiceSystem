package service;

import entity.AnnouncementEntity;
import entity.AnswerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yan
 */
public interface AnnouncementService {
    int addAnnouncement(AnnouncementEntity answerEntity);
    List<AnnouncementEntity> announcementList(Pageable pageable);
    long countAnnouncement();
    void deleteAnnouncement(int aid);
}
