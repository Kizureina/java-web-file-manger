package com.hit.mapper;
import com.hit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yoruko
 */
public interface UserMapper {
    List<User> selectAllUsers();

    int insertUserByName(@Param("username") String username, @Param("password") String password, @Param("status")int status);

    void editUserStatus(@Param("username") String username, @Param("status")int status);
    int selectUserStatusByName(String username);
    boolean changePasswordByName(@Param("username") String username, @Param("password") String password);
}
