package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Course;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseMapper {
    @Select("select * from course where chapter=#{chapter}")
    List<Course> getCoursesOfChapter(int chapter);

    @Select("select * from course where chapter=#{chapter} and courseId=#{courseId}")
    Course getCourse(int chapter, int courseId);

    @Update("update course set uri=#{uri} where chapter=#{chapter} and courseId=#{courseId}")
    int updateCourseUri(Course course);

    @Insert("insert into course(chapter,uri,courseId,duration) values(#{chapter},#{uri},#{courseId},#{duration})")
    int addCourse(Course course);

    @Insert("insert into belong_course(chapter,courseId,exerciseId) values(#{chapter},#{courseId},#{exerciseId})")
    int bindExerciseToCourse(int chapter, int courseId, int exerciseId);

    @Delete("delete from course where  chapter=#{chapter} and courseId=#{courseId}")
    int deleteCourse(int chapter, int courseId);
}
