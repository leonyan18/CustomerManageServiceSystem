package dao;

import entity.AnswerEntity;
import entity.ClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yan
 */
public interface ClassificationRepository extends JpaRepository<ClassificationEntity,Integer> {

}
