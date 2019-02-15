package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class HomeController {
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public JSONObject login(String username, String password) {
        JSONObject object = new JSONObject();
        object.put("token", UUID.randomUUID().toString());
        return object;
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public String logout() {
        return "success";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public JSONObject info(String token) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray(Arrays.<Object>asList("admin"));
        object.put("roles", array);
        object.put("name", "marvel");
        object.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return object;
    }
}
