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
    long countAllByContentLikeAndClassification_Cid(String keyword, int cid);
    long countAllByContentLike(String keyword);
    List<ProblemEntity> findAllByContentLikeAndClassification_Cid(String keyword, Pageable pageable,int cid);
    List<ProblemEntity> findAllByContentLike(String keyword, Pageable pageable);
}
