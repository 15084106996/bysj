package controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import bean.RespBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeacherLogoutServlet",value = "/teacherLogout")
public class TeacherLogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.getSession().invalidate();
        RespBean resp = RespBean.success(200, "ιεΊζε");
        response.getWriter().println(new ObjectMapper().writeValueAsString(resp));
    }
}