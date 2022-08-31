package DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.Blog;
import pojo.Student;
import pojo.Teacher;
import pojo.User;
import utils.mybatisutil;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class userMapperTest {
    @Test
    public void testFirst(){
        SqlSession sqlSession = null;
        try {
            sqlSession = mybatisutil.getSqlSession();
            //第一种方法直接getMapper 就是获取dao的实现，然后获取对象。
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testByid(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        int i = 2;
        User userById = mapper.getUserById(i);
        System.out.println(userById);
        sqlSession.close();
    }
    @Test
    public void testInsert(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        mapper.insertUser(new User(6,"陈五","Hust2486"));
//        注意提交事务，所有的增删改查，都需要提交事务。
        sqlSession.commit();
        System.out.println(mapper.getUserById(5));
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        mapper.updateUser(new User(4,"Hust","CHN2486"));
        sqlSession.commit();
        System.out.println(mapper.getUserById(4));
    }
    @Test
    public void testDel(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        mapper.deleteUser(4);
        sqlSession.commit();
        System.out.println(mapper.getUserById(4));
    }

    @Test
    public void testLike(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        List<User> userList = mapper.getUserLike("%陈%");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void testLog4j(){
        Logger logger = Logger.getLogger(userMapperTest.class);
        logger.info("现在是北京时间14点");
        logger.debug("debug....");
    }

    @Test
    public void testLimitQuery(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",1);
        map.put("pageSize",1);
        List<User> users = mapper.getUserByLimit(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //注解开发测试
    @Test
    public void testAll(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
//        userMapper mapper = sqlSession.getMapper(userMapper.class);
//        List<User> users = mapper.getAll();
//        for (User user : users) {
//            System.out.println(user);
//        }
        teacherMapper mapper1 = sqlSession.getMapper(teacherMapper.class);
        List<Teacher> all = mapper1.getById(1);
        for (Teacher teacher : all) {
            System.out.println(teacher);
        }
    }
    //测试if动态的sql
    @Test
    public void testSql(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        blogMapper mapper = sqlSession.getMapper(blogMapper.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("title","帅气");
        List<Blog> blogs = mapper.queryOne(map);
        System.out.println("====/====///====");
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }
    @Test
    public void testSet(){
        SqlSession sqlSession = mybatisutil.getSqlSession();
        blogMapper mapper = sqlSession.getMapper(blogMapper.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("title","帅气");
        map.put("id","2");
        map.put("author","陈贤文");
        mapper.updateOne(map);
        sqlSession.commit();
        sqlSession.close();
    }

}
