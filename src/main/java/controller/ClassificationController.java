package controller;

import entity.ClassificationEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.ClassificationService;
import service.ProblemService;

import java.util.List;

/**
 * @author yan
 */
@RestController
@Api("类别")
@RequestMapping("Classification")
public class ClassificationController {
    private final ClassificationService classificationService;
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @ApiOperation("获取所有分类")
    @RequestMapping(value = "/findAllClassification",method = RequestMethod.POST)
    public List<ClassificationEntity> findAllClassification(){
        return classificationService.findAllClassification();
    }
}
