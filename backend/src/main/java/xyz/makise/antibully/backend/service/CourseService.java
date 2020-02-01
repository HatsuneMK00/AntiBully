package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Course;
import xyz.makise.antibully.backend.mapper.CourseMapper;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    final
    CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
    * 根据chapter和courseId获取课程
    * */
    public Course getCourse(int chapter, int courseId) {
        Course course = null;
        try {
            course = courseMapper.getCourse(chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return course;
    }

    /*
    * 根据chapter获取所有该chapter的课程
    * */
    public List<Course> getCourseOfChapter(int chapter) {
        List<Course> courses = null;
        try {
            courses = courseMapper.getCoursesOfChapter(chapter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return courses;
    }

    /*
    * 获取所有课程 包含课程对应的习题
    * 管理员用
    * */
    public List<Map<String,Object>> getAllCourses() {
        try {
            return courseMapper.getAllCoursesWithExercise();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /*
     * 更改课程和习题的绑定
     * */
    public int updateBindOfExerciseAndCourse(int exerciseId, int chapter, int courseId) {
        try {
            return courseMapper.updateBindOfExerciseAndCourse(exerciseId, chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

    /*
    * 为课程和习题增加一个绑定
    * 只能增加不能修改
    * */
    public int bindExerciseAndCourse(int chapter, int courseId, int exerciseId) {
        int retVal = -1;
        try {
            retVal = courseMapper.bindExerciseToCourse(chapter, courseId, exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retVal;
    }

    /*
    * 添加一个课程
    * */
    public int addCourse(Course course) {
        try {
            return courseMapper.addCourse(course);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    /*
    * 删除一个课程
    * */
    public int deleteCourse(int chapter, int courseId) {
        try {
            return courseMapper.deleteCourse(chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    /*
    * 更新课程视频uri
    * */
    public int updateCourseUri(Course course) {
        try {
            return courseMapper.updateCourseUri(course);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }
}
