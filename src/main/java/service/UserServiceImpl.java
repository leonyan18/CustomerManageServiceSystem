package service;

import dto.UserDTO;
import dao.ConversationRepository;
import dao.UserRepository;
import entity.UserEntity;
import entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.ErrorMessage;
import util.LogicException;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public int addUser(UserEntity userEntity) {
        userEntity.setNewStatus(true);
        userEntity.setCreateTime(new Date(System.currentTimeMillis()));
        return userRepository.save(userEntity).getUid();
    }

    @Override
    public void addStaff(int uid) {
        UserEntity userEntity = userRepository.findByUid(uid);
        if (userEntity != null) {
            if (userEntity.getType() == UserType.USER) {
                userEntity.setNewStatus(true);
                userEntity.setType(UserType.STAFF);
                userRepository.save(userEntity);
            }
        } else {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }

    @Override
    public void deleteStaff(int uid) {
        UserEntity userEntity = userRepository.findByUid(uid);
        if (userEntity != null) {
            if (userEntity.getType() == UserType.STAFF) {
                userEntity.setType(UserType.USER);
                userRepository.save(userEntity);
            }
        } else {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
    }

    @Override
    public List<UserDTO> findAllStaff() {
        return userRepository.findAllStaff();
    }

    @Override
    public List<UserDTO> findAllUser() {
        return convertTo(userRepository.findAll());
    }

    @Override
    public List<UserDTO> findAllUserByKeyword(String keyword, Pageable pageable) {
        return userRepository.findAllUserByKeyword(keyword, pageable);
    }

    @Override
    public List<UserDTO> findAllUserByKeywordAndType(UserType type, String keyword, Pageable pageable) {
        List<UserDTO> userDTOS=userRepository.findAllUserByKeywordAndType(type,keyword, pageable);
        if (type==UserType.STAFF) {
            for (UserDTO u:userDTOS) {
                u.setMeanEvaluate(conversationRepository.getMeanEvaluate(u.getUid()));
            }
        }
        return userDTOS;
    }

    @Override
    public long countAllUserByKeyword(String keyword) {
        return userRepository.countAllUserByKeyword(keyword);
    }

    @Override
    public long countAllUserByKeywordAndType(UserType type, String keyword) {
        return userRepository.countAllUserByKeywordAndType(type,keyword);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUid(userDTO.getUid());
        if (userEntity == null) {
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
        userEntity.setType(userDTO.getType());
        userEntity.setUsername(userDTO.getUsername());
        userRepository.save(userEntity);
    }

    public List<UserDTO> convertTo(List<UserEntity> userEntities) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity u : userEntities) {
            userDTOS.add((new UserDTO(u)));
        }
        return userDTOS;
    }
}
