package xyz.makise.antibully.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.Course;
import xyz.makise.antibully.backend.entity.Exercise;
import xyz.makise.antibully.backend.entity.HelpInfo;
import xyz.makise.antibully.backend.service.CourseService;
import xyz.makise.antibully.backend.service.ExerciseService;
import xyz.makise.antibully.backend.service.HelpInfoService;

import java.util.List;
import java.util.Map;


/*
 * 添加一个admin用于所有管理员的操作
 * 没想好应该按照功能对系统进行划分还是按照权限对系统进行划分
 * 所以先在admin里面都写上 里面会有冗余的功能 仅仅是api不同 这个类为且仅为管理员前端页面服务
 *
 * */
@RestController
@ResponseBody
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    CourseService courseService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    HelpInfoService helpInfoService;

    @GetMapping("/courses")
    List<Map<String, Object>> getAllCoursesAdmin() {
        return courseService.getAllCourses();
    }

    @PutMapping("/course")
    int editCourseAdmin(@RequestBody Map<String, Object> params) {
        int retVal1;
        int retVal2;
        Course course = new Course();
//        这边不用使用Integer.parseInt的理由可能是在表单里面这两项是不能改的
        course.setChapter((Integer) params.get("chapter"));
        course.setCourseId((Integer) params.get("courseId"));
        course.setUri((String) params.get("uri"));
        retVal1 = courseService.updateCourseUri(course);
        retVal2 = courseService.updateBindOfExerciseAndCourse(
                Integer.parseInt((String) params.get("exerciseId")),
                (Integer) params.get("chapter"),
                (Integer) params.get("courseId")
        );
//        两个返回值都为正才返回1 否则返回-1表示出现问题
        if (retVal1 + retVal2 <= 0 || retVal1 * retVal2 <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @DeleteMapping("/course/{chapter}/{courseId}")
    int deleteCourseAdmin(@PathVariable("chapter") int chapter, @PathVariable("courseId") int courseId) {
        return courseService.deleteCourse(chapter, courseId);
    }

    @PostMapping("/course")
    int addCourseAdmin(@RequestBody Map<String, Object> params) {
        int retVal1;
        int retVal2;
        if (!params.containsKey("chapter") || !params.containsKey("courseId")
                || !params.containsKey("uri") || !params.containsKey("exerciseId")) {
            return -1;
        }
        Course course = new Course();
        course.setChapter(Integer.parseInt((String) params.get("chapter")));
        course.setCourseId(Integer.parseInt((String) params.get("courseId")));
        course.setUri((String) params.get("uri"));
        retVal1 = courseService.addCourse(course);

        retVal2 = courseService.bindExerciseAndCourse(
                Integer.parseInt((String) params.get("chapter")),
                Integer.parseInt((String) params.get("courseId")),
                Integer.parseInt((String) params.get("exerciseId"))
        );
        if (retVal1 + retVal2 <= 0 || retVal1 * retVal2 <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @GetMapping("/exercises")
    List<Exercise> getAllExercisesAdmin() {
        return exerciseService.getAllExercises();
    }

    @PutMapping("/exercise")
    int updateExerciseAdmin(@RequestBody Exercise exercise) {
        return exerciseService.updateExercise(exercise);
    }

    @DeleteMapping("/exercise/{exerciseId}")
    int deleteExerciseAdmin(@PathVariable("exerciseId") int exerciseId) {
        return exerciseService.deleteExercise(exerciseId);
    }

    @PostMapping("/exercise")
    Exercise addExercise(@RequestBody Exercise exercise) {
        if (!exerciseService.validateExercise(exercise)) {
            return null;
        }
        int retVal = exerciseService.addExercise(exercise);
        if (retVal != 1) {
            return null;
        }
        return exercise;
    }

    @GetMapping("/helpInfos")
    List<HelpInfo> getAllHelpInfosAdmin() {
        return helpInfoService.getAllHelpInfos();
    }

    @PutMapping("/helpInfoHandled/{infoId}")
    int helpInfoHandledAdmin(@PathVariable("infoId") int infoId) {
        return helpInfoService.makeHelpInfoHandled(infoId);
    }
}
