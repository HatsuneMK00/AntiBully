package xyz.makise.antibully.backend.controller;

import org.springframework.web.bind.annotation.*;
import xyz.makise.antibully.backend.entity.Questionnaire;
import xyz.makise.antibully.backend.service.QuestionnaireService;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/ques")
public class QuestionnaireController {
    final
    QuestionnaireService questionnaireService;

    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/{questionnaireId}")
    Questionnaire getQuestionnaire(@PathVariable("questionnaireId") int questionnaireId) {
        return questionnaireService.getQuestionnaire(questionnaireId);
    }

    @PostMapping("")
    Map<String, Object> addQuestionnaire(@RequestBody Questionnaire questionnaire) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (questionnaire.getUri() == null) {
            map.put("status",retVal);
            return map;
        }
        retVal = questionnaireService.addQuestionnaire(questionnaire);
        map.put("status", retVal);
        map.put("questionnaire", questionnaire);
        return map;
    }

    @PutMapping("")
    Map<String, Object> updateQuestionnaireUri(@RequestBody Questionnaire questionnaire) {
        HashMap<String, Object> map = new HashMap<>();
        int retVal = -1;
        if (questionnaire.getUri() == null) {
            map.put("status",retVal);
            return map;
        }
        retVal = questionnaireService.updateQuestionnaireUri(questionnaire);
        map.put("status", retVal);
        map.put("questionnaire", questionnaire);
        return map;
    }

    @DeleteMapping("/{questionnaireId}")
    Map<String, Object> deleteQuestionnaire(@PathVariable("questionnaireId") int questionnaireId) {
        HashMap<String, Object> map = new HashMap<>();
        Questionnaire questionnaireCopy = questionnaireService.getQuestionnaire(questionnaireId);
        int retVal = questionnaireService.deleteQuestionnaire(questionnaireId);
        map.put("status", retVal);
        map.put("questionnaire", questionnaireCopy);
        return map;
    }
}
