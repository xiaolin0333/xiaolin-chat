package pers.xiaolin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.xiaolin.entity.User;

/**
 * @author xiaolin03
 * @date 2025/5/28
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);
}
