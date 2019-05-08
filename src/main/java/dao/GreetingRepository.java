package dao;

import entity.GreetingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GreetingRepository extends JpaRepository<GreetingEntity, Integer> {
    List<GreetingEntity> findAllByContentLike(String keyword,Pageable pageable);
    GreetingEntity findByGid(int gid);
}
