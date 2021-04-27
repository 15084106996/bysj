package dao;

import bean.Teacher;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public Teacher findUserByNameAndPass(String tid, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher = null;
        try {

            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from teacher where  tid= ? and password = ?");
            ps.setString(1,tid);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
              teacher =new Teacher();
              teacher.setTid(rs.getString("tid"));
              teacher.setTname(rs.getString("tname"));
              teacher.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return teacher;
    }
}
