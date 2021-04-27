package service;

import bean.PageInfoss;
import bean.OnlineTime;
import dao.StudentDao;
import dao.StudentDaoImpl;

import java.util.List;

public class StudentTimeServie {
    public PageInfoss findStudentTime(String stid, String pageNum, String pageSize) {
        StudentDao dao=new StudentDaoImpl();
            //查询一页的真实数据
            List<OnlineTime> list=dao.findStudentTime(stid,(Long.valueOf(pageNum)-1)*Long.valueOf(pageSize),pageSize);
            //查询总数
            long counts=dao.getTimetotal(stid);
            //查询总时间
            long sum=dao.getTimesum(stid);
            PageInfoss infoss=new PageInfoss();
            infoss.setTotal(counts);
            infoss.setData(list);
            infoss.setSum(sum);
            return infoss;
    }
}

