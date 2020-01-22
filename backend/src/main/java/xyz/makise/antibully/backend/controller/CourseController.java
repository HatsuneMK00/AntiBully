package xyz.makise.antibully.backend.controller;

import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.Course;
import xyz.makise.antibully.backend.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/course")
public class CourseController {
    final
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/chapter/{chapter}")
    List<Course> getCoursesOfChapter(@PathVariable("chapter") int chapter) {
        return courseService.getCourseOfChapter(chapter);
    }

    @GetMapping("/{chapter}/{courseId}")
    Course getCourse(@PathVariable("chapter") int chapter, @PathVariable("courseId") int courseId) {
        return courseService.getCourse(chapter, courseId);
    }

    @PutMapping("")
    Map<String, Object> updateCourseUri(@RequestBody Course course) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (course.getChapter() == 0 || course.getCourseId() == 0 || course.getUri() == null) {
            map.put("status", retVal);
            return map;
        }
        retVal = courseService.updateCourseUri(course);
        map.put("status", retVal);
        map.put("course", course);
        return map;
    }

    @DeleteMapping("/{chapter}/{courseId}")
    Map<String, Object> deleteCourse(@PathVariable("chapter") int chapter, @PathVariable("courseId") int courseId) {
        HashMap<String, Object> map = new HashMap<>();
        Course courseCopy = courseService.getCourse(chapter, courseId);
        int retVal = courseService.deleteCourse(chapter, courseId);
        map.put("status", retVal);
        map.put("course", courseCopy);
        return map;
    }

    @PostMapping("")
    Map<String, Object> addCourse(@RequestBody Course course) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (course.getChapter() == 0 || course.getCourseId() == 0 || course.getUri() == null || course.getDuration() == 0) {
            map.put("status", retVal);
            return map;
        }
        retVal = courseService.addCourse(course);
        map.put("status", retVal);
        map.put("course", course);
        return map;
    }

    @PostMapping("/attachExercise")
    Map<String, Object> bindExerciseAndCourse(
        @RequestParam("chapter") int chapter,
        @RequestParam("courseId") int courseId,
        @RequestParam("exerciseId") int exerciseId
    ) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = courseService.bindExerciseAndCourse(chapter, courseId, exerciseId);
        map.put("status", retVal);
        map.put("chapter", chapter);
        map.put("courseId", courseId);
        map.put("exerciseId", exerciseId);
        return map;
    }

}
