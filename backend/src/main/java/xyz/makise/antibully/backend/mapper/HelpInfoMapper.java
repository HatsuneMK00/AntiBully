package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.HelpInfo;

import java.util.List;

@Repository
public interface HelpInfoMapper {
    /*
    * 获取全部的求助信息
    * 管理员用
    * */
    @Select("select * from help_info")
    List<HelpInfo> getAllHelpInfos();

    @Update("update help_info set status='handled' where infoId=#{infoId}")
    int makeHelpInfoHandled(int infoId);

}
