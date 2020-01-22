package xyz.makise.antibully.backend.service;

import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Exercise;
import xyz.makise.antibully.backend.entity.ExerciseRepo;
import xyz.makise.antibully.backend.mapper.ExerciseRepoMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExerciseRepoService {
    final
    ExerciseRepoMapper exerciseRepoMapper;

    public ExerciseRepoService(ExerciseRepoMapper exerciseRepoMapper) {
        this.exerciseRepoMapper = exerciseRepoMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    List<ExerciseRepo> getAllExerciseRepos() {
        List<ExerciseRepo> exerciseRepos = null;
        try {
            exerciseRepos = exerciseRepoMapper.getAllExerciseRepos();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return exerciseRepos;
    }

//    TODO: 这一部分也应该放在UserService中
//    Map<String,Integer> getRepoStatusOfUser(int repoId, int userId) {
//        HashMap<String, Integer> map = new HashMap<>();
//        int correctNum = 0;
//        int exerciseNum = 0;
//        try {
//            exerciseNum = exerciseRepoMapper.getExerciseNumOfRepo(repoId);
//            correctNum = exerciseRepoMapper.getCorrectNumOfUserOnRepo(repoId, userId);
//            map.put("exerciseNum", exerciseNum);
//            map.put("correctNum", correctNum);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return map;
//    }

    int bindExerciseToRepo(int repoId, int exerciseId) {
        int retVal = 0;
        try {
            retVal = exerciseRepoMapper.bindExerciseToRepo(repoId, exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retVal;
    }

    int addExerciseRepo(ExerciseRepo exerciseRepo) {
        try {
            return exerciseRepoMapper.addExerciseRepo(exerciseRepo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    int deleteExerciseRepo(int repoId) {
        try {
            return exerciseRepoMapper.deleteExerciseRepo(repoId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    int updateTitleOfExerciseRepo(ExerciseRepo exerciseRepo) {
        try {
            return exerciseRepoMapper.updateTitleOfExerciseRepo(exerciseRepo);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return 0;
    }
}
