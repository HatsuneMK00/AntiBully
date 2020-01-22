package xyz.makise.antibully.backend.controller;

import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.Exercise;
import xyz.makise.antibully.backend.service.ExerciseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/exercise")
public class ExerciseController {
    final
    ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseId}")
    Exercise getExercise(@PathVariable("exerciseId") int exerciseId) {
        return exerciseService.getExercise(exerciseId);
    }

    @GetMapping("/course/{chapter}/{courseId}")
    Exercise getExerciseOfCourse(@PathVariable("chapter") int chapter, @PathVariable("courseId") int courseId) {
        return exerciseService.getExerciseOfCourse(chapter, courseId);
    }

    @GetMapping("/repo/{repoId}")
    List<Exercise> getExercisesOfRepo(@PathVariable("repoId") int repoId) {
        return exerciseService.getExercisesOfRepo(repoId);
    }

    @PostMapping("")
    Map<String, Object> addExercise(@RequestBody Exercise exercise) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (exercise.getAnswer() == null || exercise.getContent() == null
                || exercise.getReason() == null || exercise.getType() == null) {
            map.put("status", retVal);
            return map;
        }
        retVal = exerciseService.addExercise(exercise);
        map.put("status", retVal);
        map.put("exercise", exercise);
        return map;
    }

    @DeleteMapping("/{exerciseId}")
    Map<String, Object> deleteExercise(@PathVariable("exerciseId") int exerciseId) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        Exercise exercise = exerciseService.getExercise(exerciseId);
        retVal = exerciseService.deleteExercise(exerciseId);
        map.put("status", retVal);
        map.put("exercise", exercise);
        return map;
    }

    @PutMapping("")
    Map<String, Object> updateExerciseAnswer(@RequestBody Exercise exercise) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
//        这里没法简单的对是否有exerciseId进行检查 因为不传id的话id是0，但是0是一个有效id
//        所以这边暂时不检查了 传的时候记得不要忘了id就行
        if (exercise.getAnswer() == null || exercise.getReason() == null) {
            map.put("status", retVal);
            return map;
        }
        retVal = exerciseService.updateExerciseAnswer(exercise);
        map.put("status", retVal);
        map.put("exercise", exercise);
        return map;
    }
}
