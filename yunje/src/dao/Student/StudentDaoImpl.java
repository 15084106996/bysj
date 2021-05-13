package dao.Student;

import bean.*;
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
    public long getUsualTotal(String idOrName, String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from usual, course,student,score," +
                    "ranking,avgscore,details where course.cid=usual.cid and student.stid=usual.stid and score.sid=usual.sid " +
                    "and ranking.rid=usual.rid and avgscore.avgid=usual.avgid and details.did=usual.did and student.stid=? " +
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
            ps = conn.prepareStatement("select course.cid as cid,cname,student.stid as stid,stname,score,sumscore," +
                    "classavgscore,gradeavgscore,classranking,graderanking,details,opinion from usual, course,student,score," +
                    "ranking,avgscore,details where course.cid=usual.cid and student.stid=usual.stid and score.sid=usual.sid " +
                    "and ranking.rid=usual.rid and avgscore.avgid=usual.avgid and details.did=usual.did and student.stid=? " +
                    "and(course.cid=? or course.cname like concat('%',?,'%')) limit ?,?");
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
                usual.setScore(rs.getString("score"));
                usual.setSumscore(rs.getString("sumscore"));
                usual.setClassavgscore(rs.getString("classavgscore"));
                usual.setGradeavgscore(rs.getString("gradeavgscore"));
                usual.setClassranking(rs.getString("classranking"));
                usual.setGraderanking(rs.getString("graderanking"));
                usual.setDetails(rs.getString("details"));
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
    public long getTimeTotal(String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from student,onlinetime,submissions " +
                    "where student.stid=onlinetime.stid and submissions.subid=onlinetime.subid and student.stid=?");
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
            ps = conn.prepareStatement("select student.stid as stid,stname,logintime,logouttime,onlinetime,sumtime," +
                    "submissions,sumsubmissions from student,onlinetime,submissions where student.stid=onlinetime.stid " +
                    "and submissions.subid=onlinetime.subid and student.stid=? limit ?,?");
            ps.setString(1, stid);
            ps.setLong(2, pageNum);
            ps.setLong(3, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                OnlineTime onlineTime = new OnlineTime();
                onlineTime.setStid(rs.getString("stid"));
                onlineTime.setStname(rs.getString("stname"));
                onlineTime.setLogintime(rs.getString("logintime"));
                onlineTime.setLogouttime(rs.getString("logouttime"));
                onlineTime.setOnlinetime(rs.getString("onlinetime"));
                onlineTime.setSumtime(rs.getString("sumtime"));
                onlineTime.setSubmissions(rs.getString("submissions"));
                onlineTime.setSumsubmissions(rs.getString("sumsubmissions"));
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
    public List<Homework> findStudentHomework(String idOrName, String stid, long pageNum, String pageSize){
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Homework> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select course.cid as cid,cname,student.stid as stid,stname,kind,htid,score,sumscore,classranking,graderanking" +
                    " from homework,course,student,score,ranking where course.cid=homework.cid and student.stid=homework.stid" +
                    " and score.sid=homework.sid and ranking.rid=homework.rid and student.stid=? and (course.cid=? or" +
                    " course.cname like concat('%',?,'%')) limit ?,?");
            ps.setString(1, stid);
            ps.setString(2, idOrName);
            ps.setString(3, idOrName);
            ps.setLong(4, pageNum);
            ps.setLong(5, Long.valueOf(pageSize));
            rs = ps.executeQuery();
        while (rs.next()) {
            Homework homework = new Homework();
            homework.setCid(rs.getString("cid"));
            homework.setCname(rs.getString("cname"));
            homework.setStid(rs.getString("stid"));
            homework.setStname(rs.getString("stname"));
            homework.setScore(rs.getString("score"));
            homework.setSumscore(rs.getString("sumscore"));
            homework.setClassranking(rs.getString("classranking"));
            homework.setGraderanking(rs.getString("graderanking"));
            homework.setHtid(rs.getString("htid"));
            homework.setKind(rs.getString("kind"));
            list.add(homework);
        }
    } catch(
    Exception e)

    {
        e.printStackTrace();
    } finally
    {
        DBUtils.getInstance().close(rs);
        DBUtils.getInstance().close(ps);
        DBUtils.getInstance().close(conn);
    }
        return list;
     }
    @Override
    public long getHomeworkTotal(String idOrName, String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from homework,course,student,score,ranking where " +
                    "course.cid=homework.cid and student.stid=homework.stid and score.sid=homework.sid and " +
                    "ranking.rid=homework.rid and student.stid=? and (course.cid=? or " +
                    "course.cname like concat('%',?,'%'))");
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
    public List<Topic> findStudentTopic(String htid, long pageNum, String pageSize) {
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Topic> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select topkind,topname,toptext,kname,ktext,score,sumscore,submissions,details,opinion" +
                    " from homeworktopic,homework,knowledge,score,submissions,details,topic " +
                    "where homework.htid=homeworktopic.htid and knowledge.kid=homeworktopic.kid and score.sid=homeworktopic.sid " +
                    "and submissions.subid=homeworktopic.subid and details.did=homeworktopic.did and topic.topid=homeworktopic.topid " +
                    "and homeworktopic.htid=? limit ?,?");
            ps.setString(1, htid);
            ps.setLong(2, pageNum);
            ps.setLong(3, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic= new Topic();
                topic.setTopkind(rs.getString("topkind"));
                topic.setTopname(rs.getString("topname"));
                topic.setToptext(rs.getString("toptext"));
                topic.setKtext(rs.getString("ktext"));
                topic.setKname(rs.getString("kname"));
                topic.setScore(rs.getString("score"));
                topic.setSumscore(rs.getString("sumscore"));
                topic.setSubmissions(rs.getString("submissions"));
                topic.setDetails(rs.getString("details"));
                topic.setOpinion(rs.getString("opinion"));
                list.add(topic);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally
        {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public long getTopicTotal(String htid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from homeworktopic,homework,knowledge,score,submissions,details,topic" +
                    " where homework.htid=homeworktopic.htid and knowledge.kid=homeworktopic.kid and score.sid=homeworktopic.sid " +
                    "and submissions.subid=homeworktopic.subid and details.did=homeworktopic.did and topic.topid=homeworktopic.topid " +
                    "and homeworktopic.htid=?");
            ps.setString(1, htid);
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
    public List<Experiment> findStudentExperiment(String idOrName, String stid, long pageNum, String pageSize) {
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Experiment> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select course.cid as cid,cname,student.stid as stid,stname,score,sumscore,details," +
                    "opinion,ename,etext,kind,classranking,graderanking,submissions,sumsubmissions from experiment,experimentstudent," +
                    "details,score,submissions,course,ranking,student where experimentstudent.eid=experiment.eid and " +
                    "experimentstudent.did=details.did and experimentstudent.sid=score.sid and experimentstudent.cid=course.cid " +
                    "and experimentstudent.rid=ranking.rid and experimentstudent.subid=submissions.subid and e" +
                    "xperimentstudent.stid=student.stid and student.stid=? and (course.cid=? or course.cname " +
                    "like concat('%',?,'%')) limit ?,?");
            ps.setString(1, stid);
            ps.setString(2, idOrName);
            ps.setString(3, idOrName);
            ps.setLong(4, pageNum);
            ps.setLong(5, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                Experiment experiment= new Experiment();
                experiment.setCid(rs.getString("cid"));
                experiment.setCname(rs.getString("cname"));
                experiment.setStid(rs.getString("stid"));
                experiment.setStname(rs.getString("stname"));
                experiment.setScore(rs.getString("score"));
                experiment.setSumscore(rs.getString("sumscore"));
                experiment.setDetails(rs.getString("details"));
                experiment.setOpinion(rs.getString("opinion"));
                experiment.setClassranking(rs.getString("classranking"));
                experiment.setGraderanking(rs.getString("graderanking"));
                experiment.setEname(rs.getString("ename"));
                experiment.setEtext(rs.getString("etext"));
                experiment.setKind(rs.getString("kind"));
                experiment.setSubmissions(rs.getString("submissions"));
                experiment.setSumsubmissions(rs.getString("sumsubmissions"));
                list.add(experiment);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally
        {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public long getExperimentTotal(String idOrName, String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from experiment,experimentstudent,details,score,submissions," +
                    "course,ranking,student where experimentstudent.eid=experiment.eid and experimentstudent.did=details.did " +
                    "and experimentstudent.sid=score.sid and experimentstudent.cid=course.cid and experimentstudent.rid=ranking.rid " +
                    "and experimentstudent.subid=submissions.subid and experimentstudent.stid=student.stid " +
                    "and student.stid=? and (course.cid=? or course.cname like concat('%',?,'%')) ");
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
    public List<Test> findStudentTest(String idOrName, String stid, long pageNum, String pageSize) {
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Test> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select course.cid as cid,cname,student.stid as stid,stname,ttid,kind,score," +
                    "sumscore,classranking,graderanking,ratio from test,score,course,student,ranking where test.cid=course.cid " +
                    "and test.sid=score.sid and test.stid=student.stid and test.rid=ranking.rid " +
                    "and student.stid=? and (course.cid=? or course.cname like concat('%',?,'%')) limit ?,?");
            ps.setString(1, stid);
            ps.setString(2, idOrName);
            ps.setString(3, idOrName);
            ps.setLong(4, pageNum);
            ps.setLong(5, Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                Test test=new Test();
                test.setCid(rs.getString("cid"));
                test.setCname(rs.getString("cname"));
                test.setStid(rs.getString("stid"));
                test.setStname(rs.getString("stname"));
                test.setKind(rs.getString("kind"));
                test.setRatio(rs.getString("ratio"));
                test.setScore(rs.getString("score"));
                test.setSumscore(rs.getString("sumscore"));
                test.setClassranking(rs.getString("classranking"));
                test.setGraderanking(rs.getString("graderanking"));
                list.add(test);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally
        {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public long getTestTotal(String idOrName, String stid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(*) as total from test,score,course,student,ranking " +
                    "where test.cid=course.cid and test.sid=score.sid and test.stid=student.stid and test.rid=ranking.rid " +
                    "and student.stid=? and (course.cid=? or course.cname like concat('%',?,'%'))");
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
}