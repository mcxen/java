package DAO;

import pojo.Student;
import pojo.Teacher;

import java.util.List;

public interface teacherMapper {
    List<Teacher> getById(int id);
}
