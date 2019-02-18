package service;

import dao.AnnouncementRepository;
import entity.AnnouncementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.ErrorMessage;
import util.LogicException;

import java.util.List;
/**
 * @author yan
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService{
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public int addAnnouncement(AnnouncementEntity answerEntity) {
        return announcementRepository.save(answerEntity).getAid();
    }

    @Override
    public List<AnnouncementEntity> announcementList(Pageable pageable) {
        return announcementRepository.findAll(pageable).getContent();
    }

    @Override
    public long countAnnouncement() {
        return announcementRepository.count();
    }

    @Override
    public void deleteAnnouncement(int aid) {
        if (announcementRepository.existsById(aid)) {
            announcementRepository.delete(announcementRepository.findByAid(aid));
        } else {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }
}
