package controller.student;

import bean.PageInfos;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.StudentUsualServie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchStudentUsualServlet" ,value = "/searchStudentUsual")
public class SearchStudentUsualServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String idOrName=request.getParameter("idOrName");
        String stid=request.getParameter("stid");
        String pageNum=request.getParameter("pageNum");
        String pageSize=request.getParameter("pageSize");
        StudentUsualServie servie=new StudentUsualServie();
        PageInfos pageInfos=servie.findStudentUsual(idOrName,stid,pageNum,pageSize);
        response.getWriter().println(new ObjectMapper().writeValueAsString(pageInfos));
    }
}
