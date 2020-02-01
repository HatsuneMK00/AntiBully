package xyz.makise.antibully.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.Course;
import xyz.makise.antibully.backend.service.CourseService;

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

    @GetMapping("/courses")
    List<Map<String, Object>> getAllCoursesAdmin() {
        return courseService.getAllCourses();
    }

    @PutMapping("/course")
    int editCourseAdmin(@RequestBody Map<String, Object> params) {
        int retVal1;
        int retVal2;
        Course course = new Course();
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

}
