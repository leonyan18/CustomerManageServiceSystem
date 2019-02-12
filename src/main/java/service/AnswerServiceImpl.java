package service;

import dao.AnswerRepository;
import entity.AnswerEntity;
import entity.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.ErrorMessage;
import util.LogicException;

import java.sql.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public int addAnswer(AnswerEntity answerEntity) {
        answerEntity.setCreateTime(new Date(System.currentTimeMillis()));
        return answerRepository.save(answerEntity).getAid();
    }


    @Override
    public List<AnswerEntity> answerList(Pageable pageable) {
        return answerRepository.findAll(pageable).getContent();
    }

    @Override
    public long countAnswer() {
        return answerRepository.count();
    }

    @Override
    public void deleteAnswer(int aid) {
        if(answerRepository.existsById(aid)){
            answerRepository.deleteById(aid);
        }else{
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }
}
