package com.gana.student.web;

import com.gana.student.dao.StudentDao;
import com.gana.student.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    // allow injection for tests
    private com.gana.student.dao.StudentDao studentDao = new com.gana.student.dao.StudentDao();

    // package-private setter for tests
    void setStudentDao(com.gana.student.dao.StudentDao dao) {
        this.studentDao = dao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Student> students = studentDao.listAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
