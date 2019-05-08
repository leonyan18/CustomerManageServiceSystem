package service;

import dao.GreetingRepository;
import entity.GreetingEntity;
import entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.ErrorMessage;
import util.LogicException;

import java.util.Date;
import java.util.List;

/**
 * @author yan
 */
@Service
public class GreetingServiceImpl implements GreetingService{
    private final GreetingRepository greetingRepository;

    public GreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public List<GreetingEntity> findAllByContentLike(String keyword,Pageable pageable) {
        return greetingRepository.findAllByContentLike(keyword,pageable);
    }

    @Override
    public int updateGreetingEntity(GreetingEntity greetingEntity) {
        int gid=greetingEntity.getGid();
        if(greetingRepository.existsById(gid)){
            GreetingEntity orgin=greetingRepository.findByGid(gid);
            orgin.setContent(greetingEntity.getContent());
            orgin.setUpdatetime(new Date(System.currentTimeMillis()));
            greetingRepository.save(orgin);
        }else{
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
        return 0;
    }

    @Override
    public int addGreetingEntity(GreetingEntity greetingEntity) {
        greetingEntity.setCreatetime(new Date(System.currentTimeMillis()));
        greetingEntity.setUpdatetime(new Date(System.currentTimeMillis()));
        if(greetingEntity.getCreateuser()==null){
            UserEntity userEntity=new UserEntity();
            userEntity.setUid(1);
            greetingEntity.setCreateuser(userEntity);
        }
        return greetingRepository.save(greetingEntity).getGid();
    }

    @Override
    public int deleteGreetingEntity(int gid) {
        if(greetingRepository.existsById(gid)){
            greetingRepository.deleteById(gid);
        }else{
            throw LogicException.le(ErrorMessage.NO_SUCH_ENTITY);
        }
        return 0;
    }
}
