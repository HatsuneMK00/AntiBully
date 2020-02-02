package xyz.makise.antibully.backend.controller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.*;
import xyz.makise.antibully.backend.service.*;

import java.util.*;


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
    final
    CourseService courseService;
    final
    ExerciseService exerciseService;
    final
    HelpInfoService helpInfoService;
    final
    ExerciseRepoService exerciseRepoService;
    final
    QuestionnaireService questionnaireService;

    public AdminController(CourseService courseService, ExerciseService exerciseService,
                           HelpInfoService helpInfoService, ExerciseRepoService exerciseRepoService,
                           QuestionnaireService questionnaireService) {
        this.courseService = courseService;
        this.exerciseService = exerciseService;
        this.helpInfoService = helpInfoService;
        this.exerciseRepoService = exerciseRepoService;
        this.questionnaireService = questionnaireService;
    }

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

    @GetMapping("/repos")
    List<ExerciseRepo> getAllReposAdmin() {
        return exerciseRepoService.getAllExerciseRepos();
    }

    @GetMapping("/repo/{repoId}")
    List<Integer> getExercisesOfRepo(@PathVariable("repoId") int repoId) {
        return exerciseService.getExerciseIdsOfRepo(repoId);
    }

    @PostMapping("/repo/bindRepoAndExercise")
    int bindRepoAndExerciseAdmin(@RequestBody Map<String,Object> params) {
        List<Integer> selected = (List<Integer>) params.get("selected");
        int repoId = (int) params.get("repoId");
        List<Integer> oldSelected = exerciseService.getExerciseIdsOfRepo(repoId);
        List<Integer> intersect = new ArrayList<>(selected);

//  求selected和oldSelected的交集
        intersect.retainAll(oldSelected);
//  selectedSet现在表示需要新增的绑定
        selected.removeAll(intersect);
//  oldSelectedSet表示删除的绑定
        oldSelected.removeAll(intersect);

        int retVal = 0;
        retVal += exerciseRepoService.bindExercisesToRepo(repoId, selected);
        retVal += exerciseRepoService.unbindExercisesAndRepo(repoId, oldSelected);

        return retVal;
    }

    @PutMapping("/repo")
    int updateRepoAdmin(@RequestBody ExerciseRepo repo) {
        return exerciseRepoService.updateExerciseRepoTitle(repo);
    }

    @DeleteMapping("/repo/{repoId}")
    int deleteRepoAdmin(@PathVariable("repoId") int repoId) {
        return exerciseRepoService.deleteExerciseRepo(repoId);
    }

    @PostMapping("/repo")
    ExerciseRepo addRepoAdmin(@RequestBody ExerciseRepo repo) {
        if (!exerciseRepoService.validateRepo(repo)) {
            return null;
        }
        int retVal = exerciseRepoService.addExerciseRepo(repo);
        if (retVal != 1) {
            return null;
        }
        return repo;
    }

    @GetMapping("/questionnaires")
    List<Questionnaire> getAllQuestionnairesAdmin() {
        return questionnaireService.getAllQuestionnaires();
    }

    @PostMapping("/questionnaire")
    Questionnaire addQuestionnaireAdmin(@RequestBody Questionnaire questionnaire) {
        if (!questionnaireService.validateQuestionnaire(questionnaire)) {
            return null;
        }
        int retVal = questionnaireService.addQuestionnaire(questionnaire);
        if (retVal != 1) {
            return null;
        }
        return questionnaire;
    }

    @PutMapping("/questionnaire")
    int updateQuestionnaireAdmin(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.updateQuestionnaireUri(questionnaire);
    }

    @DeleteMapping("/questionnaire/{questionnaireId}")
    int deleteQuestionnaireAdmin(@PathVariable("questionnaireId") int questionnaireId) {
        return questionnaireService.deleteQuestionnaire(questionnaireId);
    }

}
