package dao.Teacher;

import bean.Teacher;

public interface TeacherDao {
    Teacher findUserByNameAndPass(String tid, String password);
}
