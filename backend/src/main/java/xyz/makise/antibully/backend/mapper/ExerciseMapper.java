package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Exercise;

import java.util.List;

@Repository
public interface ExerciseMapper {
    /*
    * 和课程相关
    * */
    @Select("select * from exercise where exerciseId=#{exerciseId}")
    Exercise getExercise(int exerciseId);

    @Select("select exerciseId from belong_course where chapter=#{chapter} and courseId=#{courseId}")
    int getExerciseIdOfCourse(int chapter, int courseId);

//    TODO: 可以考虑增加一个使用join操作的查找，而不是两次查找组合，可能速度上会快一点

//    @Insert("insert into belong_course(chapter,courseId,exerciseId) values(#{chapter},#{courseId},#{exerciseId})")
//    int bindExerciseAndCourse(int exerciseId, int chapter, int courseId);

    /*
    * 和题库相关
    * */
    @Select("select exercise.exerciseId,content,answer,type,reason,choiceA,choiceB,choiceC,choiceD" +
            "from exercise join belong_repo on exercise.exerciseId=belong_repo.exerciseId " +
            "where repoId=#{repoId}")
    List<Exercise> getExercisesOfRepo(int repoId);

    /*
    * 常规增删改查
    * */
    @Options(useGeneratedKeys = true, keyProperty = "exerciseId")
    @Insert("insert into exercise(content,answer,type,reason) values(#{content},#{answer},#{type},#{reason})")
    int addExercise(Exercise exercise);

    @Update("update exercise set answer=#{answer} where exerciseId=#{exerciseId}")
    int updateExerciseAnswer(Exercise exercise);

    @Delete("delete from exercise where exerciseId=#{exerciseId}")
    int deleteExercise(int exerciseId);

    @Select("select * from exercise")
    List<Exercise> getAllExercises();
}
