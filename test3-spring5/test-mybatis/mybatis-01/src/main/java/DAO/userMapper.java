package DAO;

import org.apache.ibatis.annotations.Select;
import pojo.User;

import java.util.List;
import java.util.Map;

public interface userMapper {
    public List<User> getUserList();
    public User getUserById(int id);
    public void insertUser(User user);
    void updateUser(User user);
    void deleteUser(int i );
    List<User> getUserLike(String value);
    List<User> getUserByLimit(Map<String,Integer> map);
    @Select("select * from mybatis.user_tb")
    List<User> getAll();


}
