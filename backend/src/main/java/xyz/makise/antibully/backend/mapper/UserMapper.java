package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Role;
import xyz.makise.antibully.backend.entity.User;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where name=#{username}")
    User getUserByName(String username);

    @Select("select role.roleId,role.name " +
            "from user_role join role on user_role.roleId=role.roleId " +
            "where user_role.userId=#{userId}")
    List<Role> getRolesOfUserByUserId(int userId);
}
