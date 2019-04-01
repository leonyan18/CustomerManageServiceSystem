package controller;

import dao.ProblemRepository;
import entity.AnswerEntity;
import entity.ProblemEntity;
import entity.UserEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(ProblemController.class);
    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @ApiOperation(value = "添加问题",notes = "只需填写问题正文和答案正文或者答案id")
    @RequestMapping(value = "addProblem", method = RequestMethod.POST)
    public int addProblem(ProblemEntity problemEntity) {
        AnswerEntity answerEntity=problemEntity.getAnswer();
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
    public List<ProblemEntity> problemList(@RequestParam(defaultValue = "")String keyword,
                                           @RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize,
                                           @RequestParam(defaultValue = "0")int classificationId) {
        keyword="%"+keyword+"%";
        return problemService.findAllProblemByKeywordAndClassification(keyword, PageRequest.of(pageNum-1,pageSize),classificationId);
    }

    @ApiOperation(value = "问题数目",notes = "关键词搜索和类别")
    @RequestMapping(value = "countProblem", method = RequestMethod.POST)
    public long countProblem(@RequestParam(defaultValue = "")String keyword,@RequestParam(defaultValue = "0")int classificationId) {
        keyword="%"+keyword+"%";
        return problemService.countAllByContentLikeAndClassification_Cid(keyword, classificationId);
    }

    @ApiOperation(value = "更新问题")
    @RequestMapping(value = "updateProblem", method = RequestMethod.POST)
    public void updateProblem(ProblemEntity problemEntity) {
        if(problemEntity.getPid()==0){
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
