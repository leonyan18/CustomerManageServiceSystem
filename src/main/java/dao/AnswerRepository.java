package dao;

import entity.AnswerEntity;
import entity.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yan
 */
public interface AnswerRepository extends JpaRepository<AnswerEntity,Integer> {
}
