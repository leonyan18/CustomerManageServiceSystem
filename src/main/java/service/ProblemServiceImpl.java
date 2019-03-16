package service;

import com.huaban.analysis.jieba.JiebaSegmenter;
import dao.AnswerRepository;
import dao.ClassificationRepository;
import dao.ProblemRepository;
import entity.AnswerEntity;
import entity.ClassificationEntity;
import entity.ProblemEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.ErrorMessage;
import util.LogicException;

import java.sql.Date;
import java.util.List;

/**
 * @author yan
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    private static final Logger logger = LogManager.getLogger(ProblemServiceImpl.class);
    private final ProblemRepository problemRepository;
    private final AnswerRepository answerRepository;
    private final ClassificationRepository classificationRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository, AnswerRepository answerRepository, ClassificationRepository classificationRepository) {
        this.problemRepository = problemRepository;
        this.answerRepository = answerRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public int addProblem(ProblemEntity problemEntity, AnswerEntity answerEntity) {
        answerEntity.setCreateTime(new Date(System.currentTimeMillis()));
        answerRepository.save(answerEntity);
        JiebaSegmenter segmenter = new JiebaSegmenter();
        logger.info(problemEntity.getContent());
        logger.info(StringUtils.join(segmenter.sentenceProcess(problemEntity.getContent())," "));
        problemEntity.setKeywords(StringUtils.join(segmenter.sentenceProcess(problemEntity.getContent())," "));
        problemEntity.setAnswer(answerEntity);
        problemEntity.setCreateTime(new Date(System.currentTimeMillis()));
        return problemRepository.save(problemEntity).getPid();
    }

    @Override
    public int addProblem(ProblemEntity problemEntity, int aid) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAid(aid);
        problemEntity.setCreateTime(new Date(System.currentTimeMillis()));
        problemEntity.setAnswer(answerEntity);
        JiebaSegmenter segmenter = new JiebaSegmenter();
        problemEntity.setKeywords(StringUtils.join(segmenter.sentenceProcess(problemEntity.getContent())," "));
        return problemRepository.save(problemEntity).getPid();
    }

    @Override
    public List<ProblemEntity> findAllProblemByKeywordAndClassification(String keyword, Pageable pageable, int classificationId) {
        if(classificationId!=0) {
            return problemRepository.findAllByContentLikeAndClassification_Cid(keyword, pageable, classificationId);
        }else{
            return problemRepository.findAllByContentLike(keyword,pageable);
        }
    }

    @Override
    public void deleteProblem(int pid) {
        if (problemRepository.existsById(pid)) {
            problemRepository.deleteById(pid);
        } else {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }

    @Override
    public void updateProblem(ProblemEntity problemEntity) {
        if (problemRepository.existsById(problemEntity.getPid())) {
            ProblemEntity problemEntity1 = problemRepository.findByPid(problemEntity.getPid());
            AnswerEntity answer=problemEntity.getAnswer();
            ClassificationEntity classification=problemEntity.getClassification();
            if ("".equals(problemEntity.getContent())) {
                problemEntity1.setContent(problemEntity.getContent());
            }
            if(classification!=null&& classificationRepository.existsById(classification.getCid())) {
                problemEntity1.getClassification().setCid(classification.getCid());
            }
            if(answer!=null&& answerRepository.existsById(answer.getAid())) {
                AnswerEntity answerEntity=answerRepository.findByAid(answer.getAid());
                answerEntity.setContent(answer.getContent());
                answerEntity.setUpdateTime(new Date(System.currentTimeMillis()));
                answerRepository.save(answerEntity);
                problemEntity1.getAnswer().setAid(problemEntity.getAnswer().getAid());
            }
            problemEntity1.setContent(problemEntity.getContent());
            problemEntity1.setUpdateTime(new Date(System.currentTimeMillis()));
            problemRepository.save(problemEntity1);
        } else {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }

    @Override
    public long countAllByContentLikeAndClassification_Cid(String keyword, int cid) {
        if (cid!=0) {
            return problemRepository.countAllByContentLikeAndClassification_Cid(keyword, cid);
        }else{
            return problemRepository.countAllByContentLike(keyword);
        }
    }

    @Override
    public List<ProblemEntity> findAll() {
        return problemRepository.findAll();
    }
}
