package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.ExerciseRepo;

import java.util.List;

@Repository
public interface ExerciseRepoMapper {
    @Select("select * from exercise_repo where repoId=#{repoId}")
    ExerciseRepo getExerciseRepo(int repoId);

    @Select("select * from exercise_repo")
    List<ExerciseRepo> getAllExerciseRepos();

    @Select("select count(*) from belong_repo where repoId=#{repoId}")
    int getExerciseNumOfRepo(int repoId);

    @Insert("insert into belong_repo(repoId,exerciseId) values(#{repoId},#{exerciseId})")
    int bindExerciseToRepo(int repoId, int exerciseId);

    @Delete("delete from belong_repo where repoId=#{repoId} and exerciseId=#{exerciseId}")
    int unbindExerciseAndRepo(int repoId, int exerciseId);

    /*
    * 和用户相关
    * */
//    TODO: 下面这一部分应该放在UserMapper中
//    @Select("select correct_num from take_exer_repo where repoId=#{repoId} and userId=#{userId}")
//    int getCorrectNumOfUserOnRepo(int repoId, int userId);
//
//    @Insert("insert into take_exer_repo(userId,repoId,correct_num) values(#{userId},#{repoId},#{correct_num})")
//    int bindExerciseRepoAndUser(int repoId, int userId, int correct_num);
//
//    @Update("update take_exer_repo set correct_num=#{correct_num} where userId=#{userId} and repoId=#{repoId}")
//    int updateBindOfExerciseRepoAndUser(int userId, int repoId, int correct_num);

    /*
    * 常规增删改
    * */
    @Options(useGeneratedKeys = true, keyProperty = "repoId")
    @Insert("insert into exercise_repo(title) values(#{title})")
    int addExerciseRepo(ExerciseRepo exerciseRepo);

    @Delete("delete from exercise_repo where repoId=#{repoId}")
    int deleteExerciseRepo(int repoId);

    @Update("update exercise_repo set title=#{title} where repoId=#{repoId}")
    int updateTitleOfExerciseRepo(ExerciseRepo exerciseRepo);
}
