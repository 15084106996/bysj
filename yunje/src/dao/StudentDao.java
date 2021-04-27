package dao;

import bean.Student;
import bean.OnlineTime;
import bean.Usual;

import java.util.List;

public interface StudentDao {
    Student findUserByNameAndPass(String stid, String password);

    List<Usual> findStudentUsual(String idOrName,String stid , long pageNum, String pageSize);

    long getTotal(String idOrName, String stid);

    long getTimetotal(String stid);

    List<OnlineTime> findStudentTime(String stid, long pageNum, String pageSize);

    long getTimesum(String stid);
}
