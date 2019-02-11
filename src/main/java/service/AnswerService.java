package service;

import entity.AnswerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnswerService {
    int addAnswer(AnswerEntity answerEntity);
    List<AnswerEntity> answerList(Pageable pageable);
    void deleteAnswer(int aid);
}
