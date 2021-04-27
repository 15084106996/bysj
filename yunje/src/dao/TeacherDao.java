package dao;

import bean.Teacher;

public interface TeacherDao {
    Teacher findUserByNameAndPass(String tid, String password);
}
