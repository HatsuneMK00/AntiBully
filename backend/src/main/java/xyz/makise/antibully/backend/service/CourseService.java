package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Course;
import xyz.makise.antibully.backend.mapper.CourseMapper;

import java.util.List;

@Service
public class CourseService {
    final
    CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    Course getCourse(int chapter, int courseId) {
        Course course = null;
        try {
            course = courseMapper.getCourse(chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return course;
    }

    List<Course> getCourseOfChapter(int chapter) {
        List<Course> courses = null;
        try {
            courses = courseMapper.getCoursesOfChapter(chapter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return courses;
    }

    int bindExerciseAndCourse(int chapter, int courseId, int exerciseId) {
        int retVal = -1;
        try {
            retVal = courseMapper.bindExerciseToCourse(chapter, courseId, exerciseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retVal;
    }

    int addCourse(Course course) {
        try {
            return courseMapper.addCourse(course);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    int deleteCourse(int chapter, int courseId) {
        try {
            return courseMapper.deleteCourse(chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    int updateCourseUri(Course course) {
        try {
            return courseMapper.updateCourseUri(course);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }
}
