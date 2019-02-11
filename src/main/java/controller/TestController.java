package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yan
 */
@Controller
@Api
public class TestController {
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @ApiOperation("websocket 测试页")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/erroMessage",method = RequestMethod.GET)
    @ApiOperation("错误信息表")
    public String erroMessage(){
        return "erroMessage";
    }
}
