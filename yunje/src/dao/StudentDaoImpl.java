package dao;

import bean.Student;
import bean.OnlineTime;
import bean.Usual;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student findUserByNameAndPass(String stid, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from student where  stid= ? and password = ?");
            ps.setString(1, stid);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStid(rs.getString("stid"));
                student.setStname(rs.getString("stname"));
                student.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return student;
    }

    @Override
    public long getTotal(String idOrName, String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from course,usual,student where " +
                    "(course.cid=usual.cid and usual.stid=student.stid and student.stid= ?)" +
                    "and(course.cid=? or course.cname like concat('%',?,'%'))");
            ps.setString(1, stid);
            ps.setString(2, idOrName);
            ps.setString(3, idOrName);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getLong("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }


    @Override
    public List<Usual> findStudentUsual(String idOrName, String stid, long pageNum, String pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usual> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select course.cid as cid," +
                    "course.cname as cname,student.stid as stid,student.stname as stname," +
                    "usual.score as score,usual.sumscore as sumscore,usual.details as details," +
                    "usual.avgscore as avgscore,usual.opinion as opinion from course,usual,student " +
                    "where (course.cid=usual.cid and usual.stid=student.stid and student.stid= ?)and" +
                    "(course.cid=? or course.cname like concat('%',?,'%')) limit ?,?");
            ps.setString(1, stid);
            ps.setString(2, idOrName);
            ps.setString(3, idOrName);
            ps.setLong(4, pageNum);
            ps.setLong(5, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                Usual usual = new Usual();
                usual.setCid(rs.getString("cid"));
                usual.setCname(rs.getString("cname"));
                usual.setStid(rs.getString("stid"));
                usual.setStname(rs.getString("stname"));
                usual.setScore(rs.getLong("score"));
                usual.setSumscore(rs.getLong("sumscore"));
                usual.setDetails(rs.getString("details"));
                usual.setAvgscore(rs.getLong("avgscore"));
                usual.setOpinion(rs.getString("opinion"));
                list.add(usual);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public long getTimetotal(String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from student,onlinetime where " +
                    "student.stid=onlinetime.stid and student.stid= ?");
            ps.setString(1, stid);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getLong("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public List<OnlineTime> findStudentTime(String stid, long pageNum, String pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OnlineTime> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select student.stid as stid," +
                    "stname, logintime,logouttime,onlinetime from onlinetime,student " +
                    "where student.stid=onlinetime.stid and student.stid= ? limit ?,?");
            ps.setString(1, stid);
            ps.setLong(2, pageNum);
            ps.setLong(3, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                OnlineTime onlineTime = new OnlineTime();
                onlineTime.setStid(rs.getString("stid"));
                onlineTime.setStname(rs.getString("stname"));
                onlineTime.setStid(rs.getString("stid"));
                onlineTime.setStname(rs.getString("stname"));
                onlineTime.setLogintime(rs.getString("logintime"));
                onlineTime.setLogouttime(rs.getString("logouttime"));
                onlineTime.setOnlinetime(rs.getLong("onlinetime"));
                list.add(onlineTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public long getTimesum(String stid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long sum = 0;
        try {
        conn = DBUtils.getInstance().getConnection();
        ps = conn.prepareStatement("select sumtime from onlinetime where onlinetime.stid= ?");
        ps.setString(1, stid);
        rs = ps.executeQuery();
        if (rs.next()) {
            sum = rs.getLong("sumtime");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBUtils.getInstance().close(rs);
        DBUtils.getInstance().close(ps);
        DBUtils.getInstance().close(conn);
    }
        return sum;
    }
}