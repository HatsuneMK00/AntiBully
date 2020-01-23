package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Questionnaire;

import java.util.List;

@Repository
public interface QuestionnaireMapper {
    @Select("select * from questionnaire")
    List<Questionnaire> getAllQuestionnaires();

    @Select("select * from questionnaire where questionnaireId=#{questionnaireId}")
    Questionnaire getQuestionnaire(int questionnaireId);

    @Options(useGeneratedKeys = true,keyProperty = "questionnaireId")
    @Insert("insert into questionnaire(uri) values(#{uri})")
    int addQuestionnaire(Questionnaire questionnaire);

    @Update("update questionnaire set uri=#{uri} where questionnaireId=#{questionnaireId}")
    int updateQuestionnaireUri(Questionnaire questionnaire);

    @Delete("delete from questionnaire where questionnaireId=#{questionnaireId}")
    int deleteQuestionnaire(int questionnaireId);
}
