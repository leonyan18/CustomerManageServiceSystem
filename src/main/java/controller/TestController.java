package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author yan
 */
@Controller
@Api(tags="错误信息表以及websocket测试页")
public class TestController {
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @ApiOperation(value = "websocket 测试页",notes = "发送时要发送来自谁的用户id")
    public String home(HttpSession httpSession){
        httpSession.setAttribute("userId",4);
        return "home";
    }

    @RequestMapping(value = "/erroMessage",method = RequestMethod.GET)
    @ApiOperation("错误信息表")
    public String erroMessage(){
        return "erroMessage";
    }
}
