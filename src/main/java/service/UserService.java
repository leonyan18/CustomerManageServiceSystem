package service;

import DTO.UserDTO;
import entity.UserEntity;
import entity.UserType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    int addUser(UserEntity userEntity);
    void addStaff(int uid);
    void deleteStaff(int uid);
    List<UserDTO> findAllStaff();
    List<UserDTO> findAllUser();
    List<UserDTO> findAllUserByKeyword(String keyword, Pageable pageable);
    List<UserDTO> findAllUserByKeywordAndType(UserType type, String keyword, Pageable pageable);
    long countAllUserByKeyword(String keyword);
    long countAllUserByKeywordAndType(UserType type,String keyword);
    void updateUser(UserDTO userDTO);
}
