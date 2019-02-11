package service;

import entity.ClassificationEntity;

import java.util.List;

public interface ClassificationService {
    int addClassification(ClassificationEntity classificationEntity);
    List<ClassificationEntity> findAllClassification();
}
