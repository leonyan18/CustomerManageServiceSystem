package service;

import dao.ClassificationRepository;
import entity.ClassificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author yan
 */
@Service
public class ClassificationServiceImpl implements ClassificationService{
    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public int addClassification(ClassificationEntity classificationEntity){
            return classificationRepository.save(classificationEntity).getCid();
    }

    @Override
    public List<ClassificationEntity> findAllClassification(){
        return classificationRepository.findAll();
    }
}
