package com.mx.mapper;

import com.mx.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int queryMaxName();
    int insert(User record);

    int insertSelective(User record);

    User queryUserByname(String name);

    int deleteUserByname(String name);

    int updateUserByname(User user);

    List<User> queryUser();

    List<User> queryPageUsers(@Param("offset")int offset, @Param("pageSize")int pageSize,
                              @Param("sort")String sort, @Param("sortOrder")String sortOrder,
                              @Param("name")String name,@Param("uName")String uName);

    int getAlluserNum( @Param("name")String name,@Param("uName")String uName);


}