package DAO;

import pojo.Blog;

import java.util.List;
import java.util.Map;

public interface blogMapper {
    List<Blog> queryOne(Map map);
    void updateOne(Map map);
}
