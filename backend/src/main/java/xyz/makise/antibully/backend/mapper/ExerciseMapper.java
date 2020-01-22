package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Exercise;

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

    @Insert("insert into belong_course(chapter,courseId,exerciseId) values(#{chapter},#{courseId},#{exerciseId})")
    int bindExerciseAndCourse(int exerciseId, int chapter, int courseId);

    /*
    * 常规增删改
    * */
    int addExercise(Exercise exercise);

    int updateExerciseAnswer(Exercise exercise);

    int deleteExercise(int exerciseId);
}
