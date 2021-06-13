package controller.teacher;

import bean.PageInfos;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.Teacher.TeacherSumScoreServie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchTeacherSumScoreServlet" ,value = "/searchTeacherSumScore")
public class SearchTeacherSumScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String cIdOrName=request.getParameter("cIdOrName");
        String stIdOrName=request.getParameter("stIdOrName");
        String pageNum=request.getParameter("pageNum");
        String pageSize=request.getParameter("pageSize");
        TeacherSumScoreServie servie=new TeacherSumScoreServie();
        PageInfos pageInfos=servie.findTeacherSumScore(cIdOrName,stIdOrName,pageNum,pageSize);
        response.getWriter().println(new ObjectMapper().writeValueAsString(pageInfos));
    }
}
