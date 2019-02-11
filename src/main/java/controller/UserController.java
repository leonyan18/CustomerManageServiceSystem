package controller;

import DTO.UserDTO;
import entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import util.ErrorMessage;
import util.LogicException;

import java.util.List;


@RestController
@Api("会话")
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ApiOperation("添加用户")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public int addUser(UserEntity userEntity) {
        if ("".equals(userEntity.getUsername())||"".equals(userEntity.getPassword())) {
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }
        return userService.addUser(userEntity);
    }
    @ApiOperation(value = "添加客服",notes = "用户变为客服")
    @RequestMapping(value = "addStaff", method = RequestMethod.POST)
    public void addStaff(int uid) {
        userService.addStaff(uid);
    }

    @ApiOperation(value = "删除客服",notes = "客服变为用户")
    @RequestMapping(value = "deleteStaff", method = RequestMethod.POST)
    public void deleteStaff(int uid) {
        userService.deleteStaff(uid);
    }

    @ApiOperation(value = "查找所有用户")
    @RequestMapping(value = "findAllUser", method = RequestMethod.POST)
    public List<UserDTO> findAllUser() {
        return userService.findAllUser();
    }

    @ApiOperation(value = "查找所有客服")
    @RequestMapping(value = "findAllStaff", method = RequestMethod.POST)
    public List<UserDTO> findAllStaff() {
        return userService.findAllStaff();
    }

    @ApiOperation(value = "根据关键词查找用户",notes = "没有关键词也可")
    @RequestMapping(value = "findAllUserByKeyword", method = RequestMethod.POST)
    public List<UserDTO> findAllUserByKey(@ApiParam(required = false) @RequestParam(defaultValue = "") String keyword, int pageNum, int pageSize) {
        keyword="%"+keyword+"%";
        return userService.findAllUserByKeyword(keyword, PageRequest.of(pageNum-1,pageSize));
    }

    @ApiOperation(value = "根据关键词查找客服",notes = "没有关键词也可")
    @RequestMapping(value = "findAllStaffByKeyword", method = RequestMethod.POST)
    public List<UserDTO> findAllStaffByKeyword(@ApiParam(required = false) @RequestParam(defaultValue = "")String keyword, int pageNum, int pageSize) {
        keyword="%"+keyword+"%";
        return userService.findAllStaffByKeyword(keyword,PageRequest.of(pageNum-1,pageSize));
    }
    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public void findAllStaffByKeyword(UserDTO userDTO) {
        if ("".equals(userDTO.getUsername())||userDTO.getType()!=null) {
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }
        userService.updateUser(userDTO);
    }
}
