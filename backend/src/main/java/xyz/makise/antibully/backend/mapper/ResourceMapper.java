package xyz.makise.antibully.backend.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.makise.antibully.backend.entity.Resource;
import xyz.makise.antibully.backend.entity.Role;

import java.util.List;

@Repository
public interface ResourceMapper {
    @Select("select * from resource")
    List<Resource> getAllResource();

    @Select("select role.roleId,name " +
            "from resource_role join role " +
            "on resource_role.roleId=role.roleId " +
            "where resourceId=#{resourceId}")
    List<Role> getNeededRolesOfResourceByResourceId(int resourceId);
}
