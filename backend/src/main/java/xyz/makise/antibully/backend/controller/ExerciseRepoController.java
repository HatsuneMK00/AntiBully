package xyz.makise.antibully.backend.controller;

import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.ExerciseRepo;
import xyz.makise.antibully.backend.service.ExerciseRepoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/repo")
public class ExerciseRepoController {
    final
    ExerciseRepoService exerciseRepoService;

    public ExerciseRepoController(ExerciseRepoService exerciseRepoService) {
        this.exerciseRepoService = exerciseRepoService;
    }

    @GetMapping("")
    List<ExerciseRepo> getAllRepos() {
        return exerciseRepoService.getAllExerciseRepos();
    }

    @GetMapping("/{repoId}")
    ExerciseRepo getRepo(@PathVariable("repoId") int repoId) {
        return exerciseRepoService.getExerciseRepo(repoId);
    }

    @PostMapping("/attachExercise")
    Map<String, Object> bindExerciseToRepo(
            @RequestParam("repoId") int repoId,
            @RequestParam("exerciseId") int exerciseId
    ) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = exerciseRepoService.bindExerciseToRepo(repoId, exerciseId);
        map.put("status", retVal);
        map.put("repoId", repoId);
        map.put("exerciseId", exerciseId);
        return map;
    }

    @PostMapping("")
    Map<String, Object> addRepo(@RequestBody ExerciseRepo repo) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (repo.getTitle() == null) {
            map.put("status", retVal);
            return map;
        }
        retVal = exerciseRepoService.addExerciseRepo(repo);
        map.put("status", retVal);
        map.put("repo", repo);
        return map;
    }

    @PutMapping("")
    Map<String, Object> updateRepoTitle(@RequestBody ExerciseRepo repo) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (repo.getTitle() == null) {
            map.put("status", retVal);
            return map;
        }
        retVal = exerciseRepoService.updateExerciseRepoTitle(repo);
        map.put("status", retVal);
        map.put("repo", repo);
        return map;
    }

    @DeleteMapping("/{repoId}")
    Map<String, Object> deleteRepo(@PathVariable("repoId") int repoId) {
        HashMap<String, Object> map = new HashMap<>();
        ExerciseRepo repoCopy = exerciseRepoService.getExerciseRepo(repoId);
        int retVal = exerciseRepoService.deleteExerciseRepo(repoId);
        map.put("status", retVal);
        map.put("repo", repoCopy);
        return map;
    }



}
