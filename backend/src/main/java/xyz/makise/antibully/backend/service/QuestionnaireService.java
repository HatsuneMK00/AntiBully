package xyz.makise.antibully.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.Questionnaire;
import xyz.makise.antibully.backend.mapper.QuestionnaireMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireService {
    final
    QuestionnaireMapper questionnaireMapper;

    public QuestionnaireService(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<Questionnaire> getAllQuestionnaires() {
        try {
            return questionnaireMapper.getAllQuestionnaires();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
//        出错的时候返回null
        return null;
    }

    public Questionnaire getQuestionnaire(int questionnaireId) {
        try {
            return questionnaireMapper.getQuestionnaire(questionnaireId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public int addQuestionnaire(Questionnaire questionnaire) {
        try {
            return questionnaireMapper.addQuestionnaire(questionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
//        数据库层出错时返回-1
        return -1;
    }

    public int updateQuestionnaireUri(Questionnaire questionnaire) {
        try {
            questionnaireMapper.updateQuestionnaireUri(questionnaire);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public int deleteQuestionnaire(int questionnaireId) {
        try {
            questionnaireMapper.deleteQuestionnaire(questionnaireId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

//    TODO:用户是否做过某一个Questionnaire在UserService中完成
}
