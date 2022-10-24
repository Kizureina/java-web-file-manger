package com.hit.service;

import com.hit.mapper.UserMapper;
import com.hit.pojo.User;
import com.hit.util.CheckCodeUtil;
import com.hit.util.MailUtil;
import com.hit.util.SqlSessionFactoryUtils;
import com.hit.web.Servlet.Checkcode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Yoruko
 */
public class UserService {
    public static String code;
    static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<User> selectAllUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.selectAllUsers();
        return users;
    }
    public int insertUser(String username, String password, int status){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.insertUserByName(username, password, status);
        sqlSession.commit();
        return result;
    }
    public static void editUserStatus(String username, int status){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.editUserStatus(username, status);
        sqlSession.commit();
    }
    public boolean doRegister(String userName, String email) {
        //生成激活码
        code = Checkcode.getCodeString();
        //保存成功则通过线程的方式给用户发送一封邮件
        try {
            MailUtil.CODE = code;
            new Thread(new MailUtil(email, userName)).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public int selectUserStatusByName(String username){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUserStatusByName(username);
    }
}
