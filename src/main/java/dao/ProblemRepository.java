package dao;

import entity.ProblemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yan
 */
public interface ProblemRepository extends JpaRepository<ProblemEntity,Integer> {
    ProblemEntity findByPid(int pid);
    List<ProblemEntity> findAllByContentLikeAndClassification_Cid(String keyword, Pageable pageable,int cid);
}
