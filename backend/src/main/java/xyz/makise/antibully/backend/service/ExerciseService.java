package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Exercise;
import xyz.makise.antibully.backend.mapper.ExerciseMapper;

@Service
public class ExerciseService {
    final
    ExerciseMapper mapper;

    public ExerciseService(ExerciseMapper mapper) {
        this.mapper = mapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    Exercise getExerciseOfCourse(int chapter, int courseId) {
        Exercise exercise = null;
        try {
            int exerciseId = mapper.getExerciseIdOfCourse(chapter, courseId);
            exercise = mapper.getExercise(exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return exercise;
    }

    int bindExerciseAndCourse(int exerciseId, int chapter, int courseId) {
        int retVal = -1;
        try {
            retVal = mapper.bindExerciseAndCourse(exerciseId, chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retVal;
    }
}
