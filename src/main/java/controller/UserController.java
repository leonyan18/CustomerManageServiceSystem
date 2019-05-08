package controller;

import dto.UserDTO;
import entity.UserEntity;
import entity.UserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("直接添加客服")
    @RequestMapping(value = "addNewStaff", method = RequestMethod.POST)
    public int addNewStaff(UserEntity userEntity) {
        if ("".equals(userEntity.getUsername())||"".equals(userEntity.getPassword())) {
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }
        int uid= userService.addUser(userEntity);
        userService.addStaff(uid);
        return uid;
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

    @ApiOperation(value = "根据关键词查找用户",notes = "没有关键词也可")
    @RequestMapping(value = "findAllUserByKeyword", method = RequestMethod.POST)
    public List<UserDTO> findAllUserByKeyword( @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize) {
        keyword="%"+keyword+"%";
        return userService.findAllUserByKeyword(keyword, PageRequest.of(pageNum-1,pageSize));
    }
    @ApiOperation(value = "根据关键词查找客服数目",notes = "没有关键词也可")
    @RequestMapping(value = "countAllUserByKeyword", method = RequestMethod.POST)
    public long countAllUserByKeyword( @RequestParam(defaultValue = "")String keyword) {
        keyword="%"+keyword+"%";
        return userService.countAllUserByKeyword(keyword);
    }

    @ApiOperation(value = "根据关键词查找对应类型用户",notes = "没有关键词也可")
    @RequestMapping(value = "findAllUserByKeywordAndType", method = RequestMethod.POST)
    public List<UserDTO> findAllUserByKeywordAndType(UserType type, @RequestParam(defaultValue = "")String keyword, @RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "20")int pageSize) {
        keyword="%"+keyword+"%";
        return userService.findAllUserByKeywordAndType(type,keyword,PageRequest.of(pageNum-1,pageSize));
    }

    @ApiOperation(value = "根据关键词查找对应类型用户数目",notes = "没有关键词也可")
    @RequestMapping(value = "countAllUserByKeywordAndType", method = RequestMethod.POST)
    public long countAllUserByKeywordAndType(UserType type,@RequestParam(defaultValue = "")String keyword) {
        keyword="%"+keyword+"%";
        return userService.countAllUserByKeywordAndType(type, keyword);
    }
    @ApiOperation(value = "更新用户信息")
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public void updateUser(UserDTO userDTO) {
        if ("".equals(userDTO.getUsername())||userDTO.getType()!=null) {
            throw LogicException.le(ErrorMessage.WRONG_FORMAT);
        }
        userService.updateUser(userDTO);
    }
}
