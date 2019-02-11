package controller;

import dao.ProblemRepository;
import entity.AnswerEntity;
import entity.ProblemEntity;
import entity.UserEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProblemService;
import util.ErrorMessage;
import util.LogicException;

import java.util.List;

@RestController
@RequestMapping("problem")
public class ProblemController {
    private final ProblemService problemService;
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @ApiOperation(value = "添加问题",notes = "只需填写问题正文和答案正文或者答案id")
    @RequestMapping(value = "addProblem", method = RequestMethod.POST)
    public int addProblem(ProblemEntity problemEntity, AnswerEntity answerEntity) {
        if(0 != answerEntity.getAid()){
            return problemService.addProblem(problemEntity, answerEntity.getAid());
        }
        else if (!"".equals(problemEntity.getContent())&&!"".equals(answerEntity.getContent())) {
            return problemService.addProblem(problemEntity, answerEntity);
        }
        throw LogicException.le(ErrorMessage.WRONG_FORMAT);
    }

    @ApiOperation(value = "问题列表",notes = "关键词搜索和类别")
    @RequestMapping(value = "problemList", method = RequestMethod.POST)
    public List<ProblemEntity> problemList(@RequestParam(defaultValue = "")String keyword, int pageNum, int pageSize,int classificationId) {
        keyword="%"+keyword+"%";
        for (ProblemEntity p:problemService.findAllProblemByKeywordAndClassification(keyword, PageRequest.of(pageNum-1,pageSize),classificationId)){
            logger.info(p.toString());
        }
        return problemService.findAllProblemByKeywordAndClassification(keyword, PageRequest.of(pageNum-1,pageSize),classificationId);
    }

    @ApiOperation(value = "更新问题",notes = "需上传答案id和类别id")
    @RequestMapping(value = "updateProblem", method = RequestMethod.POST)
    public void updateProblem(ProblemEntity problemEntity) {
        if(problemEntity.getPid()==0
                ||problemEntity.getClassification().getCid()==0
                ||problemEntity.getAnswer().getAid()==0){
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }else{
            problemService.updateProblem(problemEntity);
        }
    }

    @ApiOperation(value = "删除问题")
    @RequestMapping(value = "deleteProblem", method = RequestMethod.POST)
    public void deleteProblem(int pid) {
        if(pid==0){
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }else{
            problemService.deleteProblem(pid);
        }
    }

}
