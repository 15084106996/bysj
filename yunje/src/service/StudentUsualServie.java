package service;

import bean.PageInfos;
import bean.Usual;
import dao.StudentDao;
import dao.StudentDaoImpl;

import java.util.List;

public class StudentUsualServie {
    StudentDao dao=new StudentDaoImpl();
    public PageInfos findStudentUsual(String idOrName,String stid, String pageNum, String pageSize) {
            //查询一页的真实数据
        List<Usual> list=dao.findStudentUsual(idOrName==null?"":idOrName,stid,(Long.valueOf(pageNum)-1)*Long.valueOf(pageSize),pageSize);
            //查询总数
        long counts=dao.getTotal(idOrName,stid);
        PageInfos infos=new PageInfos();
        infos.setTotal(counts);
        infos.setData(list);
        return infos;
    }
}
