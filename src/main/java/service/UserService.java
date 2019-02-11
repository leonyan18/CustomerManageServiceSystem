package service;

import DTO.UserDTO;
import entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    int addUser(UserEntity userEntity);
    void addStaff(int uid);
    void deleteStaff(int uid);
    List<UserDTO> findAllStaff();
    List<UserDTO> findAllUser();
    List<UserDTO> findAllUserByKeyword(String keyword, Pageable pageable);
    List<UserDTO> findAllStaffByKeyword(String keyword, Pageable pageable);
    void updateUser(UserDTO userDTO);
}
