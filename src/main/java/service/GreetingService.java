package service;

import dto.ConversationDTO;
import entity.ConversationEntity;
import entity.GreetingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yan
 */
public interface GreetingService {
    List<GreetingEntity> findAllByContentLike(String keyword,Pageable pageable);
    int updateGreetingEntity(GreetingEntity greetingEntity);
    int addGreetingEntity(GreetingEntity greetingEntity);
    int deleteGreetingEntity(int gid);
    long countGreetings(String keyword);
}
