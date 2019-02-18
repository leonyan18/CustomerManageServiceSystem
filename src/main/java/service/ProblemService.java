package service;

import entity.AnswerEntity;
import entity.ProblemEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yan
 */
public interface ProblemService {
    int addProblem(ProblemEntity problemEntity, AnswerEntity answerEntity);
    int addProblem(ProblemEntity problemEntity, int aid);
    List<ProblemEntity> findAllProblemByKeywordAndClassification(String keyword,Pageable pageable,int classificationId);
    void deleteProblem(int pid);
    void updateProblem(ProblemEntity problemEntity);
    long countAllByContentLikeAndClassification_Cid(String keyword, int cid);
}
