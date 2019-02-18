package service;

import entity.ClassificationEntity;

import java.util.List;

/**
 * @author yan
 */
public interface ClassificationService {
    int addClassification(ClassificationEntity classificationEntity);
    List<ClassificationEntity> findAllClassification();
}
