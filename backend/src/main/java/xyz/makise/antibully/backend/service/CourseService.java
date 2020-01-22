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
    CourseMapper mapper;

    public CourseService(CourseMapper mapper) {
        this.mapper = mapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    Course getCourse(int chapter, int courseId) {
        Course course = null;
        try {
            course = mapper.getCourse(chapter, courseId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return course;
    }

    List<Course> getCourseOfChapter(int chapter) {
        List<Course> courses = null;
        try {
            courses = mapper.getCoursesOfChapter(chapter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return courses;
    }
}
