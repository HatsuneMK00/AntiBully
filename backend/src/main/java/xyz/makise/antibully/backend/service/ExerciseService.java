package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Exercise;
import xyz.makise.antibully.backend.mapper.ExerciseMapper;
import xyz.makise.antibully.backend.mapper.ExerciseRepoMapper;

import java.util.List;

@Service
public class ExerciseService {
    final
    ExerciseMapper exerciseMapper;
    final
    ExerciseRepoMapper exerciseRepoMapper;

    public ExerciseService(ExerciseMapper exerciseMapper, ExerciseRepoMapper exerciseRepoMapper) {
        this.exerciseMapper = exerciseMapper;
        this.exerciseRepoMapper = exerciseRepoMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /*
     * 与课程相关
     * */

    public Exercise getExerciseOfCourse(int chapter, int courseId) {
        Exercise exercise = null;
        try {
            int exerciseId = exerciseMapper.getExerciseIdOfCourse(chapter, courseId);
            exercise = exerciseMapper.getExercise(exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return exercise;
    }

    /*
     * 与题库相关
     * */
    public List<Exercise> getExercisesOfRepo(int repoId) {
        List<Exercise> exercises = null;
        try {
            exercises = exerciseMapper.getExercisesOfRepo(repoId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return exercises;
    }

    /*
    * 返回repo的所有题目的id
    * 管理员用
    * */
    public List<Integer> getExerciseIdsOfRepo(int repoId) {
        try {
            return exerciseMapper.getExerciseIdsOfRepo(repoId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public Exercise getExercise(int exerciseId) {
        try {
            return exerciseMapper.getExercise(exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /*
    * 获取所有联系
    * 管理员用
    * */
    public List<Exercise> getAllExercises() {
        try {
            return exerciseMapper.getAllExercises();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public int addExercise(Exercise exercise) {
        try {
            return exerciseMapper.addExercise(exercise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    public int deleteExercise(int exerciseId) {
        try {
            return exerciseMapper.deleteExercise(exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    public int updateExercise(Exercise exercise) {
        try {
            return exerciseMapper.updateExercise(exercise);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public boolean validateExercise(Exercise exercise) {
        if (exercise.getAnswer() == null || exercise.getContent() == null
                || exercise.getReason() == null || exercise.getType() == null
                || exercise.getChoiceA() == null || exercise.getChoiceB() == null
                || exercise.getChoiceC() == null || exercise.getChoiceD() == null){
            return false;
        }
        return true;
    }
}
