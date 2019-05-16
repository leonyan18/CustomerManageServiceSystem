package controller;

import entity.AnswerEntity;
import entity.GreetingEntity;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AnswerService;
import service.GreetingService;

import java.util.List;

/**
 * @author yan
 */
@RestController
@RequestMapping("greeting")
public class GreetingController {
    private final GreetingService greetingService;
    private static final Logger logger = LogManager.getLogger(GreetingController.class);

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @ApiOperation("添加问候语")
    @RequestMapping(value = "/addGreeting",method = RequestMethod.POST)
    public int addGreeting(GreetingEntity greetingEntity){
        return greetingService.addGreetingEntity(greetingEntity);
    }

    @ApiOperation("更新问候语")
    @RequestMapping(value = "/updateGreeting",method = RequestMethod.POST)
    public int updateGreeting(GreetingEntity greetingEntity){
        return greetingService.updateGreetingEntity(greetingEntity);
    }

    @ApiOperation("删除问候语")
    @RequestMapping(value = "/deleteGreeting",method = RequestMethod.POST)
    public int deleteGreeting(int gid){
        return greetingService.deleteGreetingEntity(gid);
    }

    @ApiOperation("问候语列表")
    @RequestMapping(value = "/GreetingList",method = RequestMethod.POST)
    public List<GreetingEntity> greetingList(@RequestParam(defaultValue = "")String keyword, @RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize){
        logger.info(keyword);
        return greetingService.findAllByContentLike("%"+keyword+"%",PageRequest.of(pageNum-1, pageSize));
    }

    @ApiOperation("问候语数量")
    @RequestMapping(value = "/countGreetings",method = RequestMethod.POST)
    public long countGreetings(@RequestParam(defaultValue = "")String keyword){
        logger.info(keyword);
        return greetingService.countGreetings("%"+keyword+"%");
    }
}
